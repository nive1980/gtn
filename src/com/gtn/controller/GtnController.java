package com.gtn.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtn.dto.AlertDto;
import com.gtn.dto.CaseDto;
import com.gtn.dto.CaseLogDto;
import com.gtn.dto.ChartDto;
import com.gtn.dto.MenuItemDto;
import com.gtn.dto.MessageDto;
import com.gtn.dto.ProductDto;
import com.gtn.dto.ShoppingCartDto;
import com.gtn.dto.ShoppingCartItemDto;
import com.gtn.dto.SubscriptionDto;
import com.gtn.dto.UserDto;
import com.gtn.exception.ApplicationException;
import com.gtn.helper.DashBoardHelper;
import com.gtn.model.Alert;
import com.gtn.model.Case;
import com.gtn.model.CaseLog;
import com.gtn.model.MenuValue;
import com.gtn.model.Message;
import com.gtn.model.Product;
import com.gtn.model.RestResponse;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;
import com.gtn.model.SubscriptionUser;
import com.gtn.model.ThemePk;
import com.gtn.model.ThemeValue;
import com.gtn.model.User;
import com.gtn.service.AlertService;
import com.gtn.service.CaseService;
import com.gtn.service.CommonDataService;
import com.gtn.service.GenericService;
import com.gtn.service.MenuService;
import com.gtn.service.MessageService;
import com.gtn.service.ProductService;
import com.gtn.service.PublicationService;
import com.gtn.service.SubscriptionService;
import com.gtn.service.UserService;
import com.gtn.util.EmailUtil;

/**
 * REST layer
 *
 */
