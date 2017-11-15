package com.gtn.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.SbuConfigView;
import com.gtn.exception.ApplicationRuntimeException;
import com.gtn.helper.ExportOperationHelper;
import com.gtn.model.RestResponse;
import com.gtn.model.SbuConfigValue;
import com.gtn.model.ShipmentItem;
import com.gtn.model.User;
import com.gtn.service.GenericService;
import com.gtn.service.SbuConfigService;

@RestController
public class SbuConfigController extends GenericController{
	
	@Autowired
	private SbuConfigService service;

	@Autowired
	private GenericService generiService;
	
	@RequestMapping(value="searchSbuConfig", method=RequestMethod.POST)
	public ResponseEntity searchSbuConfig(@RequestBody SbuConfigView configView, HttpServletRequest request){
		
		ResponseEntity result = null;
		
		try{
			
			if("cancel".equals(configView.getReqType())){
				configView = (SbuConfigView) getSessionMap().get("configView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("configView", configView);
				//session.setAttribute("exporterView", view);
			}
			
			
			
			Collection<SbuConfigValue> results = service.searchSbuConfig(configView);
			RestResponse<Collection<SbuConfigValue>> success = new RestResponse<Collection<SbuConfigValue>>(true,"Successful",results);
			result = new ResponseEntity<RestResponse<Collection<SbuConfigValue>>>(success,HttpStatus.OK);
			
		}catch(ApplicationRuntimeException e){
				RestResponse error = new RestResponse(false,e.getMessage(),null);
				result = new ResponseEntity<RestResponse>(error,HttpStatus.ALREADY_REPORTED);
		}
		catch(Exception e){
			RestResponse error = new RestResponse<>(false,e.getCause().getMessage(),null);
			result = new ResponseEntity<RestResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value="saveSbuConfig", method=RequestMethod.POST)
	public ResponseEntity saveSbuConfig(@RequestBody SbuConfigValue value, HttpServletRequest request){
		ResponseEntity result = null;
				
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		
		try{
			if(value.getId() > 0){
				value.setModifiedBy(userDetail.getUsername());
				value.setModifiedOn(new Date());
				generiService.updateEntity(value);
			}else{				
				value.setSbu(user.getSbu());
				value.setCreatedBy(userDetail.getUsername());
				value.setCreatedOn(new Date());
				generiService.saveEntity(value);
			}
			
			RestResponse<SbuConfigValue> success = new RestResponse<SbuConfigValue>(true,"Successful",value);
			result = new ResponseEntity<>(success,HttpStatus.OK);
			
		}catch(ApplicationRuntimeException e){
				RestResponse error = new RestResponse(false,e.getMessage(),null);
				result = new ResponseEntity<RestResponse>(error,HttpStatus.ALREADY_REPORTED);
		}
		catch(Exception e){
			RestResponse error = new RestResponse<>(false,e.getCause().getMessage(),null);
			result = new ResponseEntity<RestResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value="deleteSbuConfig", method = RequestMethod.POST)
	public ResponseEntity deleteSbuConfig(HttpServletRequest request){
		ResponseEntity response = null;
		
		try{
			String id = request.getParameter("id");
			long idInt = Long.parseLong(id);			
			SbuConfigValue value = (SbuConfigValue) generiService.findEntity(new SbuConfigValue(), idInt);
			generiService.removeEntity(value);
			response = new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/findSbuConfig", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<SbuConfigValue>> findSbuConfig(HttpServletRequest request) throws JAXBException, IOException{
		String id = request.getParameter("id");
		ResponseEntity<RestResponse<SbuConfigValue>> result = null;
		
		if(ExportOperationHelper.isEmpty(id)){			
			RestResponse<SbuConfigValue> error = new RestResponse<SbuConfigValue>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<SbuConfigValue>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			SbuConfigValue item = (SbuConfigValue) generiService.findEntity(new SbuConfigValue(), Long.parseLong(id));
			RestResponse<SbuConfigValue> success = new RestResponse<SbuConfigValue>(true,"Successful",item);
			result = new ResponseEntity<RestResponse<SbuConfigValue>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<SbuConfigValue> error = new RestResponse<SbuConfigValue>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<SbuConfigValue>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return result;
	}
}
