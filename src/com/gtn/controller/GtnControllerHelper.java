package com.gtn.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtn.dto.CaseDto;
import com.gtn.dto.CaseLogDto;
import com.gtn.dto.MarkDto;
import com.gtn.dto.PartnerUrlDetailDto;
import com.gtn.dto.PaymentGatewayDto;
import com.gtn.dto.ProductDto;
import com.gtn.dto.PublicationDto;
import com.gtn.dto.SubscriptionDto;
import com.gtn.dto.UserBillingDto;
import com.gtn.dto.UserDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Case;
import com.gtn.model.CaseLog;
import com.gtn.model.Mark;
import com.gtn.model.PartnerUrlDetail;
import com.gtn.model.Product;
import com.gtn.model.Publication;
import com.gtn.model.Subscription;
import com.gtn.model.User;
import com.gtn.model.UserBilling;
import com.gtn.util.CardType;
import com.gtn.util.ValidationUtil;

@Repository("gtnControllerHelper")
public class GtnControllerHelper {

	public ProductDto parseProduct(Product product) {

		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setCost(product.getCost());
		productDto.setCurrencyCode(product.getCurrencyCode());
		productDto.setDescription(product.getDescription());
		productDto.setDiscountPercentage(product.getDiscountPercentage());
		productDto.setDisplayName(product.getDisplayName());
		productDto.setLevel(product.getLevel());
		productDto.setStatus(product.getStatus());
		
		productDto.setLabelText("ADD TO CART");
		productDto.setAddStatus("1");
		return productDto;
	}

	public User parseInput(UserDto dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmailId(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setPassword(dto.getPassword());
		return user;
	}
	
	public UserDto prepareUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmailId());
		userDto.setCompany(user.getCompanyName());
		userDto.setId(""+user.getId());

