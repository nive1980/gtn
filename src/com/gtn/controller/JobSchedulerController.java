package com.gtn.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.JobSchedularDto;
import com.gtn.exception.ApplicationRuntimeException;
import com.gtn.helper.ExportOperationHelper;
import com.gtn.model.JobSchedular;
import com.gtn.model.RestResponse;
import com.gtn.model.User;
import com.gtn.service.GenericService;
import com.gtn.service.JobSchedularService;

@RestController
public class JobSchedulerController extends GenericController{

	@Autowired
	private JobSchedularService schedular;
	
	@Autowired
	private GenericService genericService;
	
	@RequestMapping(value="getSchedulerJob", method=RequestMethod.GET)
	public ResponseEntity<RestResponse<JobSchedular>> getSchedulerJob(HttpServletRequest request){
		String jobCode = request.getParameter("jobCode");
		
		ResponseEntity<RestResponse<JobSchedular>> result = null;
		
		if(ExportOperationHelper.isEmpty(jobCode)){			
			RestResponse<JobSchedular> error = new RestResponse<JobSchedular>(false,"Invalid object ID",null);
			result = new ResponseEntity<RestResponse<JobSchedular>>(error, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try{
			JobSchedular job = schedular.getCron(jobCode);
			RestResponse<JobSchedular> success = new RestResponse<JobSchedular>(true,"Successful",job);
			result = new ResponseEntity<RestResponse<JobSchedular>>(success,HttpStatus.OK);
			
		}catch(Exception e){
			RestResponse<JobSchedular> error = new RestResponse<JobSchedular>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<JobSchedular>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		return result;
	}
	
	
	
	@RequestMapping(value="saveAesFilingConfig", method=RequestMethod.POST)
	public ResponseEntity<RestResponse<JobSchedular>> saveAesFilingConfig(@RequestBody JobSchedular value, HttpServletRequest request){
		ResponseEntity<RestResponse<JobSchedular>> result = null;
				
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		
		try{
			
			if(value.getCron()!=null){
				value.setCron("0 "+value.getCron());
			}
			
			value.setModifiedBy(userDetail.getUsername());
			value.setModifiedOn(new Date());
			genericService.updateEntity(value);
	
			
			RestResponse<JobSchedular> success = new RestResponse<JobSchedular>(true,"Successful",value);
			result = new ResponseEntity<RestResponse<JobSchedular>>(success,HttpStatus.OK);
			
		}catch(ApplicationRuntimeException e){
			RestResponse<JobSchedular> error = new RestResponse<JobSchedular>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<JobSchedular>>(error,HttpStatus.ALREADY_REPORTED);
		}
		catch(Exception e){
			RestResponse<JobSchedular> error = new RestResponse<JobSchedular>(false,e.getCause().getMessage(),null);
			result = new ResponseEntity<RestResponse<JobSchedular>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
	@RequestMapping(value="searchScheduledJobs", method=RequestMethod.POST)
	public ResponseEntity<RestResponse<Collection<JobSchedular>>> searchScheduledJobs(@RequestBody JobSchedularDto view, HttpServletRequest request){
		ResponseEntity<RestResponse<Collection<JobSchedular>>> result = null;
		
		try{		
			
			if("cancel".equals(view.getReqNavType())){
				view = (JobSchedularDto) getSessionMap().get("view");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("view", view);
				//session.setAttribute("exporterView", view);
			}
			
			Collection<JobSchedular> results = schedular.searchScheduledJobs(view);
			
			/*List<JobSchedularDto> dtos = new ArrayList<JobSchedularDto>(results.size());
			
			for(JobSchedular entry: results){
				JobSchedularDto dto = new JobSchedularDto();
				
			}*/
			
			RestResponse<Collection<JobSchedular>> success = new RestResponse<Collection<JobSchedular>>(true,"Successful",results);
			result = new ResponseEntity<RestResponse<Collection<JobSchedular>>>(success,HttpStatus.OK);
			
		}catch(ApplicationRuntimeException e){
			RestResponse<Collection<JobSchedular>> error = new RestResponse<Collection<JobSchedular>>(false,e.getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<JobSchedular>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			RestResponse<Collection<JobSchedular>> error = new RestResponse<Collection<JobSchedular>>(false,e.getCause().getMessage(),null);
			result = new ResponseEntity<RestResponse<Collection<JobSchedular>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
}