@RestController
public class GtnController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private CaseService caseService;

	@Autowired
	private AlertService alertService;

	@Autowired
	private MessageService messsageService;
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private GtnControllerHelper gtnControllerHelper;

	@Autowired
	private CommonDataService commonService;

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private EmailUtil emailUtil;

	private static final Logger logger = LoggerFactory.getLogger(GtnController.class);

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 * @throws ApplicationException
	 */
	@RequestMapping("/signup")
	public ResponseEntity<RestResponse>  signup(@RequestBody UserDto userSignUp) throws ApplicationException {
			HashMap<String, Object> responseData = new HashMap<String, Object>();
			try {
				List<String> validationResult = gtnControllerHelper.validateUserSignUp(userSignUp);
				System.out.println("validationResult :: " + validationResult);
				if(!gtnControllerHelper.isEmpty(validationResult)){
					RestResponse error = new RestResponse(false,"Server side validation fail",validationResult);
					ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error, HttpStatus.EXPECTATION_FAILED);
					return result;
				}

				if (userService.getUserByEmail(userSignUp.getEmail()) != null) {
					responseData.put("Status", "Fail");
					responseData.put("Message", "User with this Id already Exists");
					logger.info("User with Id {} already Exists." , userSignUp.getEmail());
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					RestResponse success = new RestResponse(true, "Successful", responseData);
					ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
					return result;
				}
			} catch (ApplicationException e) {
				// Do nothing as user needs to be created when user is not found
				// in the database
			}
			User user = gtnControllerHelper.parseInput(userSignUp);
			user.setUserType("P");
			user.setCreatedOn(new Date());
			user.setStatus("A");
			user.setSbu(generateSbu());
			userService.saveUser(user);
			logger.info("User created for id {} " , userSignUp.getEmail());
			responseData.put("Status", "Success");
			responseData.put("Username", userSignUp.getEmail());
			responseData.put("LoggedInUser", user);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;
	}

	private String generateSbu() throws ApplicationException{
		String sbu ;
		do
			sbu = RandomStringUtils.randomAlphanumeric(4);
		while(userService.checkSBUExist(sbu));

		return sbu;

	}
	@RequestMapping(value = "/modifyUserDetails", method = RequestMethod.POST)
	public Map<String, Object> modifyUserDetails(@RequestParam(value = "LoggedUser", required = false) String userInfo,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)
			throws ApplicationException {
		logger.info("In modifyUserDetails@@@@@@@@@ user" , userInfo);
		logger.info("In modifyUserDetails@@@@@@@@@ file" , file);
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			ObjectMapper mapper = new ObjectMapper();
			UserDto userDto = mapper.readValue(userInfo, UserDto.class);
			com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			User userToBeModified = userDetail.getUser();
			// System.out.println("userToBeModified
			// "+request.getParameter("firstName"));
			userToBeModified.setFirstName(userDto.getFirstName());
			userToBeModified.setLastName(userDto.getLastName());
			userToBeModified.setTitle(userDto.getTitle());
			userToBeModified.setPhone(userDto.getPhone());
			userToBeModified.setCompanyName(userDto.getCompany());
			userToBeModified.setEmailId(userDto.getEmail());
			userToBeModified.setModifiedOn(new Date());
			String rootDirectory = "D:\\files\\";
			if (file != null) {
				// Blob blob = new
				// SerialBlob(StreamUtils.copyToByteArray(file.getInputStream()));
				// Blob blob = Hibernate.createBlob(file.getInputStream());
				// byte[] fileBytes = new byte[(int) file.length()];
				// file.getInputStream().read(fileBytes);
				userToBeModified.setImage(file.getBytes());
				// file.transferTo(new File(rootDirectory +
				// file.getOriginalFilename()));

			}
			userService.updateUser(userToBeModified);
			response.put("LoggedInUser", (userToBeModified));

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			throw new ApplicationException(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ApplicationException(e.getMessage(), e);
		}
		return response;

	}

	@RequestMapping("/loginUser")
	public Map<String, Object> login(Principal user) {
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		HashMap<String, Object> response = new HashMap<String, Object>();
		System.out.println("user is "+userDetail.getUser().getFirstName());
		System.out.println("user is "+userDetail.getUser().getId());

		response.put("LoggedInUser", (userDetail.getUser()));
		return response;
	}

	private Subscription getUserSubscription() throws ApplicationException{
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userDetail.getUser();
		try{
			Subscription subscription = subscriptionService.getSubscriptionFromUser(user.getId());
			return subscription;
		}catch(Exception e){
		//	e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/fetchProductList")
	public ResponseEntity<RestResponse> fetchProductList() throws ApplicationException {
				
			List<Map<String, Object>> returnData = getProductList();
			logger.info("{} Products found ",returnData.size());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", returnData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;

	}

	private List<Map<String, Object>> getProductList() throws ApplicationException {
		try {
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Collection<Product> products = productService.getProducts();
			Subscription subscription = getUserSubscription();
			Collection<Product> activeProducts = null;
			if(subscription != null){
				activeProducts = subscriptionService.getUserPurchasedProducts(subscription.getSubscriptionId());
			}else{
				activeProducts = new ArrayList<Product>();
			}
			
			if (products != null && !products.isEmpty()) {
				for (Product product : products) {
					Map<String, Object> productMap = new HashMap<String, Object>();
					productMap.put("product", product.getDisplayName());
					ArrayList<ProductDto> subProductDto = new ArrayList<ProductDto>();
					Collection<Product> subProducts = productService.getSubProducts(product.getProductId());
					if (subProducts != null && !subProducts.isEmpty()) {
						for (Product subProduct : subProducts) {
							ProductDto productDto = gtnControllerHelper.parseProduct(subProduct);
							productDto.setParentProduct(product.getDisplayName());
							
							if(activeProducts.contains(subProduct)){
								productDto.setActive('A');
							}else{
								productDto.setActive('P');
							}
							
							subProductDto.add(productDto);
						}
						productMap.put("subproduct", subProductDto);
					}
					result.add(productMap);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("No data found " , e);
			throw new ApplicationException("No data found ", e);
		}
	}

	@RequestMapping("/createSubscription")
	public ResponseEntity<RestResponse> createSubscription(@RequestBody SubscriptionDto subscriptionDto) throws ApplicationException {
Subscription subscriptionreturned =null;
		try{
			subscriptionreturned=subscriptionService.getSubscriptionFromUser(userService.getUserByEmail(subscriptionDto.getUsername()).getId());
		}
		catch(Exception e) {
			System.out.println("subscription is new");
		}
		if(subscriptionreturned!=null) {
			HashMap<String, String> responseData = new HashMap<String, String>();
			responseData.put("Status", "Success");
			responseData.put("message", "ERROR_CODE_EXISTS");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;
		} else {	


		Subscription subscription = new Subscription();
			subscription.setNoOfCountries(subscriptionDto.getNoOfCountries());
			subscription.setNoOfUsers(subscriptionDto.getNoOfUsers());
			subscription.setTotalCost(subscriptionDto.getTotalCost());
			subscription.setCurrencyCode(subscriptionDto.getTotalCurrencyCode());
			subscription.setEffFromDate(new Date());
			//subscription.setEffToDate(DateUtils.addDays(new Date(), 30));
			subscription.setStatus("A");
			subscriptionService.saveSubscription(subscription);
			logger.info("Subscription created for {} " ,subscriptionDto.getUsername());
			for (ProductDto productDto : subscriptionDto.getProducts()) {
				SubscriptionProduct subscriptionProduct = new SubscriptionProduct();
				subscriptionProduct.setProductId(productDto.getProductId());
				subscriptionProduct.setSubscriptionId(subscription.getSubscriptionId());
				subscriptionProduct.setCreatedOn(new Date());
				subscriptionProduct.setStatus("P");
				subscriptionService.saveSubscriptionProduct(subscriptionProduct);
			}

			SubscriptionUser subscriptionUser = new SubscriptionUser();
			subscriptionUser.setSubscriptionId(subscription.getSubscriptionId());
			subscriptionUser.setUserId(userService.getUserByEmail(subscriptionDto.getUsername()).getId());
			subscriptionUser.setCreatedOn(new Date());
			subscriptionUser.setStatus("A");
			subscriptionService.saveSubscriptionUser(subscriptionUser);
			logger.info("Subscription User mapping added");
			HashMap<String, String> responseData = new HashMap<String, String>();
			
			responseData.put("Status", "Success");
			responseData.put("SubId", Long.toString(subscription.getSubscriptionId()));
			responseData.put("SubTotal", subscription.getTotalCost().toString());
			responseData.put("nextPage", "payment");
			
			logger.info("Subscription status - {}",responseData.get("Status"));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;
}
	}

	private SubscriptionProduct checkNew(Collection<SubscriptionProduct> current, ProductDto dto){
		for(SubscriptionProduct product: current){
			if(product.getProductId() == dto.getProductId()){
				return product;
			}
		}
		return null;
	}
	
	@RequestMapping("/modifySubscription")
	public Map<String, String> modifySubscription(@RequestBody SubscriptionDto subscriptionDto) {
		try {
			Subscription subscription = subscriptionService
					.getSubscription(new Long(subscriptionDto.getSubscriptionId()).longValue());
			
			com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			long userId = userDetail.getUser().getId();
			
			Long currentNoOfUsers = subscription.getNoOfUsers();
			HashMap<String, String> response = new HashMap<String, String>();
			
			Collection<User> users = userService.getAdditionalUsers(userId);
			
			if(users.size() + 1 > subscriptionDto.getNoOfUsers()){
				response.put("Status", "Error");
				response.put("msg", "Current subscription users : "+(users.size() + 1)+" can not be greater then : "+subscriptionDto.getNoOfUsers());
				return response;
			}
			
			logger.info("Subscriptino in kmodify@@@@@@@@@@@ " , subscription.getSubscriptionId());
			subscription.setNoOfCountries(subscriptionDto.getNoOfCountries());
			subscription.setNoOfUsers(subscriptionDto.getNoOfUsers());
			subscription.setTotalCost(subscriptionDto.getTotalCost());
			subscription.setCurrencyCode(subscriptionDto.getTotalCurrencyCode());
			subscription.setEffFromDate(new Date());
			subscription.setEffToDate(DateUtils.addDays(new Date(), 30));
			subscription.setStatus("A");
			subscriptionService.saveSubscription(subscription);
			
			Collection<SubscriptionProduct> currentSubscription = subscriptionService.getCurrentSubscriptionProducts(subscription.getSubscriptionId());
			/*List<ProductDto> modifiedSubscription = subscriptionDto.getProducts();
			
			//get new products for subscription
			for(ProductDto dto : modifiedSubscription){
				
			}
			*/
			subscriptionService.deleteSubscriptionProduct(subscription.getSubscriptionId());
			logger.info("Subscription created ");
			
			String nextPage = "";
			
			for (ProductDto productDto : subscriptionDto.getProducts()) {
				SubscriptionProduct subscriptionProduct = new SubscriptionProduct();
				subscriptionProduct.setProductId(productDto.getProductId());
				subscriptionProduct.setSubscriptionId(subscription.getSubscriptionId());
				subscriptionProduct.setCreatedOn(new Date());
				
				SubscriptionProduct dbValue = checkNew(currentSubscription, productDto);
				
				if(dbValue== null){
					subscriptionProduct.setStatus("P");
					nextPage = "payment";
				}else{
					subscriptionProduct.setStatus(dbValue.getStatus());
					subscriptionProduct.setCreatedOn(dbValue.getCreatedOn());
					subscriptionProduct.setModifiedOn(new Date());
					if("P".equalsIgnoreCase(dbValue.getStatus())){
						nextPage = "payment";
					}
				}
				
				subscriptionService.saveSubscriptionProduct(subscriptionProduct);
			}
			
			if(currentNoOfUsers < subscriptionDto.getNoOfUsers()){
				nextPage = "payment";
			}
			
			if("".equals(nextPage)){
				nextPage = "billing";
			}
			
			/*
			 * SubscriptionUser subscriptionUser = new SubscriptionUser();
			 * subscriptionUser.setSubscriptionId(subscription.getSubscriptionId
			 * ()); subscriptionUser.setUserId(userService.getUserByEmail(
			 * subscriptionDto.getUsername()).getId());
			 * subscriptionUser.setCreatedOn(new Date());
			 * subscriptionUser.setStatus("A");
			 * subscriptionService.saveSubscriptionUser(subscriptionUser);
			 */
			
			response.put("Status", "Success");
			response.put("SubId", Long.toString(subscription.getSubscriptionId()));
			response.put("SubTotal", subscription.getTotalCost().toString());
			response.put("nextPage", nextPage);

			return response;
		} catch (ApplicationException e) {
		//	e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/forgotPassword")
	public ResponseEntity<RestResponse>  forgotPassword(@RequestBody UserDto user) throws ApplicationException {
		Map<String, String> responseData = new HashMap<String, String>();
			if (userService.processForgotPassword(user.getEmail())) {
				//responseData.put("Status", "Success");
				
				String otp = userService.getOtpForUser(user.getEmail());
				
				String msg = "Please use this OTP to change your password : <BR /><BR />";
				msg += "<b>"+otp+"</b>";
				//send email
				//emailUtil.setMailSender();
				emailUtil.sendMailHtml("naveen.ara@gmail.com", user.getEmail(), "GTN Password change OTP : ", msg);
				
				responseData.put("Message", "Use OTP send to your email for Login and reset your password");
				responseData.put("UserId", user.getEmail());
			} else {
				//responseData.put("Status", "Fail");
				responseData.put("Message", "Enter a valid Email Id.");
				responseData.put("UserId", user.getEmail());
				RestResponse error = new RestResponse(false,"Server side validation fail",responseData);
				ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error, HttpStatus.EXPECTATION_FAILED);
				return result;

			}
			logger.info("Forgot Password status - {}",responseData.get("Status"));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;

	}

	@RequestMapping("/resetPassword")
	public ResponseEntity<RestResponse> resetPassword(@RequestBody UserDto user) throws ApplicationException {

		List<String> responseData = gtnControllerHelper.validateResetPassword(user);
		System.out.println("validationResult :: " + responseData);
		if (!gtnControllerHelper.isEmpty(responseData)) {
			RestResponse error = new RestResponse(false, "Server side validation fail", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error,
					HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		if (userService.processResetPassword(user.getEmail(), user.getOtp(), user.getPassword())) {
			logger.info("Reser Password status - Pass");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
			return result;
		}else{
			responseData.add("OTP is invalid or expired. Use Forgot Password link to reset your password.");
			RestResponse error = new RestResponse(false, "Application logic fail", responseData);
			ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error,
					HttpStatus.EXPECTATION_FAILED);
			logger.info("Reset Password status - Fail");
			return result;

		}
	}

	@RequestMapping("/changePassword")
	public ResponseEntity<RestResponse> changePassword(@RequestBody Map<String, Object> payload)
			throws ApplicationException {

		userService.changePassword((String) payload.get("email"), (String) payload.get("oldPassword"),
				(String) payload.get("newPassword"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		RestResponse success = new RestResponse(true, "Successful", "");
		headers.set("SUCCESS_MESSAGE", "Successful");
		// response.put("Status", "Success");
		ResponseEntity result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}

	@RequestMapping(value = "/getAdditionalUsers", method = RequestMethod.POST)
	public List<UserDto> getAdditionalUsers(@RequestBody UserDto userDto) throws Exception {
		logger.info("username in getAdditionalUsers is " , userDto);
		Collection<User> users = userService.getAdditionalUsers(new Long(userDto.getId()).longValue());
		logger.info("users " , users);

		ArrayList<UserDto> userDtos = new ArrayList<UserDto>();

		if (users != null && !users.isEmpty()) {
			logger.info("users not null");
			for (User user : users) {

				UserDto userDto1 = gtnControllerHelper.prepareUserDto(user);
				userDto1.setFirstName(userDto1.getFirstName()+" "+userDto1.getLastName());
				userDtos.add(userDto1);

			}
		}
		logger.info("size is " , userDtos.size());

		return userDtos;

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> deleteUser(@RequestBody UserDto userDto) throws Exception {
		int deletedNos = userService.deleteUser(userDto.getId());
		HashMap<String, String> response = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<RestResponse> result = null;
		RestResponse success = new RestResponse(true, "Successful", userDto);
		headers.set("SUCCESS_MESSAGE", "Successful");
		response.put("Status", "Success");
		result = new ResponseEntity<>(success, headers, HttpStatus.OK);

		return result;

	}

	@RequestMapping("/addAdditionalUser")
	public ResponseEntity<RestResponse> addAdditionalUser(@RequestBody UserDto newUser) throws ApplicationException {
		Map responseData = new HashMap();
		List<String> validationResult = gtnControllerHelper.validateAdditionalUserSignUp(newUser);
		System.out.println("validationResult :: " + validationResult);
		if(!gtnControllerHelper.isEmpty(validationResult)){
			RestResponse error = new RestResponse(false,"Server side validation fail",validationResult);
			ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		logger.info("New User " ,newUser);
		User parentUser = userService.getUserByEmail(newUser.getParentUserEmail());
		Subscription subscription = subscriptionService.getSubscription(newUser.getSubscriptionId());
		Collection<User> additionalUsers = userService.getAdditionalUsers(parentUser.getId());
		int additionalUserCount = 0;
		if (additionalUsers != null && !additionalUsers.isEmpty())
			additionalUserCount = additionalUsers.size();
		else
			additionalUserCount = 0;
		if (subscription!=null && subscription.getNoOfUsers() > 1 && additionalUserCount < (subscription.getNoOfUsers() - 1)) {
			try {
				if (userService.getUserByEmail(newUser.getEmail()) != null) {
					responseData.put("Status", "Fail");
					responseData.put("Message", "User with this Id already Exists");
					logger.info("User with Id {} already Exists",newUser.getEmail());
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					RestResponse success = new RestResponse(false, "Error", responseData);
					ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.BAD_REQUEST);
					return result;
				}
			} catch (ApplicationException e) {
				// Do nothing as user needs when user is not found in the
				// database
			}
			User user = gtnControllerHelper.parseInput(newUser);
			user.setUserType("A");
			user.setParentUserId(parentUser.getId());
			user.setResetPwdFlag("Y");
			user.setCreatedOn(new Date());
			user.setStatus("A");
			user.setSbu(parentUser.getSbu());
			String otp = RandomStringUtils.randomAlphanumeric(8);
			user.setPassword(otp);
			userService.saveUser(user);
			
			/**
			 * send email to user
			 */
			String msg = "Please use this OTP to change your password : <BR /><BR />";
			msg += "<b>"+otp+"</b>";
			//send email
			//emailUtil.setMailSender();
			//uncomment this later
			//emailUtil.sendMailHtml("naveen.ara@gmail.com", newUser.getEmail(), "GTN Password change OTP : ", msg);
			
			logger.info("Additional user added");
			SubscriptionUser subscriptionUser = new SubscriptionUser();
			subscriptionUser.setSubscriptionId(subscription.getSubscriptionId());
			subscriptionUser.setUserId(user.getId());
			subscriptionUser.setCreatedOn(new Date());
			subscriptionUser.setStatus("A");
			subscriptionService.saveSubscriptionUser(subscriptionUser);
			logger.info("Additional user added to subscription");
			responseData.put("Status", "Success");
			responseData.put("Message", "User Added Successfully");
			responseData.put("user", gtnControllerHelper.prepareUserDto(user));
		} else {
			responseData.put("Status", "Fail");
			responseData.put("Message", "Additional User can not be added based on the Subscription taken.");
			logger.info("Additional user can not be added to subscription");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", responseData);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;
	}

	@RequestMapping("/deleteSubscription")
	public ResponseEntity<RestResponse> deleteSubscription(@RequestBody UserDto userDto) throws ApplicationException {
		subscriptionService.deleteSubscription(userDto);
		HashMap<String, String> response = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<RestResponse> result = null;
		RestResponse success = new RestResponse(true, "Successful", userDto);
		headers.set("SUCCESS_MESSAGE", "Successful");
		response.put("Status", "Success");
		result = new ResponseEntity<>(success, headers, HttpStatus.OK);

		return result;

	}

	@RequestMapping("/getSubscriptionFromUser")
	public SubscriptionDto getSubscriptionFromUser(@RequestBody UserDto userDto) throws ApplicationException {

		
		Subscription subscription = subscriptionService.getSubscriptionFromUser(new Long(userDto.getId()).longValue());
		logger.info("subscription  " , subscription);
		Subscription subscriptionTransact =null;
		boolean isPaymentDone=false;
		try {
			subscriptionTransact = subscriptionService.getSubscriptionTransact(new Long(userDto.getId()).longValue());
			isPaymentDone=true;
		}
		catch(ApplicationException ae) {
			System.out.println("ae "+ae.getMessage());
		}
		System.out.println("subscriptionTransact "+subscriptionTransact);
		
		Collection<Product> products = subscriptionService.getSubscriptionProducts(subscription.getSubscriptionId());
		logger.info("subscription products " , products.size());
		ArrayList<ProductDto> productDtos = new ArrayList<ProductDto>();

		if (products != null && !products.isEmpty()) {
			logger.info("products not null");
			for (Product product : products) {

				ProductDto productDto = gtnControllerHelper.parseProduct(product);
				productDtos.add(productDto);

			}
		}

		SubscriptionDto subDto = gtnControllerHelper.prepareSubscriptionDto(subscription);
		subDto.setProducts(productDtos);
		subDto.setIsPaymentDone(""+isPaymentDone);
		
		return subDto;
	}

	@RequestMapping("/createFreeTrialSubscription")
	public void createFreeTrialSubscription(@RequestBody SubscriptionDto subscriptionDto) throws ApplicationException {
			Subscription subscription = new Subscription();
			subscription.setNoOfCountries(1);
			subscription.setNoOfUsers(1);
			subscription.setTotalCost(new BigDecimal(0));
			subscription.setCurrencyCode("USD");
			subscription.setEffFromDate(new Date());
			subscription.setEffToDate(DateUtils.addDays(new Date(), 15));
			subscription.setStatus("A");
			subscriptionService.saveSubscription(subscription);
			logger.info("Free trail Subscription created for {} " , subscriptionDto.getUsername());
			Collection<Product> products = productService.getProducts();
			for (Product product : products) {
				if (!product.getDisplayName().equalsIgnoreCase("Publications")) {
					Collection<Product> subProducts = productService.getSubProducts(product.getProductId());
					for (Product subProduct : subProducts) {
						SubscriptionProduct subscriptionProduct = new SubscriptionProduct();
						subscriptionProduct.setProductId(subProduct.getProductId());
						subscriptionProduct.setSubscriptionId(subscription.getSubscriptionId());
						subscriptionProduct.setCreatedOn(new Date());
						subscriptionProduct.setStatus("A");
						subscriptionService.saveSubscriptionProduct(subscriptionProduct);
					}
				}
			}
			SubscriptionUser subscriptionUser = new SubscriptionUser();
			subscriptionUser.setSubscriptionId(subscription.getSubscriptionId());
			subscriptionUser.setUserId(userService.getUserByEmail(subscriptionDto.getUsername()).getId());
			subscriptionUser.setCreatedOn(new Date());
			subscriptionUser.setStatus("A");
			subscriptionService.saveSubscriptionUser(subscriptionUser);
			logger.info("Subscription User mapping added");
	}

	@RequestMapping("/saveCase")
	public ResponseEntity<RestResponse> saveCase(@RequestBody CaseDto caseDto) throws ApplicationException {
		List<String> validationResult = gtnControllerHelper.validateCase(caseDto);
		System.out.println("validationResult :: " + validationResult);
		if(!gtnControllerHelper.isEmpty(validationResult)){
			RestResponse error = new RestResponse(false,"Server side validation fail",validationResult);
			ResponseEntity<RestResponse> result = new ResponseEntity<RestResponse>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}

		User loggedInUser = userService.getUserByEmail(caseDto.getUserId());
		Case newCase = new Case();
		if (caseDto.getTicketId() > 0) {
			newCase = caseService.getCase(caseDto.getTicketId());
		}
		gtnControllerHelper.populateCase(caseDto, newCase, loggedInUser);
		caseService.saveCase(newCase);
		logger.info("Case added by {} and Ticke Id : {} ",loggedInUser.getEmailId(),newCase.getTicketId());
		if (!gtnControllerHelper.isEmpty(caseDto.getNewMessage())) {
			CaseLog newCaseLog = new CaseLog();
			newCaseLog.setTicketId(newCase.getTicketId());
			newCaseLog.setMessage(caseDto.getNewMessage());
			newCaseLog.setMessageBy(loggedInUser.getId());
			newCaseLog.setMessageOn(new Date());
			caseService.saveCaseLog(newCaseLog);
		}
		//Add message when Admin updates case
		if("S".equalsIgnoreCase(loggedInUser.getUserType()))
			addMessageForCaseUpdate(newCase);

		List<CaseDto> cases = getAllCasesByUser(loggedInUser);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", cases);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;
	}

	private void addMessageForCaseUpdate(Case caseItem) throws ApplicationException{
		Message message = new Message();
		message.setDescription("Support Response received for Ticket Id : " + caseItem.getTicketId());
		message.setTicketId(caseItem.getTicketId());
		message.setUserId(caseItem.getCreatedBy());
		message.setStatus("U");
		message.setCreatedDate(new Date());
		messsageService.saveMessage(message);
		logger.info("Message record added for Response from Support for Ticket Id : {} "+caseItem.getTicketId());
	}
	@RequestMapping("/getCasesToDisplay")
	public ResponseEntity<RestResponse> getCasesToDisplay(@RequestBody CaseDto caseDto) throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(caseDto.getUserId());
		List<CaseDto> cases = getAllCasesByUser(loggedInUser);
		logger.info("No of cases to Display : {}" ,cases.size());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", cases);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;
	}

	private List<CaseDto> getAllCasesByUser(User user) throws ApplicationException {
		List<CaseDto> caseDtos = new ArrayList<CaseDto>();
		Collection<Case> cases = null;
		if ("S".equalsIgnoreCase(user.getUserType()))
			cases = caseService.getCasesForAdmin(user.getId());
		else
			cases = caseService.getCasesByUser(user.getId());
		for (Case caseModel : cases) {
			User createdBy = userService.getUser(caseModel.getCreatedBy());
			User assignedTo = (caseModel.getAssignedTo() > 0) ? userService.getUser(caseModel.getAssignedTo()) : null;
			Collection<CaseLog> caseLogs = caseService.getCaseLogs(caseModel.getTicketId());
			List<CaseLogDto> caseLogDtos = new ArrayList<CaseLogDto>();
			for (CaseLog caseLog : caseLogs) {
				User messageBy = userService.getUser(caseLog.getMessageBy());
				caseLogDtos.add(gtnControllerHelper.populareCaseLogDto(caseLog,
						messageBy.getFirstName() + " " + messageBy.getLastName()));
			}
			CaseDto caseDtoItem = gtnControllerHelper.populateCaseDto(caseModel, createdBy, assignedTo, caseLogDtos,
					getProductList());
			caseDtos.add(caseDtoItem);
		}
		return caseDtos;
	}

	@RequestMapping("/getActivityDataForCase")
	public ResponseEntity<RestResponse> getActivityDataForCase(@RequestBody CaseDto caseDto)
			throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(caseDto.getUserId());
		ArrayList<ChartDto> chartDtos = new ArrayList<ChartDto>();
		Collection<Case> openCases = caseService.getOpenCasesByUser(loggedInUser.getId());
		Collection<Case> closedCases = caseService.getClosedCasesByUser(loggedInUser.getId());
		ChartDto openChartDto = new ChartDto();
		openChartDto.setLabel("Open");
		openChartDto.setValue(gtnControllerHelper.isEmpty(openCases) ? 0 : openCases.size());
		chartDtos.add(openChartDto);
		ChartDto closedChartDto = new ChartDto();
		closedChartDto.setLabel("Closed");
		closedChartDto.setValue(gtnControllerHelper.isEmpty(closedCases) ? 0 : closedCases.size());
		chartDtos.add(closedChartDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", chartDtos);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}

	@RequestMapping("/getAlerts")
	public ResponseEntity<RestResponse> getAlerts(@RequestBody AlertDto alertDto)
			throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(alertDto.getUserId());
		ArrayList<AlertDto> alertDtos = new ArrayList<AlertDto>();
		Collection<Alert> alerts = alertService.getAlerts(loggedInUser.getId());
		for(Alert alert : alerts){
			AlertDto alertDtoItem = new AlertDto();
			alertDtoItem.setAlertDescription(alert.getDescription());
			alertDtos.add(alertDtoItem);
		}
		logger.info("No of Alerts for {} : {}",alertDto.getUserId(),alertDtos.size());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", alertDtos);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}

	@RequestMapping("/getUnreadMessageCount")
	public ResponseEntity<RestResponse> getUnreadMessageCount(@RequestBody MessageDto messageDto)
			throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(messageDto.getUserId());
		long unreadMessageCount = messsageService.getUnreadMessageCountByUser(loggedInUser.getId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", unreadMessageCount);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}

	@RequestMapping("/getUnreadMessageCountForCases")
	public ResponseEntity<RestResponse> getUnreadMessageCountForCases(@RequestBody MessageDto messageDto)
			throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(messageDto.getUserId());
		long unreadMessageCountForCase = messsageService.getUnreadMessageCountForCasesByUser(loggedInUser.getId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", unreadMessageCountForCase);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}

	@RequestMapping("/markMessageAsReadForCases")
	public ResponseEntity<RestResponse> markMessageAsReadForCases(@RequestBody MessageDto messageDto)
			throws ApplicationException {
		User loggedInUser = userService.getUserByEmail(messageDto.getUserId());
		messsageService.markMessageAsReadForCases(loggedInUser.getId(),messageDto.getTicketId());
		long unreadMessageCountForCase = messsageService.getUnreadMessageCountForCasesByUser(loggedInUser.getId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", unreadMessageCountForCase);
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);
		return result;

	}
@RequestMapping("/shopTheCart")
	public ResponseEntity<RestResponse> shopTheCart(@RequestBody ShoppingCartDto shoppingCartDto)
			throws ApplicationException {
	logger.info("shoppingCartDto ",shoppingCartDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestResponse success = new RestResponse(true, "Successful", "Cart Payment initiated");
		ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);

		for(ShoppingCartItemDto item: shoppingCartDto.getItems()) {
System.out.println("item is "+item.getItemId());
						if(item.getType().equals("Publication")) {
				publicationService.mapUserToPublication(userService.getUserByEmail(shoppingCartDto.getUserId()).getId(),item.getItemId());
			}
		}

		return result;

	}
@RequestMapping("/updatePublicationStatus")
public ResponseEntity<RestResponse> updatePublicationStatus(@RequestBody ShoppingCartDto shoppingCartDto)
		throws ApplicationException {
logger.info("shoppingCartDto ",shoppingCartDto);
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestResponse success = new RestResponse(true, "Successful", "Cart Payment completed");
	ResponseEntity<RestResponse> result = new ResponseEntity<>(success, headers, HttpStatus.OK);

	for(ShoppingCartItemDto item: shoppingCartDto.getItems()) {
System.out.println("item is "+item.getItemId());
					if(item.getType().equals("Publication")) {
			publicationService.saveUserToPublicationStatus(userService.getUserByEmail(shoppingCartDto.getUserId()).getId(),item.getItemId());
		}
	}

	return result;

}


	@RequestMapping("/getSbuList")
	public ResponseEntity<RestResponse> getSbuList(){
		ResponseEntity<RestResponse> response = null;
		try{
			Map mapResponse = new HashMap();
			
			com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			User user = userDetail.getUser();
			mapResponse.put("sbu", user.getSbu());
			
			//Collection<String> sbuList = commonService.getSbuList();
			//mapResponse.put("sbuList", sbuList);
			mapResponse.put("sbuList", Arrays.asList(user.getSbu()));
			
			RestResponse restRes = new RestResponse(true,"success",mapResponse);
			response = new ResponseEntity<RestResponse>(restRes,HttpStatus.OK);
		}catch(Exception e){
			RestResponse restRes = new RestResponse(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse>(restRes,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/getUserThemeClass", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<String>> getUserThemeClass(HttpServletRequest request){
		ResponseEntity<RestResponse<String>> response = null;

		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDetail.getUser();

		if(user == null){
			RestResponse<String> result = new RestResponse<String>(false, "Session expired, please relogin.", null);
			response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.BAD_REQUEST);
		}else{
			ThemePk pk = new ThemePk();
			pk.setUserId(user.getId());
			pk.setSbu(user.getSbu());

			try {
				ThemeValue theme = (ThemeValue) genericService.findEntity(new ThemeValue(), pk);

				String gtnClass = null;

				if(theme == null){
					gtnClass = "gtn-yellow";
				}else{
					gtnClass = theme.getGtnClass();
				}

				RestResponse<String> result = new RestResponse<String>(true, "Success", gtnClass);
				response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.OK);
			} catch (ApplicationException e) {
				RestResponse<String> result = new RestResponse<String>(false, e.getMessage(), null);
				response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}

		return response;
	}

	@RequestMapping(value = "/updateUserThemeClass", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<String>> updateUserThemeClass(HttpServletRequest request){
		ResponseEntity<RestResponse<String>> response = null;

		String gtnClassUser = request.getParameter("gtnClass");

		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDetail.getUser();

		if(user == null){
			RestResponse<String> result = new RestResponse<String>(false, "Session expired, please relogin.", null);
			response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.BAD_REQUEST);
		}else if(gtnClassUser == null){
			RestResponse<String> result = new RestResponse<String>(false, "Invalid input.", null);
			response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.BAD_REQUEST);
		}else{
			ThemePk pk = new ThemePk();
			pk.setUserId(user.getId());
			pk.setSbu(user.getSbu());

			try {
				ThemeValue theme = (ThemeValue) genericService.findEntity(new ThemeValue(), pk);

				String gtnClass = null;

				if(theme == null){
					theme = new ThemeValue();
					theme.setDefaultClass("gtn-yellow");
					theme.setGtnClass(gtnClassUser);
					theme.setSbu(user.getSbu());
					theme.setUserId(user.getId());

					genericService.saveEntity(theme);
				}else{
					theme.setGtnClass(gtnClassUser);

					genericService.updateEntity(theme);
				}

				RestResponse<String> result = new RestResponse<String>(true, "Success", gtnClassUser);
				response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.OK);
			} catch (ApplicationException e) {
				RestResponse<String> result = new RestResponse<String>(false, e.getMessage(), null);
				response = new ResponseEntity<RestResponse<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}

		return response;
	}

	@RequestMapping(value = "/getDashBoardLHSMenu", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<List<MenuItemDto>>> getDashBoardLHSMenu(){
		ResponseEntity<RestResponse<List<MenuItemDto>>> response = null;
		DashBoardHelper helper = new DashBoardHelper();

		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		Collection<Product> subscribedProducts = null;
		
		try{
			Subscription subscription = subscriptionService.getSubscriptionTransact(new Long(user.getId()).longValue());
			logger.info("subscription  " , subscription);
	
			subscribedProducts = subscriptionService.getSubscriptionProductsActive(subscription.getSubscriptionId());
			logger.info("subscription products " , subscribedProducts.size());
		}catch(ApplicationException e){
			subscribedProducts = new ArrayList<Product>();
			//RestResponse<List<MenuItemDto>> result = new RestResponse<List<MenuItemDto>>(false, e.getMessage(), null);
			//response = new ResponseEntity<RestResponse<List<MenuItemDto>>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			//e.printStackTrace();
			//return response;
		}catch(Exception e){
			RestResponse<List<MenuItemDto>> result = new RestResponse<List<MenuItemDto>>(false, e.getMessage(), null);
			response = new ResponseEntity<RestResponse<List<MenuItemDto>>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			return response;
		}
		
		
		try{
			Collection<MenuValue> menuList = menuService.getMenuList(user);
			List<MenuItemDto> menuTree = helper.prepareLHSDashboardMenu(menuList, subscribedProducts);
			
			RestResponse<List<MenuItemDto>> result = new RestResponse<List<MenuItemDto>>(true, "Successful", menuTree);
			response = new ResponseEntity<RestResponse<List<MenuItemDto>>>(result, HttpStatus.OK);
		}catch(ApplicationException e){			
			RestResponse<List<MenuItemDto>> result = new RestResponse<List<MenuItemDto>>(false, e.getMessage(), null);
			response = new ResponseEntity<RestResponse<List<MenuItemDto>>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@RequestMapping(value = "/getCartInfo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<SubscriptionDto>> getCartInfo(){
		ResponseEntity<RestResponse<SubscriptionDto>> response = null;
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userDetail.getUser();
		
		try{			
			Subscription subscription = subscriptionService.getSubscriptionFromUser(new Long(user.getId()).longValue());			
			Collection c = subscriptionService.getUserCart(subscription.getSubscriptionId());
			
			if(c.size() > 0){
				Object[] row = (Object[]) c.iterator().next();
				
				BigDecimal amount = (BigDecimal) row[0];
				Long itemCount = (Long) row[1];
				
				SubscriptionDto obj = new SubscriptionDto();
				
				if(amount!=null)
					obj.setTotalCost(amount);
				else
					obj.setTotalCost(new BigDecimal(0.00));
					
				obj.setNoOfUsers(itemCount);
				
				RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successful", obj);
				response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);
				
				
			}else{
				SubscriptionDto subscriptionEmpty = new SubscriptionDto();
				subscriptionEmpty.setTotalCost(new BigDecimal(0.0));
				
				RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successfull", subscriptionEmpty);
				response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);
				
			}
			System.out.println(c);
			
		}catch(ApplicationException e){
			/*RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(false, e.getMessage(), null);
			response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.INTERNAL_SERVER_ERROR);*/
			
			SubscriptionDto subscriptionEmpty = new SubscriptionDto();
			subscriptionEmpty.setTotalCost(new BigDecimal(0.0));
			
			RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successfull", subscriptionEmpty);
			response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);
		//	e.printStackTrace();
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/getPurchasedProducts", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<SubscriptionDto>> getPurchasedProducts(){
		ResponseEntity<RestResponse<SubscriptionDto>> response = null;
		SubscriptionDto obj = new SubscriptionDto();
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userDetail.getUser();
		
		try{			
			Subscription subscription = subscriptionService.getSubscriptionFromUser(new Long(user.getId()).longValue());			
			Collection<Product> c = subscriptionService.getUserPurchasedProducts(subscription.getSubscriptionId());
			List<ProductDto> products = null;
			
			if(c.size() > 0){
				products = new ArrayList<ProductDto>(c.size());
				
				obj.setNoOfUsers(subscription.getNoOfUsers());
				
				for(Product p : c){
					ProductDto pdto = new ProductDto();
					
					pdto.setDisplayName(p.getDisplayName());
					pdto.setCost(p.getCost());
					pdto.setCurrencyCode(p.getCurrencyCode());
					
					products.add(pdto);
				}
				
				obj.setProducts(products);
				
				RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successful", obj);
				response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);				
				
			}else{
				RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successfull", new SubscriptionDto());
				response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);
				
			}
			System.out.println(c);
			
		}catch(ApplicationException e){
			/*RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(false, e.getMessage(), null);
			response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.INTERNAL_SERVER_ERROR);*/
			
			SubscriptionDto subscriptionEmpty = new SubscriptionDto();
			subscriptionEmpty.setTotalCost(new BigDecimal(0.0));
			
			RestResponse<SubscriptionDto> result = new RestResponse<SubscriptionDto>(true, "Successfull", subscriptionEmpty);
			response = new ResponseEntity<RestResponse<SubscriptionDto>>(result, HttpStatus.OK);
			
			e.printStackTrace();
		}
		
		return response;
	}
	
}
