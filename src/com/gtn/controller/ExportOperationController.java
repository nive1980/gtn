package com.gtn.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gtn.configuration.ConnectionUtil;
import com.gtn.dto.DplAuditDto;
import com.gtn.dto.DplAuditReasonView;
import com.gtn.dto.GridResponseDto;
import com.gtn.dto.ProductsDto;
import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.ShipmentCartonDto;
import com.gtn.dto.ShipmentDocEmailDto;
import com.gtn.dto.ShipmentDto;
import com.gtn.dto.ShipmentItemDto;
import com.gtn.dto.ShipmentScreeningResponse;
import com.gtn.dto.ValidationErrorDto;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.enums.ShipmentEaseStatus;
import com.gtn.exception.ApplicationException;
import com.gtn.exception.ApplicationRuntimeException;
import com.gtn.helper.AESDataValidation;
import com.gtn.helper.ExportOperationHelper;
import com.gtn.helper.MasterHelper;
import com.gtn.model.AesStatusHistory;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.Country;
import com.gtn.model.CurrencyValue;
import com.gtn.model.DplAuditValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ModeOfTransportValue;
import com.gtn.model.PortsValue;
import com.gtn.model.ProductValue;
import com.gtn.model.RestResponse;
import com.gtn.model.Shipment;
import com.gtn.model.ShipmentCarton;
import com.gtn.model.ShipmentDocs;
import com.gtn.model.ShipmentItem;
import com.gtn.model.State;
import com.gtn.model.User;
import com.gtn.service.CountryService;
import com.gtn.service.ExportOperationService;
import com.gtn.service.GenericService;
import com.gtn.service.LicenceScreeningService;
import com.gtn.service.MasterService;
import com.gtn.service.WatchListScreeningService;
import com.gtn.util.Constants;
import com.gtn.util.EmailUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
public class ExportOperationController extends GenericController{
	
	@Autowired
	private ExportOperationService service;
	
	@Autowired
	private WatchListScreeningService watchListScreeningService;
	
	@Autowired
	private LicenceScreeningService licenseScreening;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AESDataValidation aesValidator;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private ConnectionUtil conUtil;
	
	@Autowired
	private GenericService genericService;

	private static final Logger logger = LoggerFactory.getLogger(ExportOperationController.class);
	
	/*@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<RestResponse> bindingResultHandler(HttpServletRequest req,MethodArgumentNotValidException e){
		ResponseEntity<RestResponse> response = null;
		
		 List<ObjectError> errors = e.getBindingResult().getAllErrors();
		
		return response;
	}*/
	
	/**
	 * Save Shipment
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveShipment", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveShipment(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		ExportOperationHelper helper = new ExportOperationHelper();
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		
		if(ExportOperationHelper.isEmpty(shipment.getShipmentNo())){
			String lastNo = service.getNextShipmentNo(user.getSbu());
			String nextShipNo = helper.getNextShipNo(lastNo, user.getSbu());
			
			shipment.setShipmentNo(nextShipNo);
			
		}
		
		try{			
			shipment.setSbuCode(user.getSbu());
			shipment.setCreatedBy(userDetail.getUsername());
			shipment.setCreatedOn(new Date());
			shipment.setEaseStatus(ShipmentEaseStatus.PENDING.getType());
			
			service.saveShipment(shipment);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
			
			HttpSession session = request.getSession();
			session.setAttribute("shipmentId", shipment.getId());
		}catch(ApplicationRuntimeException e){
				RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
				result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.ALREADY_REPORTED);
		}
		catch(Exception e){
			try{
				RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getCause().getMessage(),null);
				result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}catch(Exception eInner){
				RestResponse<Shipment> error = new RestResponse<Shipment>(false,eInner.getCause().getMessage(),null);
				result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return result;
	}
	
	/**
	 * Get all shipments in table
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/getShipments", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Collection<Shipment>>> getAllShipment(HttpServletRequest request) throws JAXBException, IOException{
		ResponseEntity<RestResponse<Collection<Shipment>>> result = null;
		String sbu = getUserSbu();
		try{
			Collection<Shipment> shipments = service.getAllShipment(sbu);
			RestResponse<Collection<Shipment>> success = new RestResponse<Collection<Shipment>>(true,"Successful",shipments);
			result = new ResponseEntity<RestResponse<Collection<Shipment>>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Collection<Shipment>> error = new RestResponse<Collection<Shipment>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<Shipment>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * Remove shipment from db
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/removeShipment", method = RequestMethod.GET)
	public ResponseEntity removeShipment(HttpServletRequest request) throws JAXBException, IOException, ApplicationException{
	
		String shipmentId = request.getParameter("shipmentId");
		try{			
			//Shipment shipment = service.getShipment(shipmentNo);
			
			service.removeShipment(Integer.parseInt(shipmentId));
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	/**
	 * Search a shipment
	 * @param view
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/searchShipments", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Collection<Shipment>>> searchShipments(@RequestBody ShipmentDto view, HttpServletRequest request) throws JAXBException, IOException{
	
		ResponseEntity<RestResponse<Collection<Shipment>>> result = null;
		try{
			Collection<Shipment> shipments = service.searchShipment(view);
			RestResponse<Collection<Shipment>> success = new RestResponse<Collection<Shipment>>(true,"Successful",shipments);
			result = new ResponseEntity<RestResponse<Collection<Shipment>>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Collection<Shipment>> error = new RestResponse<Collection<Shipment>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<Shipment>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;	
			
	}
	
	@RequestMapping(value = "/searchShipmentsPagination", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<GridResponseDto>> searchShipmentsPagination(@RequestBody ShipmentDto view, HttpServletRequest request) throws JAXBException, IOException{
	
		ResponseEntity<RestResponse<GridResponseDto>> result = null;
		ExportOperationHelper helper = new ExportOperationHelper();
		/*ShipmentDto searchView = (ShipmentDto) getSessionMap().get("shipmentSearchView");
		Long count = (Long) getSessionMap().get("resultCountShipmentSearch");*/
		
		try{
			String sbu = getUserSbu();
			view.setSbuCode(sbu);
			Long count = service.searchShipmentCount(view);
			Collection<Shipment> shipments = service.searchShipmentPagination(view);
			
			Collection<ShipmentDto> views = helper.parseShipmentOp(shipments);
			
			GridResponseDto gridResponse = new GridResponseDto();
			
			gridResponse.setResults(views);
			gridResponse.setTotalResultCount(count);
			
			RestResponse<GridResponseDto> success = new RestResponse<GridResponseDto>(true,"successful",gridResponse);
			result = new ResponseEntity<RestResponse<GridResponseDto>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<GridResponseDto> error = new RestResponse<GridResponseDto>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<GridResponseDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;	
			
	}
	