		userDto.setPhone(user.getPhone());
		return userDto;
	}
	public UserBilling parseBillingInfo(PaymentGatewayDto dto) throws ApplicationException {
		try {
			UserBilling userBilling = new UserBilling();
			userBilling.setFirstName(dto.getBillingname());
			userBilling.setCardNo(new Long(dto.getCcNumber()));
			userBilling.setAdd_1(dto.getAdd_1());
			userBilling.setAdd_2(dto.getAdd_2());
			userBilling.setCity(dto.getCard_city());
			userBilling.setCountry_code(dto.getCard_country());
			userBilling.setPrimary(dto.getPrimary());
			 DateFormat dateFormat= new SimpleDateFormat("MM/yy");
			System.out.println("card expiry "+dto.getExpiry());
			userBilling.setExp_date((Date)(dateFormat.parse(dto.getExpiry())));
			userBilling.setZipcode(Integer.parseInt(dto.getCard_zip()));
			System.out.println("userBilling@@@@@@@@@ "+userBilling);
			return userBilling;
		}
		catch(Exception e)
		{
			throw new ApplicationException(e.getMessage(),e);
		}

	}
	public SubscriptionDto prepareSubscriptionDto(Subscription subscription) {
		SubscriptionDto subDto = new SubscriptionDto ();
		subDto.setNoOfCountries(subscription.getNoOfCountries());
		subDto.setNoOfUsers(subscription.getNoOfUsers());
		subDto.setTotalCost(subscription.getTotalCost());
		subDto.setTotalCurrencyCode(subscription.getCurrencyCode());
		subDto.setSubscriptionId(""+subscription.getSubscriptionId());
		//subDto.setUserName(subscription.getUserName());
		return subDto;
	}
	public CaseDto populateCaseDto(Case caseModel, User createdBy, User assignedTo,List<CaseLogDto> caseLogDtos,List<Map<String, Object>> productList){
		CaseDto caseDto = new CaseDto();
		caseDto.setTicketId(caseModel.getTicketId());
		caseDto.setDescription(caseModel.getDescription());
		caseDto.setCategory(caseModel.getCategory());
		caseDto.setPriority(caseModel.getPriority());
		caseDto.setCreatedOn(caseModel.getCreatedOn());
		caseDto.setCreatedByName(createdBy.getFirstName()+ " " + createdBy.getLastName());
		if(assignedTo != null)
			caseDto.setAssignedToName(assignedTo.getFirstName() + " " +assignedTo.getLastName() );
		caseDto.setStatus(caseModel.getStatus());
		caseDto.setMessages(caseLogDtos);
		for(Map<String, Object> item : productList ){
			ArrayList<ProductDto> subProducts = (ArrayList<ProductDto>) item.get("subproduct");
			for(ProductDto subProduct : subProducts){
				if (subProduct.getProductId() == caseModel.getCategory()) {
					caseDto.setSelectedProduct(item);
					caseDto.setSelectedSubProduct(subProduct);
				}
			}
		}
		return caseDto;		
	}

	public UserBillingDto prepareBillingDto(UserBilling billing) throws Exception{
		UserBillingDto userBillingDto = new UserBillingDto();
		userBillingDto.setAdd_1(billing.getAdd_1());
		userBillingDto.setAdd_2(billing.getAdd_2());
		userBillingDto.setCardNo(billing.getCardNo());
		userBillingDto.setCardType((CardType.detect(""+billing.getCardNo())).toString());
		userBillingDto.setCity(billing.getCity());
		userBillingDto.setCountry_code(billing.getCountry_code());
		 DateFormat dateFormat= new SimpleDateFormat("MM/yy");
			System.out.println("card expiry "+billing.getExp_date());
		//	userBilling.setExp_date((Date)(dateFormat.parse(dto.getExpiry())));
		
		userBillingDto.setExp_date(dateFormat.format(billing.getExp_date()));
		userBillingDto.setFirstName(billing.getFirstName());
		userBillingDto.setLastName(billing.getLastName());
		userBillingDto.setPrimary(billing.getPrimary());
		userBillingDto.setState(billing.getState());
		userBillingDto.setZipcode(billing.getZipcode());
		userBillingDto.setId(billing.getId());
		userBillingDto.setUserId(billing.getUserId());
		return userBillingDto;
	}

	public CaseLogDto populareCaseLogDto(CaseLog caseLog, String messageByName){
		CaseLogDto caseLogDto = new CaseLogDto();
		caseLogDto.setTicketId(caseLog.getTicketId());
		caseLogDto.setMessage(caseLog.getMessage());
		caseLogDto.setMessageOn(caseLog.getMessageOn());
		caseLogDto.setMessageByName(messageByName);
		return caseLogDto;
	}

	public Case populateCase(CaseDto caseDto, Case caseModel,User user) throws ApplicationException {
		if (caseModel.getTicketId() > 0) {
			caseModel.setDescription(caseDto.getDescription());
			caseModel.setCategory(caseDto.getCategory());
			caseModel.setPriority(caseDto.getPriority());
			if (isEmpty(caseDto.getStatus()))
				caseModel.setStatus("O");
			else
				caseModel.setStatus(caseDto.getStatus());
			if("S".equalsIgnoreCase(user.getUserType()) && caseModel.getAssignedTo()<=0){
				caseModel.setAssignedTo(user.getId());
				caseModel.setAssignedOn(new Date());
			}
		} else {
			caseModel.setTicketId(caseDto.getTicketId());
			caseModel.setDescription(caseDto.getDescription());
			caseModel.setCategory(caseDto.getCategory());
			caseModel.setPriority(caseDto.getPriority());
			caseModel.setCreatedBy(user.getId());
			caseModel.setCreatedOn(new Date());
			if (isEmpty(caseDto.getStatus()))
				caseModel.setStatus("O");
			else
				caseModel.setStatus(caseDto.getStatus());
		}
		return caseModel;
	}


