package com.gtn.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.exception.ApplicationException;
import com.gtn.exception.ApplicationRuntimeException;
import com.gtn.helper.ExportOperationHelper;
import com.gtn.model.RestResponse;
import com.gtn.service.ExportOperationService;

@RestController
public class BusinessIntelligenceController {
	
	@Autowired
	private ExportOperationService eadService;

	@RequestMapping(value="/getShipmentStatusGraph", method = RequestMethod.GET)
	public ResponseEntity<List> getShipmentStatusGraph(HttpServletRequest request) throws JSONException{
		
		ResponseEntity<List> response = null;
		List arr = new ArrayList();
		
		String type = request.getParameter("type");
		
		try {
			
			if("gtn".equals(type)){
				Collection<Object[]> result = eadService.getShipmentStatusCount();
				
				if(result!=null && result.size() > 0){
					Iterator it = result.iterator();
					
					while(it.hasNext()){
						Object[] objs = (Object[]) it.next();
						Map obj = new HashMap();
						obj.put("key", ExportOperationHelper.getStatus((String) objs[1]));
						if(objs[0]!=null){
							obj.put("y", (Long) objs[0]);
						}else{
							obj.put("y", 0);
						}					
						arr.add(obj);
					}
				}
			}else if("aes".equals(type)){
				Collection<Object[]> result = eadService.getShipmentAesStatusCount();
				
				if(result!=null && result.size() > 0){
					Iterator it = result.iterator();
					
					while(it.hasNext()){
						Object[] objs = (Object[]) it.next();
						Map obj = new HashMap();
						String key = (String) objs[1];
						obj.put("key", objs[1] == null ? " - " : key.substring(0, 1).toUpperCase() + key.substring(1));
						if(objs[0]!=null){
							obj.put("y", (Long) objs[0]);
						}else{
							obj.put("y", 0);
						}					
						arr.add(obj);
					}
				}
			}else{
				response = new ResponseEntity<List>(arr, HttpStatus.BAD_REQUEST);
				return response;
			}
			
			
			//RestResponse<JSONArray> success = new RestResponse<JSONArray>(true,"success",arr);
			response = new ResponseEntity<List>(arr, HttpStatus.OK);
			
		} catch (ApplicationException | ApplicationRuntimeException e) {
			e.printStackTrace();
			//RestResponse<JSONArray> error = new RestResponse<JSONArray>(false,e.getMessage(),null);
			response = new ResponseEntity<List>(arr, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		return response;		
	}
	
	
	
	@RequestMapping(value="/getShipmentByCountryGraph", method = RequestMethod.GET)
	public ResponseEntity<List> getShipmentByCountryGraph() throws JSONException{
		
		ResponseEntity<List> response = null;
		List arr = new ArrayList();
		try {
			Collection<Object[]> result = eadService.getShipmentByCountryCount();
						
			Map obj = new HashMap();
			
			if(result!=null && result.size() > 0){
				Iterator it = result.iterator();
				
				obj.put("key", "Cumulative Shipments");
				List arrInner = new ArrayList();
				
				while(it.hasNext()){
					Object[] objs = (Object[]) it.next();
					Map objInner = new HashMap();
					objInner.put("label", (String) objs[2]);
					objInner.put("cname", (String) objs[1]);
					if(objs[0]!=null){
						objInner.put("value", (Long) objs[0]);
						
					}else{
						objInner.put("value", 0);
					}
					
					arrInner.add(objInner);
				}
				
				obj.put("values", arrInner);
			}
			
			arr.add(obj);
			
			//RestResponse<JSONArray> success = new RestResponse<JSONArray>(true,"success",arr);
			response = new ResponseEntity<List>(arr, HttpStatus.OK);
			
		} catch (ApplicationException | ApplicationRuntimeException e) {
			e.printStackTrace();
			//RestResponse<JSONArray> error = new RestResponse<JSONArray>(false,e.getMessage(),null);
			response = new ResponseEntity<List>(arr, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		return response;		
	}
	

	@RequestMapping(value="/getShipmentByValue", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Map>> getShipmentByValue(HttpServletRequest request) throws JSONException{
		
		ResponseEntity<RestResponse<Map>> response = null;
		Map responseMap = new HashMap();
		
		List arr = new ArrayList();
		List countArr = new ArrayList();
				
		try {
			
				Collection<Object[]> result = eadService.getShipmentsByValue();
				
				if(result!=null && result.size() > 0){
					Iterator it = result.iterator();
					
					while(it.hasNext()){
						Object[] objs = (Object[]) it.next();
						Map obj = new HashMap();
						Map countObj = new HashMap();
						
						obj.put("x", (Date) objs[2]);
						countObj.put("x", (Date) objs[2]);
						
						if(objs[0]!=null){
							obj.put("y", (BigDecimal) objs[0]);
						}else{
							obj.put("y", 0);
						}					
						arr.add(obj);
						
						
						if(objs[1]!=null){
							countObj.put("y", (Integer) objs[1]);
						}else{
							countObj.put("y", 0);
						}					
						countArr.add(countObj);
					}
				}
			
			responseMap.put("valueTotal", arr);
			responseMap.put("countTotal", countArr);
			
			RestResponse<Map> success = new RestResponse<Map>(true,"success",responseMap);
			response = new ResponseEntity<RestResponse<Map>>(success, HttpStatus.OK);
			
		} catch (ApplicationException | ApplicationRuntimeException e) {
			e.printStackTrace();
			RestResponse<Map> error = new RestResponse<Map>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<Map>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;		
	}
	
}