	/**
	 * Find a shipment by id
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/findShipment", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Shipment>> findShipment(HttpServletRequest request) throws JAXBException, IOException{
		String id = request.getParameter("id");
		String mode = request.getParameter("mode");
		
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			Shipment shipment = service.findShipment(Integer.parseInt(id));
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
			
			if(!isEmpty(mode) && "edit".equals(mode)){
				HttpSession session = request.getSession();
				session.setAttribute("shipmentId", id);
			}
			
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	
	@RequestMapping(value = "/findShipmentByNo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Shipment>> findShipmentByNo(HttpServletRequest request) throws JAXBException, IOException{
		String shipmentNo = request.getParameter("shipmentNo");
		String mode = request.getParameter("mode");
		
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(ExportOperationHelper.isEmpty(shipmentNo)){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			Shipment shipment = service.getShipment(shipmentNo);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
			
			if(!isEmpty(mode) && "edit".equals(mode)){
				HttpSession session = request.getSession();
				session.setAttribute("shipmentId", shipment.getId());
			}
			
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	

	
	@RequestMapping(value = "/getShipmentFromSession", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Shipment>> getShipmentFromSession(HttpServletRequest request) throws JAXBException, IOException{
		
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		try{
			HttpSession session = request.getSession();
			int id = (Integer) session.getAttribute("shipmentId");
			if(id <= 0){
				RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Shipment not found in session",null);
				result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.NOT_FOUND);
				return result;
			}
			
			Shipment shipment = service.findShipment(id);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
						
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	@RequestMapping(value = "/getDropDown", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Map>> setDropDown(HttpServletRequest request) throws ApplicationException, JSONException{
		
		Map map = new HashMap<>();
		RestResponse<Map> response = null;
		
		String countryCode = request.getParameter("countryCode");
		
		Collection<Country> countries = countryService.getCountries();
		map.put("countries", countries);
		
		if(!ExportOperationHelper.isEmpty(countryCode)){
			Collection<State> states = masterService.getStates(countryCode, "");
			map.put("states", states);
		}
				
		response = new RestResponse<Map>(true, "success", map);
		
		return new ResponseEntity<RestResponse<Map>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Save exporter details 
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveExporter", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveExporter(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){		
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateExporter(shipment);
		
		if(validator.size() > 0){
			shipment.setMsgs(validator);
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Server side validation fail",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		
		Shipment exporter, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		exporter = helper.prepareExporter(shipmentDb, shipment);
		exporter.setModifiedOn(new Date());
		exporter.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(exporter);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
		//return new ResponseEntity<Shipment>(shipment, HttpStatus.OK);
	}
	
	/**
	 * Save Ultimate consignee details 
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveUltConsignee", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveUltConsignee(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateUltConsignee(shipment);
		
		if(validator.size() > 0){
			shipment.setMsgs(validator);
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Server side validation fail",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		
		Shipment ultConsignee, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
		
		boolean screenReq = helper.screeningReq(shipmentDb, shipment);
				
		ultConsignee = helper.prepareUltConsignee(shipmentDb, shipment);
		helper.populateShiBillTo(ultConsignee);
		ultConsignee.setModifiedOn(new Date());
		ultConsignee.setModifiedBy(getUserName());
		
		try{
			
			if(screenReq){
				ScreeningEntity screeningEntity = helper.prepareWlsScreeningInp(ultConsignee);
				WlsScreeningResponse response = helper.doWatchListScreening(screeningEntity, watchListScreeningService);
				//WlsScreeningResponse response = deserialzeAddressJDK7("C:\\Users\\lenovo\\Desktop\\GTN\\wlsScreeningResponse.txt");
				
				//response.getHttsReturned().setHits(0);
				
				if(response.getHttsReturned().getHits() > 0){
					ultConsignee.setScreeningStatus("H");				
					shipment.setScreeningStatus("H");
					shipment.setStatus("H");
					ultConsignee.setStatus("H");	
				}else{
					ultConsignee.setScreeningStatus("A");
					shipment.setScreeningStatus("A");
					shipment.setStatus("P");
					ultConsignee.setStatus("H");	
				}
				ultConsignee.setScreenedOn(new Date());
				System.out.println("Screening done ----------------------- > ");
			}
			
			service.saveShipment(ultConsignee);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error in entity screening : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	private WlsScreeningResponse deserialzeAddressJDK7(String filename) {

		WlsScreeningResponse address = null;

		try (ObjectInputStream ois
			= new ObjectInputStream(new FileInputStream(filename))) {

			address = (WlsScreeningResponse) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return address;
	}
	
	
	
	
	/**
	 * Save Intermedite consignee details
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveInterConsignee", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveInterConsignee(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateInterConsignee(shipment);
		
		if(validator.size() > 0){
			shipment.setMsgs(validator);
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Server side validation fail",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}		
		
		Shipment interConsignee, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		interConsignee = helper.prepareInterConsignee(shipmentDb, shipment);
		interConsignee.setModifiedOn(new Date());
		interConsignee.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(interConsignee);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Save Freight forwarder address
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveFreightForwarder", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveFreightForwarder(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateFreightFw(shipment);
		
		if(validator.size() > 0){
			shipment.setMsgs(validator);
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Server side validation fail",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}	
		
		
		Shipment freughtFwConsignee, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		freughtFwConsignee = helper.prepareFreightForwader(shipmentDb, shipment);
		freughtFwConsignee.setModifiedOn(new Date());
		freughtFwConsignee.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(freughtFwConsignee);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Save shipment billing details
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveShipmentBilling", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveShipmentBilling(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateBilling(shipment);
		
		if(validator.size() > 0){
			shipment.setMsgs(validator);
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Server side validation fail",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
				
		
		Shipment shipmentBilling, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		shipmentBilling = helper.prepareShipmentBilling(shipmentDb, shipment);
		shipmentBilling.setModifiedOn(new Date());
		shipmentBilling.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(shipmentBilling);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Get shipment items
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getShipmentItems", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<ShipmentItemDto>>> getShipmentItems(HttpServletRequest request){		
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<Collection<ShipmentItemDto>>> result = null;
		ExportOperationHelper helper = new ExportOperationHelper();
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<Collection<ShipmentItemDto>> error = new RestResponse<Collection<ShipmentItemDto>>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentItemDto>>>(error, HttpStatus.BAD_REQUEST);
		}
		
		try{
			Collection<ShipmentItem> items = service.getShipmentItem(Integer.parseInt(id));
			helper.updateItemStatus(items);
			Collection<ShipmentItemDto> views = new ArrayList<ShipmentItemDto>();
			
			for(ShipmentItem item: items){
				ShipmentItemDto view = new ShipmentItemDto();
				
				BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
				
				BeanUtils.copyProperties(view, item);
				
				view.setDelete(item.getId()+"");
				view.setCopy(item.getId()+"");
				
				views.add(view);
			}
			
			RestResponse<Collection<ShipmentItemDto>> success = new RestResponse<Collection<ShipmentItemDto>>(true,"Successful",views);
			result = new ResponseEntity<RestResponse<Collection<ShipmentItemDto>>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Collection<ShipmentItemDto>> error = new RestResponse<Collection<ShipmentItemDto>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentItemDto>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	/**
	 * Get shipment cartons
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getShipmentCartons", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<ShipmentCartonDto>>> getShipmentCartons(HttpServletRequest request){		
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<Collection<ShipmentCartonDto>>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<Collection<ShipmentCartonDto>> error = new RestResponse<Collection<ShipmentCartonDto>>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentCartonDto>>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			
			Collection<ShipmentCarton> cartons = service.getShipmentCarton(Integer.parseInt(id));
			
			Collection<ShipmentCartonDto> views = new ArrayList<ShipmentCartonDto>();
			
			for(ShipmentCarton carton: cartons){
				ShipmentCartonDto view = new ShipmentCartonDto();
				BeanUtils.copyProperties(view, carton);
				
				view.setDelete(carton.getId()+"");
				view.setCopy(carton.getId()+"");
				
				views.add(view);
			}
			
			
			RestResponse<Collection<ShipmentCartonDto>> success = new RestResponse<Collection<ShipmentCartonDto>>(true,"Successful",views);
			result = new ResponseEntity<RestResponse<Collection<ShipmentCartonDto>>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Collection<ShipmentCartonDto>> error = new RestResponse<Collection<ShipmentCartonDto>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentCartonDto>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	/**
	 * get next item from table
	 * @param shipmentId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNextItemNo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Integer>> getNextItemNo(@RequestParam int shipmentId, HttpServletRequest request){
		ResponseEntity<RestResponse<Integer>> result = null;
		
		if(shipmentId <= 0){			
			RestResponse<Integer> error = new RestResponse<Integer>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Integer>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			int itemNo = service.getNextItemNo(shipmentId);
			itemNo++;
			RestResponse<Integer> success = new RestResponse<Integer>(true,"Successful",itemNo);
			result = new ResponseEntity<RestResponse<Integer>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Integer> error = new RestResponse<Integer>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Integer>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Save shipment item
	 * @param item
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ApplicationException 
	 */
	@RequestMapping(value = "/saveItem", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ShipmentItem>> saveItem(@RequestBody ShipmentItem item, HttpServletRequest request) throws IOException{

		ResponseEntity<RestResponse<ShipmentItem>> result = null;
		ExportOperationHelper helper = new ExportOperationHelper();
		Shipment shipment = null;
		//server side validation
		List<String> validator = ExportOperationHelper.validateItem(item);
		
		if(validator.size() > 0){
			item.setMsgs(validator);
			RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"Server side validation fail",item);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		
		try{				
			 shipment = service.findShipment(item.getShipmentId());
		}catch(ApplicationException e){			
			RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"Shipment not found.",item);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
		
		
		if(item.getId() == 0){
			try{				
				shipment = helper.updateShipmentLsStatus(shipment, item, licenseScreening, service);
			
			}catch(ApplicationException e){
				e.printStackTrace();
				/*RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"License Screening fail",item);
				result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				return result;*/
			}
		}else{
			if(item.getScreenType() == null){
				//screening wasnt done while created new
				try{				
					shipment = helper.updateShipmentLsStatus(shipment, item, licenseScreening, service);
				}catch(ApplicationException e){
					e.printStackTrace();
					/*RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"License Screening fail",item);
					result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
					return result;*/
				}
			}else{				
				ShipmentItem itemDb = null;
				try{				
					itemDb = service.findEntity(new ShipmentItem(), item.getId());
				}catch(ApplicationException e){			
					RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"Shipment Item not found.",item);
					result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.NOT_FOUND);
					return result;
				}
				
				
				String lastScreenType = item.getScreenType();
				
				boolean rescreen = false;
				
				if(lastScreenType.equalsIgnoreCase("product no") && !itemDb.getPartNo().equals(item.getPartNo())){
					rescreen = true;
				}else if(lastScreenType.equalsIgnoreCase("eccn") && !itemDb.getEccn().equals(item.getEccn())){
					rescreen = true;
				}else if(lastScreenType.equalsIgnoreCase("usml") && !itemDb.getUsmlCategory().equals(item.getUsmlCategory())){
					rescreen = true;
				}
				
				if(rescreen){
					try{				
						shipment = helper.updateShipmentLsStatus(shipment, item, licenseScreening, service);
					}catch(ApplicationException e){
						e.printStackTrace();
						/*RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"License Screening fail",item);
						result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
						return result;*/
					}
				}
			}
		}
		