/*	public CaseLog populateCaseLog(CaseLogDto caseLogDto, long messageBy) throws ApplicationException {
		try {
			CaseLog caseLog = new CaseLog();
			caseLog.setTicketId(caseLogDto.getTicketId());
			caseLog.setMessage(caseLogDto.getMessage());
			caseLog.setMessageBy(messageBy);
			if (caseLogDto.getMessageOn() == null)
				caseLog.setMessageOn(new Date());
			else {
				SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
				caseLog.setMessageOn(sdfr.parse(caseLogDto.getMessageOn()));
			}
			return caseLog;
		} catch (ParseException e) {
			throw new ApplicationException("System Error", e);
		}
	}*/
	
	public boolean isEmpty(Object object) {

		if (object == null)
			return true;

		if(object instanceof Collection)
			return ((Collection)object).isEmpty();
		
		if (object instanceof String)
			return (((String) object).trim().length() > 0) ? false : true;

		return false;
	}
		public PublicationDto preparePublicationDto(Publication publication) {
		PublicationDto publicationDto = new PublicationDto();
		publicationDto.setAuthor(publication.getAuthor());
		publicationDto.setDescription(publication.getDescription());
		publicationDto.setPrice(publication.getPrice());
		publicationDto.setPublicationType(publication.getPublicationType());
		publicationDto.setPublishDate(publication.getPublishDate());
		publicationDto.setTitle(publication.getTitle());
		publicationDto.setPubUrl(""+publication.getPubUrl());
		publicationDto.setCurrency(publication.getCurrency());
		
		System.out.println("id11111111111111  "+publication.getId());
		publicationDto.setId(publication.getId());
		return publicationDto;
	}

	public MarkDto prepareMarkDto(Mark mark) {
		MarkDto markdto = new MarkDto();
		markdto.setAuthor(mark.getAuthor());
		markdto.setCollapsed(mark.getCollapsed()==1? "true" : "false");
		markdto.setColor(mark.getColor());
		markdto.setDatechanged(mark.getDatechanged());
		markdto.setDatecreated(mark.getDatecreated());
		markdto.setDisplayFormat(mark.getDisplayFormat());
		markdto.setDocument_filename(mark.getDocument_filename());
		markdto.setDocument_relative_path(mark.getDocument_relative_path());
		markdto.setHas_selection(mark.getHas_selection()==1? "true" : "false");
		markdto.setHeight(""+mark.getHeight());
		markdto.setId(mark.getId());
		markdto.setNote(mark.getNote());
		markdto.setPageIndex(""+mark.getPageIndex());
		markdto.setPoints(mark.getPoints());
		markdto.setPositionX(""+mark.getPositionX());
		markdto.setPositionY(""+mark.getPositionY());
		markdto.setReadonly(mark.getReadonly()==1? "true" : "false");
		markdto.setSelection_info(mark.getSelection_info());
		markdto.setSelection_text(mark.getSelection_text());
		markdto.setSelection_text(mark.getSelection_text());
		markdto.setWidth(""+mark.getWidth());
		markdto.setType(mark.getType());
		return markdto;
	}
	
	public PartnerUrlDetailDto populatePartnerUrlDetailDto(PartnerUrlDetail partnerUrlDetail){
		PartnerUrlDetailDto partnerUrlDetailDto = new PartnerUrlDetailDto();
		partnerUrlDetailDto.setId(partnerUrlDetail.getId());
		partnerUrlDetailDto.setPartnerName(partnerUrlDetail.getPartnerName());
		partnerUrlDetailDto.setApiName(partnerUrlDetail.getApiName());
		partnerUrlDetailDto.setUrl(partnerUrlDetail.getUrl());
		return partnerUrlDetailDto;
	}
	
	public PartnerUrlDetail populatePartnerUrlDetail(PartnerUrlDetailDto partnerUrlDetailDto){
		PartnerUrlDetail partnerUrlDetail = new PartnerUrlDetail();
		partnerUrlDetail.setId(partnerUrlDetail.getId());
		partnerUrlDetail.setPartnerName(partnerUrlDetail.getPartnerName());
		partnerUrlDetail.setApiName(partnerUrlDetail.getApiName());
		partnerUrlDetail.setUrl(partnerUrlDetail.getUrl());
		return partnerUrlDetail;
	}

	/*public SecurityParamDto populateSecurityParamDto(SecurityParam securityParam){
		SecurityParamDto securityParamDto = new SecurityParamDto();
		securityParamDto.setAccount(securityParam.getAccount());
		securityParamDto.setAccountNumber(securityParam.getAccountNumber());
		securityParamDto.setBillTo(securityParam.getBillTo());
		securityParamDto.setBillToAddressLine1(securityParam.getBillToAddressLine1());
		securityParamDto.setBillToAddressLine2(securityParam.getBillToAddressLine2());
		securityParamDto.setBillToCityCode(securityParam.getBillToCityCode());
		securityParamDto.setBillToCityName(securityParam.getBillToCityName());
		securityParamDto.setBillToContactFName(securityParam.getBillToContactFName());
		securityParamDto.setBillToContactLName(securityParam.getBillToContactLName());
		securityParamDto.setBillToContactMName(securityParam.getBillToContactMName());
		securityParamDto.setBillToCountryCode(securityParam.getBillToCountryCode());
		securityParamDto.setBillToCountryName(securityParam.getBillToCountryName());
		securityParamDto.setBillToEmail(securityParam.getBillToEmail());
		securityParamDto.setBillToName(securityParam.getBillToName());
		securityParamDto.setBillToPhone(securityParam.getBillToPhone());
		securityParamDto.setBillToStateCode(securityParam.getBillToStateCode());
		securityParamDto.setBillToStateName(securityParam.getBillToStateName());
		securityParamDto.setBillToZipcode(securityParam.getBillToZipcode());
		securityParamDto.setId(securityParam.getId());
		securityParamDto.setLicenseNumber(securityParam.getLicenseNumber());
		securityParamDto.setMeterNumber(securityParam.getMeterNumber());
		securityParamDto.setPartnerName(securityParam.getPartnerName());
		securityParamDto.setPassword(securityParam.getPassword());
		securityParamDto.setShipperAddressLine1(securityParam.getShipperAddressLine1());
		securityParamDto.setShipperAddressLine2(securityParam.getShipperAddressLine2());
		securityParamDto.setShipperCityCode(securityParam.getShipperCityCode());
		securityParamDto.setShipperCityName(securityParam.getShipperCityName());
		securityParamDto.setShipperContactFName(securityParam.getShipperContactFName());
		securityParamDto.setShipperContactLName(securityParam.getShipperContactLName());
		securityParamDto.setShipperContactMName(securityParam.getShipperContactMName());
		securityParamDto.setShipperCountryCode(securityParam.getShipperCountryCode());
		securityParamDto.setShipperCountryName(securityParam.getShipperCountryName());
		securityParamDto.setShipperEmail(securityParam.getShipperEmail());
		securityParamDto.setShipperName(securityParam.getShipperName());
		securityParamDto.setShipperPhone(securityParam.getShipperPhone());
		securityParamDto.setShipperStateCode(securityParam.getShipperStateCode());
		securityParamDto.setShipperStateName(securityParam.getShipperStateName());
		securityParamDto.setShipperZipcode(securityParam.getShipperZipcode());
		securityParamDto.setSubCode(securityParam.getSubCode());
		securityParamDto.setThirdPartAccNo(securityParam.getThirdPartAccNo());
		securityParamDto.setUserName(securityParam.getUserName());
		return securityParamDto;
	}
	
	public SecurityParam populateSecurityParam(SecurityParamDto securityParamDto){
		SecurityParam secParam = new SecurityParam();
		secParam.setAccount(securityParamDto.getAccount());
		secParam.setAccountNumber(securityParamDto.getAccountNumber());
		secParam.setBillTo(securityParamDto.getBillTo());
		secParam.setBillToAddressLine1(securityParamDto.getBillToAddressLine1());
		secParam.setBillToAddressLine2(securityParamDto.getBillToAddressLine2());
		secParam.setBillToCityCode(securityParamDto.getBillToCityCode());
		secParam.setBillToCityName(securityParamDto.getBillToCityName());
		secParam.setBillToContactFName(securityParamDto.getBillToContactFName());
		secParam.setBillToContactLName(securityParamDto.getBillToContactLName());
		secParam.setBillToContactMName(securityParamDto.getBillToContactMName());
		secParam.setBillToCountryCode(securityParamDto.getBillToCountryCode());
		secParam.setBillToCountryName(securityParamDto.getBillToCountryName());
		secParam.setBillToEmail(securityParamDto.getBillToEmail());
		secParam.setBillToName(securityParamDto.getBillToName());
		secParam.setBillToPhone(securityParamDto.getBillToPhone());
		secParam.setBillToStateCode(securityParamDto.getBillToStateCode());
		secParam.setBillToStateName(securityParamDto.getBillToStateName());
		secParam.setBillToZipcode(securityParamDto.getBillToZipcode());
		secParam.setId(securityParamDto.getId());
		secParam.setLicenseNumber(securityParamDto.getLicenseNumber());
		secParam.setMeterNumber(securityParamDto.getMeterNumber());
		secParam.setPartnerName(securityParamDto.getPartnerName());
		secParam.setPassword(securityParamDto.getPassword());
		secParam.setShipperAddressLine1(securityParamDto.getShipperAddressLine1());
		secParam.setShipperAddressLine2(securityParamDto.getShipperAddressLine2());
		secParam.setShipperCityCode(securityParamDto.getShipperCityCode());
		secParam.setShipperCityName(securityParamDto.getShipperCityName());
		secParam.setShipperContactFName(securityParamDto.getShipperContactFName());
		secParam.setShipperContactLName(securityParamDto.getShipperContactLName());
		secParam.setShipperContactMName(securityParamDto.getShipperContactMName());
		secParam.setShipperCountryCode(securityParamDto.getShipperCountryCode());
		secParam.setShipperCountryName(securityParamDto.getShipperCountryName());
		secParam.setShipperEmail(securityParamDto.getShipperEmail());
		secParam.setShipperName(securityParamDto.getShipperName());
		secParam.setShipperPhone(securityParamDto.getShipperPhone());
		secParam.setShipperStateCode(securityParamDto.getShipperStateCode());
		secParam.setShipperStateName(securityParamDto.getShipperStateName());
		secParam.setShipperZipcode(securityParamDto.getShipperZipcode());
		secParam.setSubCode(securityParamDto.getSubCode());
		secParam.setThirdPartAccNo(securityParamDto.getThirdPartAccNo());
		secParam.setUserName(securityParamDto.getUserName());
		return secParam;
	}*/
	
	public List<String> validateResetPassword(UserDto userDto){
		List<String> validationError = new ArrayList<String>();
		
		if(!ValidationUtil.validateEmail(userDto.getEmail(), 50) )
			validationError.add("Enter a valid Email Id.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getOtp(), 8) )
			validationError.add("Enter a valid Otp.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getPassword(), 100) )
			validationError.add("Enter a valid password.");
		
		return validationError;
	}
	
	public List<String> validateUserSignUp(UserDto userDto){
		List<String> validationError = new ArrayList<String>();
		if(!ValidationUtil.ValidateMandatoryString(userDto.getFirstName(), 50) )
			validationError.add("Enter a valid First Name.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getLastName(), 50) )
			validationError.add("Enter a valid Last Name.");
		if(!ValidationUtil.validateEmail(userDto.getEmail(), 50) )
			validationError.add("Enter a valid Email Id.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getPassword(), 50) )
			validationError.add("Enter a valid Password.");
		if(!ValidationUtil.ValidateMandatoryNumericString(userDto.getPhone(), 20) )
			validationError.add("Enter a valid Phone.");
		return validationError;
	}
	
	public List<String> validateAdditionalUserSignUp(UserDto userDto){
		List<String> validationError = new ArrayList<String>();
		if(!ValidationUtil.ValidateMandatoryString(userDto.getFirstName(), 50) )
			validationError.add("Enter a valid First Name.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getLastName(), 50) )
			validationError.add("Enter a valid Last Name.");
		if(!ValidationUtil.validateEmail(userDto.getEmail(), 50) )
			validationError.add("Enter a valid Email Id.");
		return validationError;
	}
	public List<String> validateCase(CaseDto caseDto){
		List<String> validationError = new ArrayList<String>();
		if(!ValidationUtil.ValidateMandatoryString(caseDto.getDescription(), 1000) )
			validationError.add("Enter a valid Description.");
		/*if((caseDto.getCategory()<= 0) )
			validationError.add("Enter a Category.");*/		
		if(!ValidationUtil.ValidateMandatoryString(caseDto.getPriority(), 1) )
			validationError.add("Enter a valid Priority.");
		if(!ValidationUtil.ValidateMandatoryString(caseDto.getNewMessage(), 1000) )
			validationError.add("Enter a valid Message.");
		return validationError;
	}
	
	public List<String> validateAdditionalUser(UserDto userDto){
		List<String> validationError = new ArrayList<String>();
		if(!ValidationUtil.ValidateMandatoryString(userDto.getFirstName(), 50) )
			validationError.add("Enter a valid First Name.");
		if(!ValidationUtil.ValidateMandatoryString(userDto.getLastName(), 50) )
			validationError.add("Enter a valid Last Name.");
		if(!ValidationUtil.validateEmail(userDto.getEmail(), 50) )
			validationError.add("Enter a valid Email Id.");
		return validationError;
	}
	
}
