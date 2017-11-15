package com.gtn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.enums.ShipmentEaseStatus;
import com.gtn.exception.ApplicationException;
import com.gtn.helper.ExportOperationHelper;
import com.gtn.model.JobSchedular;
import com.gtn.model.RestResponse;
import com.gtn.model.Shipment;
import com.gtn.service.AesFilingService;
import com.gtn.service.ExportOperationService;
import com.gtn.service.GenericService;
import com.gtn.service.JobSchedularService;
import com.gtn.webservices.ShipmentAesFiling;
import com.gtn.webservices.dto.ShipmentAesFilingHelper;
import com.gtn.webservices.dto.ShipmentDetail;
import com.gtn.webservices.dto.ShipmentDetailsReq;
import com.gtn.webservices.dto.ShipmentDetailsRes;

@RestController
@EnableScheduling
public class WebserviceController implements SchedulingConfigurer{

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private ExportOperationService expoprtOpService;
	
	@Autowired
	private AesFilingService aesService;
	
	@Autowired
	private JobSchedularService jobService;
	
	private static final Logger logger = LoggerFactory.getLogger(WebserviceController.class);
	
	@RequestMapping(value = "createShipmentInEase", method = RequestMethod.GET)
	public ResponseEntity publishShipmentToEase(HttpServletRequest request) throws ApplicationException{
		ResponseEntity response = null;
		String id = request.getParameter("id");
				
		
		if(ExportOperationHelper.isEmpty(id)){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Invalid object ID",null);
			response = new ResponseEntity(error, HttpStatus.BAD_REQUEST);
			return response;
		}
				
		ShipmentAesFilingHelper helper = new ShipmentAesFilingHelper();
		Shipment shipment = null;
		
		try{
			shipment = (Shipment) genericService.findEntity(new Shipment(), Integer.parseInt(id));
			
		}catch(Exception e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Internal server error",null);
			response = new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}

		ShipmentAesFiling filingShipment = new ShipmentAesFiling();
		String res = null;
		
		try{
			res = filingShipment.fileShipmentToEase(shipment, expoprtOpService);
			shipment.setEaseStatus(ShipmentEaseStatus.SUBMITTED.getType());
			shipment.setEaseSubmitDate(new Date());
			
			RestResponse<Shipment> success = new RestResponse<Shipment>(true,"Successful",null);
			response = new ResponseEntity(success, HttpStatus.OK);
			
			genericService.updateEntity(shipment);
		}catch(IOException e){
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Internal server error",null);
			response = new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			RestResponse<Shipment> error = new RestResponse<Shipment>(false,"Internal server error",null);
			response = new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
			
		}
		
		System.out.println(" -------------------- Shipment filing response -------------------------- ");
		System.out.println(res);
		
		return response;
	}
	
	
	//@Scheduled(cron ="0 49 15 * * *")
	public void aesFilings() throws ApplicationException, IOException{
		
		System.out.println("************************************  cron called at : "+new Date());
		
		logger.info("********** Starting Read shipment from EASE Webservice :: "+new Date().toString()+" **********");
		ShipmentAesFilingHelper helper = new ShipmentAesFilingHelper();
		ShipmentAesFiling filingShipment = new ShipmentAesFiling();
		
		Collection<String> shipNos = aesService.getAesSubmmitedShipments();
		
		logger.info("********** Found shipments to process **********   "+shipNos);
		
		List<String> shipList = new ArrayList<String>(shipNos);
		
		ShipmentDetailsReq request = helper.getEaseShipmentRequest(shipList);
		

		ShipmentDetailsRes shipmentResponse = filingShipment.getShipmentFrmEase(request);
		logger.info("********** EASE get shipment response ********** "+shipmentResponse);
		
		Set<ShipmentDetail> output = helper.processShipmentResponse(shipmentResponse, expoprtOpService);
		
		if(output!=null){
			
			logger.info("********** AES Shipments updated on :: "+new Date().toString()+" ********** "+output);
			
			//update EASE response to GTN
			aesService.updatedAesResponse(output);
		}
		
		logger.info("********** End Read shipment from EASE Webservice :: "+new Date().toString()+"**********");
		
		System.out.println(shipmentResponse);
	}
	
	 private String cronConfig() throws ApplicationException {
		 JobSchedular job = jobService.getCron("AES_FILING");
		 return job.getCron();
     }

	 /**
	  * Schedule AES Filing job
	  */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {		
		try {			
			taskRegistrar.addTriggerTask(() ->{
				try {
					aesFilings();
				} catch (ApplicationException e) {
					logger.error("Error running AES Job : "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("Error running AES Job : "+e.getMessage());
					e.printStackTrace();
				}
			}, (triggerContext) -> {
	                String cron = null;
					try {
						cron = cronConfig();
					} catch (ApplicationException e) {
						e.printStackTrace();
					}
	                logger.info("Scheduling next AES run : "+cron);
	                CronTrigger trigger = new CronTrigger(cron);
	                Date nextExec = trigger.nextExecutionTime(triggerContext);
	                return nextExec;
	            }
	        );
			
		} catch (Exception e) {
			logger.error("Error running AES Job : "+e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
}