		/*if(shipment.getLsStatus() == null || "Yes".equals(shipment.getLsStatus())){
			shipment.setStatus("H");
		}*/
		
		try{
			service.saveShipmentItem(item);
			RestResponse<ShipmentItem> success = new RestResponse<ShipmentItem>(true,"Successful",item);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		shipment.setModifiedOn(new Date());
		shipment.setModifiedBy(getUserName());
		String lsStatus = null;
		try{
			lsStatus = updateShipmentLsStatus(shipment);
			shipment.setLsStatus(lsStatus);
			service.saveShipment(shipment);
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		
		item.setShipLsStatus(lsStatus);
		
		return result;
	}
	
	private String updateShipmentLsStatus(Shipment shipment) throws ApplicationException{
				//update shipment ls status
				Collection<ShipmentItem> items = service.getShipmentItem(shipment.getId());
				
				if(items == null || items.size() == 0){
					
					return null;
				}
				
				boolean oneY = false;
				boolean allN = true;
				
				for(ShipmentItem item: items){
					if(!isEmpty(item.getLicReq())){
						if("Y".equals(item.getLicReq()) && isEmpty(item.getLicenseNo())){
							oneY = true;
						}
						if(!"N".equals(item.getLicReq()) && ("Y".equals(item.getLicReq()) && isEmpty(item.getLicenseNo()))){
							allN = false;
						}
					}
				}
				
				if(oneY){
					return "Y";
				}else if(allN){
					return "N";
				}else{
					return "NA";
				}
				
				
				/*String lsStatus = null;
				boolean allNull = true;
				boolean allNOrNa = true;
				
				for(ShipmentItem dbItem: items){
					if(!isEmpty(dbItem.getLicReq())){
						allNull = false;
						if("Y".equals(dbItem.getLicReq())){
							lsStatus = "Y";
						}
						if(!"N".equals(dbItem.getLicReq()) && !"NA".equals(dbItem.getLicReq())){
							allNOrNa = false;
						}
					}
				}
				
				if(allNull){
					shipment.setLsStatus(null);
				}
				
				if(lsStatus == null && allNOrNa){
					lsStatus = "N";
				}
				
				if(lsStatus!=null){
					shipment.setLsStatus(lsStatus);
				}
				
				//update shipment with LS status.
				service.saveShipment(shipment);*/
	}

	
	/**
	 * Delete shipment item
	 * @param itemId
	 * @param request
	 * @throws ApplicationException 
	 */
	@RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
	public ResponseEntity deleteItem(@RequestParam int itemId, HttpServletRequest request) {
		ResponseEntity response = null;
		Shipment shipment = null;
		try{
			ShipmentItem item = (ShipmentItem) genericService.read(ShipmentItem.class, itemId);
			shipment = (Shipment) genericService.read(Shipment.class, item.getShipmentId());
			
			service.deleteItem(itemId);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String lsStatus = null;
		try{
			
			lsStatus = updateShipmentLsStatus(shipment);
			shipment.setLsStatus(lsStatus);
			service.updateEntity(shipment);
		}catch(ApplicationException e){
			String error = "Error updating shipment status.";
			RestResponse<String> restResponse = new RestResponse<String>(false, "fail", null);
			ResponseEntity res = new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			return res;
		}
		
		RestResponse<String> restResponse = new RestResponse<String>(true, "success", lsStatus);
		return new ResponseEntity<>(restResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/findItem", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<ShipmentItem>> findItem(HttpServletRequest request) throws JAXBException, IOException{
		String id = request.getParameter("itemId");
		ResponseEntity<RestResponse<ShipmentItem>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			ShipmentItem item = (ShipmentItem) service.findEntity(new ShipmentItem(), Integer.parseInt(id));
			RestResponse<ShipmentItem> success = new RestResponse<ShipmentItem>(true,"Successful",item);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ShipmentItem> error = new RestResponse<ShipmentItem>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<ShipmentItem>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	
	/////////// carton start /////////////
	
	@RequestMapping(value = "/getNextCartonNo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Integer>> getNextCartonNo(@RequestParam int shipmentId, HttpServletRequest request){
		ResponseEntity<RestResponse<Integer>> result = null;
		
		if(shipmentId <= 0){			
			RestResponse<Integer> error = new RestResponse<Integer>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Integer>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			int cartonNo = service.getNextCartonNo(shipmentId);
			cartonNo++;
			RestResponse<Integer> success = new RestResponse<Integer>(true,"Successful",cartonNo);
			result = new ResponseEntity<RestResponse<Integer>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Integer> error = new RestResponse<Integer>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Integer>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/findCarton", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<ShipmentCarton>> findCarton(HttpServletRequest request) throws JAXBException, IOException{
		String id = request.getParameter("cartonId");
		ResponseEntity<RestResponse<ShipmentCarton>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<ShipmentCarton> error = new RestResponse<ShipmentCarton>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			ShipmentCarton carton = (ShipmentCarton) service.findEntity(new ShipmentCarton(), Integer.parseInt(id));
			RestResponse<ShipmentCarton> success = new RestResponse<ShipmentCarton>(true,"Successful",carton);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ShipmentCarton> error = new RestResponse<ShipmentCarton>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	
	@RequestMapping(value = "/deleteCarton", method = RequestMethod.GET)
	public ResponseEntity deleteCarton(@RequestParam int cartonId, HttpServletRequest request) {
		ResponseEntity response = null;
		try{
			service.deleteCarton(cartonId);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Save shipment carton
	 * @param carton
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveCarton", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ShipmentCarton>> saveCarton(@RequestBody ShipmentCarton carton, HttpServletRequest request){

		ResponseEntity<RestResponse<ShipmentCarton>> result = null;
		
		//server side validation
		List<String> validator = ExportOperationHelper.validateCarton(carton);
		
		if(validator.size() > 0){
			carton.setMsgs(validator);
			RestResponse<ShipmentCarton> error = new RestResponse<ShipmentCarton>(false,"Server side validation fail",carton);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(error, HttpStatus.EXPECTATION_FAILED);
			return result;
		}
		
		try{
			service.saveShipmentCarton(carton);
			RestResponse<ShipmentCarton> success = new RestResponse<ShipmentCarton>(true,"Successful",carton);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ShipmentCarton> error = new RestResponse<ShipmentCarton>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<ShipmentCarton>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Empty save for list item/carton screen
	 * @param shipment
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveListItemCarton", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveListItemCarton(@RequestBody Shipment shipment, HttpServletRequest request){

		ResponseEntity<RestResponse<Shipment>> result = null;
		
		try{
			//service.saveShipmentCarton(carton);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",null);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
	
	/**
	 * Get shipment cartons
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getShipmentDocs", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<ShipmentDocs>>> getShipmentDocs(HttpServletRequest request){		
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<Collection<ShipmentDocs>>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<Collection<ShipmentDocs>> error = new RestResponse<Collection<ShipmentDocs>>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			Collection<ShipmentDocs> docs = service.getShipmentDocs(Integer.parseInt(id));
			RestResponse<Collection<ShipmentDocs>> success = new RestResponse<Collection<ShipmentDocs>>(true,"Successful",docs);
			result = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Collection<ShipmentDocs>> error = new RestResponse<Collection<ShipmentDocs>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	/**
	 * Upload shipment document
	 * @throws IOException 
	 * @throws ApplicationException 
	 */
	@RequestMapping(value = "/uploadShipmentDoc", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Collection<ShipmentDocs>>> uploadShipmentDoc(@RequestParam("file") MultipartFile file, @RequestParam("shipmentId") int shipmentId, HttpServletRequest request) throws IOException, ApplicationException{		
		ResponseEntity<RestResponse<Collection<ShipmentDocs>>> response = null;
		
		ShipmentDocs doc = new ShipmentDocs();
		Shipment shipment = service.findShipment(shipmentId);
		
		//String path = request.getServletContext().getRealPath("/");
		String catalinaBase = System.getProperty( "catalina.base" );
		
		if(file!=null && shipment != null){
			doc.setFileName(file.getOriginalFilename());
			doc.setCreatedOn(new Date());
			doc.setShipmentId(shipment.getId());
			
			byte[] bytes = file.getBytes();
			
			String rootDir = catalinaBase+File.separator+"webapps"+File.separator+"ROOT";
							
			File dir = new File(rootDir + File.separator + "shipmentDocs" + File.separator +shipment.getShipmentNo());
			
			if (!dir.exists())
                 dir.mkdirs();
			
			File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			if(serverFile.exists() && !serverFile.isDirectory()) {
				RestResponse<Collection<ShipmentDocs>> error = new RestResponse<Collection<ShipmentDocs>>(false,"File already present.",null);
				response = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(error, HttpStatus.BAD_REQUEST);
				return response;
                //serverFile.delete();
            }
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
            try{
            	//use SBU
            	doc.setFilePath(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            	service.saveEntity(doc);
            	
            	Collection<ShipmentDocs> docs = service.getShipmentDocs(shipment.getId());
            	RestResponse<Collection<ShipmentDocs>> success = new RestResponse<Collection<ShipmentDocs>>(true,"Successful",docs);
            	response = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(success,HttpStatus.OK);
            }catch(Exception e){
            	RestResponse<Collection<ShipmentDocs>> error = new RestResponse<Collection<ShipmentDocs>>(false,e.getMessage(),null);
            	response = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
            }
		}else{
			RestResponse<Collection<ShipmentDocs>> error = new RestResponse<Collection<ShipmentDocs>>(false,"File not found.",null);
			response = new ResponseEntity<RestResponse<Collection<ShipmentDocs>>>(error, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/removeDocument", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<String>> removeDocument(HttpServletRequest request) throws JAXBException, IOException, NumberFormatException, ApplicationException{
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<String>> response = null;
		
		if(ExportOperationHelper.isEmpty(id)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<String>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}		
		
		service.removeDocument(Integer.parseInt(id));
		RestResponse<String> success = new RestResponse<String>(true,"Successful","Deleted successfully");
		
		return new ResponseEntity<RestResponse<String>>(success, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ApplicationException, IOException{
		String id = request.getParameter("id");
		
		if(ExportOperationHelper.isEmpty(id)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid object ID",null);
			return;
			//response = new ResponseEntity<RestResponse<String>>(error, HttpStatus.BAD_REQUEST);
			//return response;
		}
		
		ShipmentDocs doc = service.findEntity(new ShipmentDocs(), Integer.parseInt(id));
		String fileName;
		String filePath;
		
		File file = new File(doc.getFilePath());
		
		if(file.exists()){
			InputStream inStrm = new FileInputStream(file);
			filePath = doc.getFilePath();
			fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
			
			final String agent = request.getHeader("USER-AGENT");
			
			if (agent != null && agent.indexOf("MSIE") != -1) {// For IE
				fileName = URLEncoder.encode(fileName, "UTF8");
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + doc.getFileName());
			} else if (agent != null && agent.indexOf("Mozilla") != -1) {// For
																			// FireFox,
																			// Chrome,
																			// Safari
				response.setCharacterEncoding("UTF-8");
				fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
				response.setContentType("application/force-download");
				response.addHeader("Content-Disposition",
						"attachment; filename=\"" + doc.getFileName() + "\"");
			}
			
			final ServletOutputStream out = response.getOutputStream();
			
			final byte bytes[] = new byte[32768];
			int index = inStrm.read(bytes, 0, 32768);
			while (index != -1) {
				out.write(bytes, 0, index);
				index = inStrm.read(bytes, 0, 32768);
			}
			inStrm.close();
			out.flush();
		}
	}
	
	
	
	/**
	 * Save document screen
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveShipmentDocument", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveShipmentDocument(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Shipment shipDoc, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		shipDoc = helper.prepareShipDoc(shipmentDb, shipment);
		shipDoc.setModifiedOn(new Date());
		shipDoc.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(shipDoc);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * Save Booking and Custom filings
	 * @param shipment
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveShipmentBookingCustom", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Shipment>> saveShipmentBookingCustom(@RequestBody Shipment shipment, HttpServletRequest request) throws JAXBException, IOException{
		
		ExportOperationHelper helper = new ExportOperationHelper();
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(shipment.getId() <= 0){			
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Shipment shipBookCustom, shipmentDb = null;
		
		try{
			shipmentDb = service.findShipment(shipment.getId());
			if(shipmentDb == null){
				throw new ApplicationException("Not found", new Exception());
			}
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Error getting shipment : "+e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.NOT_FOUND);
			return result;
		}
				
		shipBookCustom = helper.prepareBookingCustom(shipmentDb, shipment);
		shipBookCustom.setModifiedOn(new Date());
		shipBookCustom.setModifiedBy(getUserName());
		
		try{
			service.saveShipment(shipBookCustom);
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/updateDocFileType", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<String>> updateDocFileType(HttpServletRequest request) throws NumberFormatException, ApplicationException{
		
		String id = request.getParameter("id");
		String fileType = request.getParameter("fileType");
		
		ResponseEntity<RestResponse<String>> response = null;
		
		if(ExportOperationHelper.isEmpty(id)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<String>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		ShipmentDocs doc = service.findEntity(new ShipmentDocs(), Integer.parseInt(id));
		
		doc.setFileType(fileType);
		
		service.updateEntity(doc);
		
		return response;
	}
	
	
	@RequestMapping(value= "/validateShipment", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<List<String>>> validateShipment(HttpServletRequest request){
		ResponseEntity<RestResponse<List<String>>> response = null;
		ExportOperationHelper helper = new ExportOperationHelper();
		
		String shipmentId = request.getParameter("shipmentId");
		
		if(ExportOperationHelper.isEmpty(shipmentId)){
			RestResponse<List<String>> error = new RestResponse<List<String>>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<List<String>>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}
		Shipment shipment = null;
		try{
			shipment = service.findShipment(Integer.parseInt(shipmentId));
		}catch(ApplicationException e){
			RestResponse<List<String>> error = new RestResponse<List<String>>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<List<String>>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		List<String> op = helper.validateShipment(shipment);
		
		if(op.size() == 0){
			shipment.setStatus(Constants.VALIDATED);
			try {
				service.saveShipment(shipment);
				
				RestResponse<List<String>> success = new RestResponse<List<String>>(true,"success",op);
				response = new ResponseEntity<RestResponse<List<String>>>(success, HttpStatus.OK);
				
			} catch (ApplicationException | ApplicationRuntimeException e) {
				e.printStackTrace();
				RestResponse<List<String>> error = new RestResponse<List<String>>(false,e.getMessage(),null);
				response = new ResponseEntity<RestResponse<List<String>>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
		}else{
			RestResponse<List<String>> success = new RestResponse<List<String>>(true,"success",op);
			response = new ResponseEntity<RestResponse<List<String>>>(success, HttpStatus.OK);
		}
		
		return response;
	}
	
	@RequestMapping(value="/getCurrency", method=RequestMethod.GET)
	public Collection<CurrencyValue> getCurrency(HttpServletRequest request) throws ApplicationException{
		List<CurrencyValue> currencies = new ArrayList<CurrencyValue>();
		
		String param = request.getParameter("req");
		
		Collection<CurrencyValue> currency = service.getCurrency(param);
				
		return currency;
	}
	
	@RequestMapping(value="/getMot", method=RequestMethod.GET)
	public Collection<ModeOfTransportValue> getMot(HttpServletRequest request) throws ApplicationException{
		List<ModeOfTransportValue> currencies = new ArrayList<ModeOfTransportValue>();
		
		String param = request.getParameter("req");
		
		Collection<ModeOfTransportValue> mot = service.getModeOfTransport(param);
				
		return mot;
	}
	
	@RequestMapping(value="/getShipmentStatus", method=RequestMethod.GET)
	public ResponseEntity<String> updateShipmentStatus(HttpServletRequest request)
	{
		String status = request.getParameter("status");
		
		String statusDesc = ExportOperationHelper.getStatus(status);		
		
		return new ResponseEntity<String>(statusDesc, HttpStatus.OK);
	}
	
	
	/**
	 * This method is used to get Shipment AES status
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getShipmentAesStatus", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>> getShipmentAesStatus(HttpServletRequest request){		
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<Map<String, List<ValidationErrorDto>>> error = new RestResponse<Map<String, List<ValidationErrorDto>>>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>>(error, HttpStatus.BAD_REQUEST);
		}
		
		try{
			
			Shipment shipment = service.findShipment(Integer.parseInt(id));
			Map<String, List<ValidationErrorDto>> results = aesValidator.validateAesFilingUI(shipment, "");
			
			RestResponse<Map<String, List<ValidationErrorDto>>> success = new RestResponse<Map<String, List<ValidationErrorDto>>>(true,"Successful",results);
			result = new ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>>(success,HttpStatus.OK);
		}catch(NullPointerException e){
			RestResponse<Map<String, List<ValidationErrorDto>>> error = new RestResponse<Map<String, List<ValidationErrorDto>>>(false,"Nullpointerexception raised in AES Validations.",null);
			result = new ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e){
			RestResponse<Map<String, List<ValidationErrorDto>>> error = new RestResponse<Map<String, List<ValidationErrorDto>>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Map<String, List<ValidationErrorDto>>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}

	@RequestMapping(value="getExporters", method=RequestMethod.GET)
	public Collection<ExporterValue> getExporters(HttpServletRequest request){
		String param = request.getParameter("req");
		
		try{
			Collection<ExporterValue> exporters = service.getExporter(param);
			return exporters;
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="getConsignees", method=RequestMethod.GET)
	public Collection<ConsigneeValue> getConsignees(HttpServletRequest request){
		String param = request.getParameter("req");
		
		try{
			Collection<ConsigneeValue> consigness = service.getConsignee(param);
			return consigness;
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="/getPorts", method=RequestMethod.GET)
	public Collection<PortsValue> getPorts(HttpServletRequest request) throws ApplicationException{
		
		String param = request.getParameter("req");
		String country = request.getParameter("country");
		
		Collection<PortsValue> ports = service.getPorts(param, country);
				
		return ports;
	}
		
	
	@RequestMapping(value = "/getShipmentAesStatusHistory", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<AesStatusHistory>>> getShipmentAesStatusHistory(HttpServletRequest request){		
		String shipmentNo = request.getParameter("id");
		ResponseEntity<RestResponse<Collection<AesStatusHistory>>> result = null;
		
		if(ExportOperationHelper.isEmpty(shipmentNo)){			
			RestResponse<Collection<AesStatusHistory>> error = new RestResponse<Collection<AesStatusHistory>>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Collection<AesStatusHistory>>>(error, HttpStatus.BAD_REQUEST);
		}
		
		try{
			Collection<AesStatusHistory> results = service.getShipmentAesStatusHistory(shipmentNo);			
			RestResponse<Collection<AesStatusHistory>> success = new RestResponse<Collection<AesStatusHistory>>(true,"Successful",results);
			result = new ResponseEntity<RestResponse<Collection<AesStatusHistory>>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<Collection<AesStatusHistory>> error = new RestResponse<Collection<AesStatusHistory>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<AesStatusHistory>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	
	/**
	 * Cancel a shipment by id
	 * @param request
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@RequestMapping(value = "/cancelShipment", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Shipment>> cancelShipment(HttpServletRequest request) throws JAXBException, IOException{
		String id = request.getParameter("id");
		
		ResponseEntity<RestResponse<Shipment>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){		
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<Shipment>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			Shipment shipment = service.findShipment(Integer.parseInt(id));
			
			shipment.setStatus("C");
			service.updateEntity(shipment);
			
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",shipment);
			result = new ResponseEntity<RestResponse<Shipment>>(success,HttpStatus.OK);						
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Shipment>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
	
	
	private static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	private String getUserName(){
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		return userDetail.getUsername();
	}
	private String getUserSbu(){
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		return user.getSbu();
	}
	
	
	
	/****
	 * Jasper report requests
	 * @throws JRException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ApplicationException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getShipmentReport", method = RequestMethod.GET)
	public String getShipmentReport(HttpServletRequest req, HttpServletResponse res) throws JRException, ClassNotFoundException, SQLException, NumberFormatException, ApplicationException, IOException{
		String shipId = req.getParameter("shipId");
		String type = req.getParameter("type");
		
		Shipment shipment = service.findShipment(Integer.parseInt(shipId));
		JasperPrint print = ExportOperationHelper.getReportOp(shipId,type,req, conUtil);
		
		//String s = req.getServletContext().getRealPath("/jasper reports/packingListReport.jrxml");
		
		/*//Compile jrxml file.
		jasperReport = JasperCompileManager
	               .compileReport("C:\\Users\\lenovo\\Desktop\\jasper reports\\packingListReport.jrxml");
		
		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("shipId", Integer.parseInt(shipId));
		
		Connection conn = ConnectionUtil.getSQLServerConnection_SQLJDBC();
		
		JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);*/
		
		String fileName = ExportOperationHelper.getFileName(type, shipment.getShipmentNo());
		
		res.setContentType("application/force-download");
	    res.setHeader("Content-disposition", "inline; filename=\""+fileName+ "\"");
	    
	    final OutputStream outStream = res.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(print, outStream);
        
        /*// PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "C:\\Users\\lenovo\\Desktop\\jasper reports\\FirstJasperReport.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();*/
 
        System.out.print("Done!");
		
		return "Done!";
	}

	@RequestMapping(value="sendDocEmail", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ShipmentDocEmailDto>> sendDocEmail(@RequestBody ShipmentDocEmailDto emailDto, HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException, JRException, SQLException{
		ResponseEntity<RestResponse<ShipmentDocEmailDto>> response = null;
		
		if(ExportOperationHelper.isEmpty(emailDto.getDocType()) || emailDto.getShipId() == null){
			emailDto.setErrorMsr("Invalid shipment information provided.");
			RestResponse<ShipmentDocEmailDto> restRes = new RestResponse<ShipmentDocEmailDto>(false, "Missing info", emailDto);
			response = new ResponseEntity<RestResponse<ShipmentDocEmailDto>>(restRes, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		List<String> validationResult = ExportOperationHelper.validateDocEmail(emailDto);
		
		if(validationResult.size() > 0){
			emailDto.setValidationErrors(validationResult);
			RestResponse<ShipmentDocEmailDto> restRes = new RestResponse<ShipmentDocEmailDto>(false, "Server side validation fail.", emailDto);
			response = new ResponseEntity<RestResponse<ShipmentDocEmailDto>>(restRes, HttpStatus.EXPECTATION_FAILED);
			return response;
		}
		
		String[] emails = emailDto.getTo().split(",");
		
		//trim all emails
		Arrays.parallelSetAll(emails, (i) -> emails[i].trim());

		String fileName = ExportOperationHelper.getFileName(emailDto.getDocType(), emailDto.getShipmentNo());
		JasperPrint print = ExportOperationHelper.getReportOp(emailDto.getShipId()+"",emailDto.getDocType(),req, conUtil);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, baos);
		DataSource aAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		
		emailDto.setMessage(emailDto.getMessage().replaceAll("(\r\n|\n)", "<br />"));
		
		for(String s: emails){
			try{
				emailUtil.sendMailHtmlWithAttachement("naveen.ara@gmail.com", s, emailDto.getSubject(), emailDto.getMessage(),
						fileName, aAttachment);
			}catch(Exception e){
				RestResponse<ShipmentDocEmailDto> restRes = new RestResponse<ShipmentDocEmailDto>(false, e.getMessage(), emailDto);
				response = new ResponseEntity<RestResponse<ShipmentDocEmailDto>>(restRes, HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
		}
		
		RestResponse<ShipmentDocEmailDto> restRes = new RestResponse<ShipmentDocEmailDto>(true, "Email successfully sent.", emailDto);
		response = new ResponseEntity<RestResponse<ShipmentDocEmailDto>>(restRes, HttpStatus.OK);
		return response;
	}
	
	
	@RequestMapping(value="performWlsScreening", method=RequestMethod.GET)
	public ResponseEntity<RestResponse<ShipmentScreeningResponse>> performWlsScreening(HttpServletRequest request){
		ResponseEntity<RestResponse<ShipmentScreeningResponse>> response = null;
		
		String shipmentId = request.getParameter("shipmentId");
		String type = request.getParameter("type");
		
		ShipmentScreeningResponse wlsResponse = new ShipmentScreeningResponse();
		WlsScreeningResponse screeningResponse = new WlsScreeningResponse();
		
		ExportOperationHelper helper = new ExportOperationHelper();
		
		if(ExportOperationHelper.isEmpty(shipmentId)){
			//emailDto.setErrorMsr("Invalid shipment information provided.");
			
			RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, "Invalid shipment id.", wlsResponse);
			response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.BAD_REQUEST);
			return response;
		}
		Shipment shipment = null;
		
		try {
			shipment = service.findShipment(Integer.parseInt(shipmentId));
		} catch (NumberFormatException | ApplicationException e) {
			RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, "Shipment not found.", wlsResponse);
			response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		if("ultConsignee".equals(type)){
			if(ExportOperationHelper.isEmpty(shipment.getUltConsigneeName())){
				RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, "Ultimate Consignee name is missing.", wlsResponse);
				response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.NOT_FOUND);
				return response;
			}else{
				//prepare request
				ScreeningEntity entity = helper.prepareScreeningEntity(shipment, type);
				
				String responseStr = null;
				
				try{
					responseStr = watchListScreeningService.doWatchListScreening(entity);
				}catch(ApplicationException | IOException | JAXBException  e){
					RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, e.getMessage(), wlsResponse);
					response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.INTERNAL_SERVER_ERROR);
					return response;
				}
				//WlsScreeningResponse responseObj = null;
				
				try{
					screeningResponse = watchListScreeningService.translateXmlResponse(responseStr);
				}catch(Exception e){
					RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, e.getMessage(), wlsResponse);
					response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.INTERNAL_SERVER_ERROR);
					return response;
				}
				
			}
		}else{
			RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, "Invalid screening type.", wlsResponse);
			response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		wlsResponse.setWlsResponse(screeningResponse);
		wlsResponse.setOldStatus(shipment.getScreeningStatus());
		
		/**
		 * get shipment consignee audit
		 */
		
		try {
			Collection<DplAuditValue> audits = service.getDplAudit(shipment.getId()+"", "SHIPMENT", "CONSIGNEE");
			Collection<DplAuditDto> auditsView = null;
			MasterHelper masterHelper = new MasterHelper();
			
			if(audits!=null){
				auditsView = masterHelper.prepareAudits(audits);				
				
			}else{
				auditsView = new ArrayList<DplAuditDto>();
			}
			
			wlsResponse.setAudits(auditsView);
			
		} catch (ApplicationException e) {
			e.printStackTrace();
			
			RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(false, e.getMessage(), wlsResponse);
			response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
			
		}
				
		RestResponse<ShipmentScreeningResponse> restRes = new RestResponse<ShipmentScreeningResponse>(true, "Successful", wlsResponse);
		response = new ResponseEntity<RestResponse<ShipmentScreeningResponse>>(restRes, HttpStatus.OK);
		return response;
	}
	
	
	@RequestMapping(value = "/updateShipmentDplAuditStatus", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<DplAuditDto>> updateDplAuditStatus(HttpServletRequest request, @RequestBody DplAuditReasonView view){
		ResponseEntity<RestResponse<DplAuditDto>> response = null;
		ExportOperationHelper helper = new ExportOperationHelper();
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		
		try{
				DplAuditValue auditValue = new DplAuditValue();
				
				Integer shipId = view.getShipmentId();
				
				if(shipId == null){
					RestResponse<DplAuditDto> restRes = new RestResponse<DplAuditDto>(false, "Invalid shipment id.", null);
					response = new ResponseEntity<RestResponse<DplAuditDto>>(restRes, HttpStatus.BAD_REQUEST);
					return response;
				}
				
				Shipment shipment = service.findShipment(shipId);
				
				//get next audit number
				Long auditNo = masterService.getDplAudit();
				
				auditValue.setDplAuditNo(auditNo);
				auditValue.setTablename("SHIPMENT");
				auditValue.setTablekey(shipment.getId()+"");
				auditValue.setOldStatus(shipment.getScreeningStatus());
				auditValue.setNewStatus(view.getNewStatus());
				auditValue.setOtherReason(view.getOtherReason());
				auditValue.setCreatedBy(userDetail.getUsername());
				auditValue.setCreatedOn(new Date());
				auditValue.setSbuCode(user.getSbu());
				auditValue.setEntityName("CONSIGNEE");
				auditValue.setPartyName(shipment.getUltConsigneeName());
				
				genericService.saveEntity(auditValue);
				
				shipment.setScreeningStatus(view.getNewStatus());
				service.updateEntity(shipment);
				
				/*if(view.getEntityType()!=null && "exporter".equals(view.getEntityType())){
					//get exporter
					ExporterValue exporter = (ExporterValue) genericService.findEntity(new ExporterValue(), view.getCode());
					
					if(exporter == null){
						RestResponse<DplAuditDto> error = new RestResponse<DplAuditDto>(false,"Invalid exporter.",null);
						response = new ResponseEntity<RestResponse<DplAuditDto>>(error,HttpStatus.BAD_REQUEST);
						return response;
					}
					
					//get next audit number
					Long auditNo = masterService.getDplAudit();
					auditValue = helper.prepareAudit("EXPORTER", userDetail, auditNo, exporter.getStatus(), view.getNewStatus(), view.getOtherReason(), exporter.getExpName(), exporter.getExpCode());
					genericService.saveEntity(auditValue);
					
					//update exporter status
					exporter.setStatus(view.getNewStatus());
					genericService.updateEntity(exporter);
				}else if(view.getEntityType()!=null && "consignee".equals(view.getEntityType())){
					//get consignee
					ConsigneeValue consignee = (ConsigneeValue) genericService.findEntity(new ConsigneeValue(), view.getCode());
					
					if(consignee == null){
						RestResponse<DplAuditDto> error = new RestResponse<DplAuditDto>(false,"Invalid consignee.",null);
						response = new ResponseEntity<RestResponse<DplAuditDto>>(error,HttpStatus.BAD_REQUEST);
						return response;
					}
					
					//get next audit number
					Long auditNo = masterService.getDplAudit();
					auditValue = helper.prepareAudit("CONSIGNEE", userDetail, auditNo, consignee.getStatus(), view.getNewStatus(), view.getOtherReason(), consignee.getConsigneeName(), consignee.getConsigneeId());
					genericService.saveEntity(auditValue);
					
					//update exporter status
					consignee.setStatus(view.getNewStatus());
					genericService.updateEntity(consignee);
				}
				
				
				//insert dpl reasons
				if(view.getReasonCodes()!=null && view.getReasonCodes().size() > 0){
					updateDplReasons(auditValue.getDplAuditNo(), view.getReasonCodes());
				}*/
				
				DplAuditDto auditDto = new DplAuditDto();
				auditDto.setNewStatus(view.getNewStatus());
				
				RestResponse<DplAuditDto> success = new RestResponse<DplAuditDto>(true,"success",auditDto);
				response = new ResponseEntity<RestResponse<DplAuditDto>>(success,HttpStatus.OK);
			
		}catch(ApplicationException e){
			e.printStackTrace();
			RestResponse<DplAuditDto> error = new RestResponse<DplAuditDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<DplAuditDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
				
		return response;
	}
	
	@RequestMapping(value="getProducts", method=RequestMethod.GET)
	public Collection<ProductsDto> getProducts(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException{
		String param = request.getParameter("req");
		
		
		
		try{
			Collection<ProductValue> products = service.getProducts(param, getUserSbu());
			Collection<ProductsDto> views = new ArrayList<ProductsDto>(products.size());
			
			for(ProductValue product: products){
				ProductsDto view = new ProductsDto();
				
				view.setPartNo(product.getPartNo());
				view.setPartDesc(product.getDescription());
				view.setExportClass(product.getExportClass());
				view.setCurrency(product.getCurrency());
				//BeanUtils.copyProperties(view, product);
				
				
				views.add(view);
			}
			
			return views;
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		return null;
	}

	
	
	//@Scheduled(fixedDelay = 5000)
    //@Scheduled(fixedRate = 5000)
    /*public void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }*/
}
