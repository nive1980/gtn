package com.gtn.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.DplAuditDto;
import com.gtn.dto.DplAuditReasonView;
import com.gtn.dto.DplReasonDto;
import com.gtn.dto.ExporterDto;
import com.gtn.dto.GridResponseDto;
import com.gtn.dto.ProductManufacturerDto;
import com.gtn.dto.ProductsDto;
import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.exception.ApplicationException;
import com.gtn.helper.MasterHelper;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.Country;
import com.gtn.model.DplAuditReasonValue;
import com.gtn.model.DplAuditValue;
import com.gtn.model.DplReasonValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ProductManufacturerValue;
import com.gtn.model.ProductManufacturerValuePk;
import com.gtn.model.ProductValue;
import com.gtn.model.RestResponse;
import com.gtn.model.State;
import com.gtn.model.User;
import com.gtn.service.GenericService;
import com.gtn.service.MasterService;
import com.gtn.service.WatchListScreeningService;

@RestController
public class MasterController extends GenericController{

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private WatchListScreeningService watchListScreeningService;
	
	@RequestMapping(value = "/saveExporterValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ExporterDto>> saveExporterValue(HttpServletRequest request, @RequestBody ExporterDto exporter) throws ApplicationException{
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		
		ResponseEntity<RestResponse<ExporterDto>> response = null;
		MasterHelper helper = new MasterHelper();
		
		List<String> errors = helper.validateExporter(exporter, genericService);
		
		if(errors.size() > 0){
			exporter.setMsgs(errors);
			RestResponse<ExporterDto> error = new RestResponse<ExporterDto>(false, "Exporter code missing or already present.", exporter);
			response = new ResponseEntity<RestResponse<ExporterDto>>(error, HttpStatus.EXPECTATION_FAILED);
		}else{
			ExporterValue exporterValue = helper.prepareExporter(exporter);
			
			try{
				if(exporter.getType()!=null && "New".equals(exporter.getType())){
					exporterValue.setCreatedOn(new Date());
					exporterValue.setCreatedBy(userDetail.getUsername());
					
					ScreeningEntity screeningEntity = helper.prepareScreenEntity(exporter);
					
					//screen entity	
					String screenResultStr = watchListScreeningService.doWatchListScreening(screeningEntity);					
					WlsScreeningResponse screenResult = watchListScreeningService.translateXmlResponse(screenResultStr);
					
					if(screenResult.getHttsReturned().getHits() > 0){
						exporterValue.setStatus("H");
					}else{
						exporterValue.setStatus("A");
					}
					
					exporter.setStatus(exporterValue.getStatus());
					
					//save audit entity
					Long auditNo = masterService.getDplAudit();
					DplAuditValue auditValue = helper.prepareAudit("EXPORTER", userDetail, auditNo, "T", exporterValue.getStatus(), null, exporterValue.getExpName(), exporterValue.getExpCode());
					genericService.saveEntity(auditValue);
	
					//save exporter
					genericService.saveEntity(exporterValue);
					exporter.setType("Edit");
				}else{
					exporterValue.setModifiedOn(new Date());
					exporterValue.setModifiedBy(userDetail.getUsername());
					genericService.updateEntity(exporterValue);
				}
				
				RestResponse<ExporterDto> success = new RestResponse<ExporterDto>(true,"Successful",exporter);
				response = new ResponseEntity<RestResponse<ExporterDto>>(success,HttpStatus.OK);
			}catch(Exception r){
				RestResponse<ExporterDto> error = new RestResponse<ExporterDto>(false,r.getMessage(),null);
				response = new ResponseEntity<RestResponse<ExporterDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		
		return response;
	}
	
	@RequestMapping(value = "/searchExporterValueCount", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Long>> searchExporterValueCount(HttpServletRequest request, @RequestBody ExporterDto view){
		ResponseEntity<RestResponse<Long>> response = null;
		//HttpSession session = request.getSession();
		try{
			
			//view.setSbu(null);
			
			if("cancel".equals(view.getReqType())){
				view = (ExporterDto) getSessionMap().get("exporterView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("exporterView", view);
				//session.setAttribute("exporterView", view);
			}
			
			//Collection<ExporterValue> results = masterService.searchExporters(view);
			Long totalCount = masterService.searchExportersCount(view);
			
			getSessionMap().put("resultCountExporterSearch", totalCount);
			/*Collection<ConsigneeValue> results = masterService.searchConsignee(view);
			List<ConsigneeDto> views = helper.prepareConsigneeDtoList(results);*/
			
			
			RestResponse<Long> success = new RestResponse<Long>(true,"Successful",totalCount);
			response = new ResponseEntity<RestResponse<Long>>(success,HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<Long> error = new RestResponse<Long>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<Long>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/searchExporterValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<GridResponseDto>> searchExporterValue(HttpServletRequest request, @RequestBody ExporterDto view){
		ResponseEntity<RestResponse<GridResponseDto>> response = null;
		MasterHelper helper = new MasterHelper();
		//HttpSession session = request.getSession();
		try{
			
			ExporterDto searchView = (ExporterDto) getSessionMap().get("exporterView");
			
			if(searchView == null)
				return null;
			
			Long count = (Long) getSessionMap().get("resultCountExporterSearch");
			
			searchView.setOffset(view.getOffset());
			searchView.setLimit(view.getLimit());
			
			if(!isEmpty(view.getSortBy()) && !isEmpty(view.getDirection())){
				searchView.setSortBy(helper.mapEntityCol(view.getSortBy()));
				searchView.setDirection(view.getDirection());
			}
			
			/*if("cancel".equals(view.getReqType())){
				view = (ExporterDto) getSessionMap().get("exporterView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("exporterView", view);
				//session.setAttribute("exporterView", view);
			}*/
			
			Collection<ExporterValue> results = masterService.searchExporters(searchView);
			Collection resultsDto = new ArrayList<>();
			
			for(ExporterValue val: results){
				resultsDto.add(helper.prepareExporterView(val));
			}
			
			//Long totalCount = masterService.searchExportersCount(view);
			
			GridResponseDto gridResponse = new GridResponseDto();
			
			gridResponse.setResults(resultsDto);
			gridResponse.setTotalResultCount(count);
			
			RestResponse<GridResponseDto> success = new RestResponse<GridResponseDto>(true,"Successful",gridResponse);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(success,HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<GridResponseDto> error = new RestResponse<GridResponseDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/findExporter", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<ExporterDto>> findExporter(HttpServletRequest request){
		ResponseEntity<RestResponse<ExporterDto>> response = null;
		String exporterCode = request.getParameter("expCode");
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(exporterCode)){
			RestResponse<ExporterDto> error = new RestResponse<ExporterDto>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<ExporterDto>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			ExporterValue exporter = (ExporterValue) genericService.findEntity(new ExporterValue(), exporterCode);
			ExporterDto dto = helper.prepareExporterView(exporter);
			
			dto.setType("Edit");
			
			RestResponse<ExporterDto> success = new RestResponse<ExporterDto>(true,"Successful",dto);
			response = new ResponseEntity<RestResponse<ExporterDto>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<ExporterDto> error = new RestResponse<ExporterDto>(false,e.getCause().getMessage(),null);
			response = new ResponseEntity<RestResponse<ExporterDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/deleteExporter", method = RequestMethod.DELETE)
	public ResponseEntity<RestResponse<String>> deleteExporter(HttpServletRequest request){
		ResponseEntity<RestResponse<String>> response = null;
		String expCode = request.getParameter("expCode");
		
		if(isEmpty(expCode)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			/*ExporterValue exporter = (ExporterValue) genericService.findEntity(new ExporterValue(), expCode);
			
			if(exporter!=null){
				
				genericService.removeEntity(exporter);
				
				RestResponse<String> success = new RestResponse<String>(true,"Successful",expCode);
				response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
			}*/
			
			masterService.removeExpCons(expCode, "exporter");
			
			RestResponse<String> success = new RestResponse<String>(true,"Successful",expCode);
			response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
			
			ExporterDto searchView = (ExporterDto) getSessionMap().get("exporterView");
			Long count = masterService.searchExportersCount(searchView);
			getSessionMap().put("resultCountExporterSearch", count);
			
		}catch(ApplicationException e){
			RestResponse<String> error = new RestResponse<String>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/getDplAudit", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<DplAuditDto>>> getDplAudit(HttpServletRequest request){
		ResponseEntity<RestResponse<Collection<DplAuditDto>>> response = null;
		String tableKey = request.getParameter("tableKey");
		String type = request.getParameter("type");
		
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(tableKey) || isEmpty(type)){
			RestResponse<Collection<DplAuditDto>> error = new RestResponse<Collection<DplAuditDto>>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<Collection<DplAuditDto>>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			Collection<DplAuditValue> audits = masterService.getDplAudit(tableKey, type);
			Collection<DplAuditDto> auditsView = null;
			
			if(audits!=null){
				auditsView = helper.prepareAudits(audits);				
				
			}else{
				auditsView = new ArrayList<DplAuditDto>();
			}
			
			RestResponse<Collection<DplAuditDto>> restResponse = new RestResponse<Collection<DplAuditDto>>(true,"Success",auditsView);
			response = new ResponseEntity<RestResponse<Collection<DplAuditDto>>>(restResponse,HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<Collection<DplAuditValue>> error = new RestResponse<Collection<DplAuditValue>>(false,e.getCause().getMessage(),null);
			response = new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/getDplAuditReason", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<DplReasonDto>>> getDplAuditReason(HttpServletRequest request){
		ResponseEntity<RestResponse<Collection<DplReasonDto>>> response = null;
		String statusCode = request.getParameter("statusCode");
		
		MasterHelper helper = new MasterHelper();
				
		try{
			Collection<DplReasonValue> reasons = masterService.getDplReason(statusCode);
			Collection<DplReasonDto> reasonView = null;
			
			if(reasons!=null){
				reasonView = helper.prepareDplReason(reasons);				
				
			}else{
				reasonView = new ArrayList<DplReasonDto>();
			}
			
			RestResponse<Collection<DplReasonDto>> restResponse = new RestResponse<Collection<DplReasonDto>>(true,"Success",reasonView);
			response = new ResponseEntity<RestResponse<Collection<DplReasonDto>>>(restResponse,HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<Collection<DplReasonDto>> error = new RestResponse<Collection<DplReasonDto>>(false,e.getCause().getMessage(),null);
			response = new ResponseEntity<RestResponse<Collection<DplReasonDto>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/updateDplAuditStatus", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<DplAuditDto>> updateDplAuditStatus(HttpServletRequest request, @RequestBody DplAuditReasonView view){
		ResponseEntity<RestResponse<DplAuditDto>> response = null;
		
		MasterHelper helper = new MasterHelper();
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
			
		try{
			if(isEmpty(view.getCode())){
				RestResponse<DplAuditDto> error = new RestResponse<DplAuditDto>(false,"Missing input",null);
				response = new ResponseEntity<RestResponse<DplAuditDto>>(error,HttpStatus.BAD_REQUEST);
			}else{
				
				DplAuditValue auditValue = null;
				
				if(view.getEntityType()!=null && "exporter".equals(view.getEntityType())){
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
				}
				
				DplAuditDto auditDto = new DplAuditDto();
				auditDto.setNewStatus(view.getNewStatus());
				
				RestResponse<DplAuditDto> success = new RestResponse<DplAuditDto>(true,"success",auditDto);
				response = new ResponseEntity<RestResponse<DplAuditDto>>(success,HttpStatus.OK);
			}
		}catch(ApplicationException e){
			e.printStackTrace();
			RestResponse<DplAuditDto> error = new RestResponse<DplAuditDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<DplAuditDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
				
		return response;
	}
	
	private void updateDplReasons(long auditNo, List<String> reasonCodes) throws ApplicationException{
		//List<DplAuditReasonValue> reasons = new ArrayList<DplAuditReasonValue>(reasonCodes.size());
		for(String reasonCode: reasonCodes){
			DplAuditReasonValue reason = new DplAuditReasonValue();
			reason.setDplAuditNo(auditNo);
			reason.setReasonCode(reasonCode);
			
			genericService.saveEntity(reason);
		}
	}
	
	
	@RequestMapping(value = "/getDplAuditReasonHistory", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<DplAuditReasonView>>> getDplAuditReasonHistory(HttpServletRequest request){
		ResponseEntity<RestResponse<Collection<DplAuditReasonView>>> response = null;
		String tableKey = request.getParameter("tableKey");
		String type = request.getParameter("type");
		
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(tableKey) || isEmpty(type)){
			RestResponse<Collection<DplAuditReasonView>> error = new RestResponse<Collection<DplAuditReasonView>>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<Collection<DplAuditReasonView>>>(error,HttpStatus.BAD_REQUEST);
		}
		
		try{
			Collection<DplAuditValue> audits = masterService.getDplAudit(tableKey, type);
			Collection<DplAuditReasonView> auditsView = null;
			
			if(audits!=null){
				auditsView = helper.prepareAuditsHistory(audits, masterService);				
				
			}else{
				auditsView = new ArrayList<DplAuditReasonView>();
			}
			
			RestResponse<Collection<DplAuditReasonView>> restResponse = new RestResponse<Collection<DplAuditReasonView>>(true,"Success",auditsView);
			response = new ResponseEntity<RestResponse<Collection<DplAuditReasonView>>>(restResponse,HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<Collection<DplAuditReasonView>> error = new RestResponse<Collection<DplAuditReasonView>>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<Collection<DplAuditReasonView>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ProductsDto>> saveProduct(HttpServletRequest request, @RequestBody ProductsDto product){
		ResponseEntity<RestResponse<ProductsDto>> response = null;
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		MasterHelper helper = new MasterHelper();
		
		//server side validation
		List<String> validator = helper.validateProduct(product);
		
		if(!isEmpty(product.getPartNo()) && "New".equals(product.getReqType())){
			try {
				ProductValue partDb = (ProductValue) genericService.findEntity(new ProductValue(), product.getPartNo());
				if(partDb!=null){
					validator.add("Part number - "+product.getPartNo()+" already present.");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
		}
		
		if(validator.size() > 0){
			product.setMsgs(validator);
			RestResponse<ProductsDto> error = new RestResponse<ProductsDto>(false,"Server side validation fail",product);
			response = new ResponseEntity<RestResponse<ProductsDto>>(error, HttpStatus.EXPECTATION_FAILED);
			return response;
		}
		
		ProductValue productValue = helper.prepareProduct(product);
		
		try{
			if(product.getReqType()!=null && "New".equals(product.getReqType())){
				productValue.setCreatedBy(userDetail.getUsername());
				productValue.setCreatedOn(new Date());
				
				genericService.saveEntity(productValue);
			}else{
				productValue.setUpdatedBy(userDetail.getUsername());
				productValue.setUpdatedOn(new Date());
				
				genericService.updateEntity(productValue);
			}
			
			product.setReqType("Edit");
			RestResponse<ProductsDto> success = new RestResponse<ProductsDto>(true,"Successful",product);
			response = new ResponseEntity<RestResponse<ProductsDto>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ProductsDto> error = new RestResponse<ProductsDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<ProductsDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/*@RequestMapping(value = "/searchProductsValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<List<ProductsDto>>> searchProductsValue(HttpServletRequest request, @RequestBody ProductsDto product){
		ResponseEntity<RestResponse<List<ProductsDto>>> response = null;
		MasterHelper helper = new MasterHelper();
		
		try{
			
			if("cancel".equals(product.getReqNavType())){
				product = (ProductsDto) getSessionMap().get("productView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("productView", product);
				//session.setAttribute("exporterView", view);
			}
			
			
			Collection<Object[]> results = masterService.searchProducts(product);
			
			List<ProductsDto> views = helper.prepareProductTableView(results);
			
			RestResponse<List<ProductsDto>> success = new RestResponse<List<ProductsDto>>(true,"Successful",views);
			response = new ResponseEntity<RestResponse<List<ProductsDto>>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<List<ProductsDto>> error = new RestResponse<List<ProductsDto>>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<List<ProductsDto>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}*/
	
	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<ProductsDto>> getProduct(HttpServletRequest request){
		ResponseEntity<RestResponse<ProductsDto>> response = null;
		String partNo = request.getParameter("id");
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(partNo)){
			RestResponse<ProductsDto> error = new RestResponse<ProductsDto>(false, "Invalid input", null);
			response = new ResponseEntity<RestResponse<ProductsDto>>(error, HttpStatus.NOT_FOUND);
		}else{
			ProductValue product;
			try {
				product = (ProductValue) genericService.findEntity(new ProductValue(), partNo);
				if(product == null){
					throw new ApplicationException("Prouct not found", null);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				RestResponse<ProductsDto> error = new RestResponse<ProductsDto>(false, "Error getting product : "+e.getMessage(), null);
				response = new ResponseEntity<RestResponse<ProductsDto>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
			ProductsDto dto = helper.prepareProductValueDto(product);
			dto.setReqType("Edit");
			
			RestResponse<ProductsDto> success = new RestResponse<ProductsDto>(true, "Successful", dto);
			response = new ResponseEntity<RestResponse<ProductsDto>>(success, HttpStatus.OK);
		}
		
		return response;
	}
	
	@RequestMapping(value = "deleteProduct", method = RequestMethod.DELETE)
	public ResponseEntity<RestResponse<String>> deleteProduct(HttpServletRequest request){
		ResponseEntity<RestResponse<String>> response = null;
		String id = request.getParameter("id");
		
		if(isEmpty(id)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			ProductValue product = (ProductValue) genericService.findEntity(new ProductValue(), id);
			
			if(product!=null){
				//delete manufacturers
				
				Collection<ProductManufacturerValue> productManufs = masterService.getProductManufacturers(id);
				
				for(ProductManufacturerValue val : productManufs){
					genericService.removeEntity(val);
				}
				
				genericService.removeEntity(product);
				
				RestResponse<String> success = new RestResponse<String>(true,"Successful",id);
				response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
				
				ProductsDto searchView = (ProductsDto) getSessionMap().get("productView");
				Long count = masterService.searchProductCount(searchView);
				getSessionMap().put("resultCountProductSearch", count);
			}
			
		}catch(ApplicationException e){
			RestResponse<String> error = new RestResponse<String>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return response;
	}
	
	@RequestMapping(value = "getProductManufacture", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<List<ProductManufacturerDto>>> getProductManufacture(HttpServletRequest request){
		ResponseEntity<RestResponse<List<ProductManufacturerDto>>> response = null;
		String partNo = request.getParameter("id");
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(partNo)){
			RestResponse<List<ProductManufacturerDto>> error = new RestResponse<List<ProductManufacturerDto>>(false, "Invalid input", null);
			response = new ResponseEntity<RestResponse<List<ProductManufacturerDto>>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			Collection<ProductManufacturerValue> results = masterService.getProductManufacturers(partNo);
			List<ProductManufacturerDto> resultDto = helper.prepareProductManufDtoList(results);
			
			RestResponse<List<ProductManufacturerDto>> success = new RestResponse<List<ProductManufacturerDto>>(true, "Successful", resultDto);
			response = new ResponseEntity<RestResponse<List<ProductManufacturerDto>>>(success, HttpStatus.OK);
			
			
		}catch(ApplicationException e){
			RestResponse<List<ProductManufacturerDto>> error = new RestResponse<List<ProductManufacturerDto>>(false, "Error getting manufacturers", null);
			response = new ResponseEntity<RestResponse<List<ProductManufacturerDto>>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		return response;
	}
	
	@RequestMapping(value = "getProductManufactureItemNo", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Long>> getProductManufactureItemNo(HttpServletRequest request){
		ResponseEntity<RestResponse<Long>> response = null;
		String partNo = request.getParameter("id");
		
		try{
			Long itemNo = masterService.getProductManufactureItemNo(partNo);
			
			RestResponse<Long> success = new RestResponse<Long>(true, "Successful", itemNo);
			response = new ResponseEntity<RestResponse<Long>>(success, HttpStatus.OK);
			
		}catch(ApplicationException e){
			RestResponse<Long> error = new RestResponse<Long>(false, "Error getting item no : "+e.getMessage(), null);
			response = new ResponseEntity<RestResponse<Long>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/saveProductManufacture", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ProductManufacturerDto>> saveProductManufacture(HttpServletRequest request, @RequestBody ProductManufacturerDto manufacture){
		ResponseEntity<RestResponse<ProductManufacturerDto>> response = null;
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		MasterHelper helper = new MasterHelper();
		
		//server side validation
		List<String> validator = helper.validateProductManufacture(manufacture);
		
		if(!isEmpty(manufacture.getPartNo()) && manufacture.getItemNo()>0 && "New".equals(manufacture.getReqType())){
			try {
				ProductManufacturerValuePk pk = new ProductManufacturerValuePk(manufacture.getPartNo(), manufacture.getItemNo());
				ProductManufacturerValue manufactureDb = (ProductManufacturerValue) genericService.findEntity(new ProductManufacturerValue(),pk);
				if(manufactureDb!=null){
					validator.add("Part number - "+manufacture.getPartNo()+" and Item number - "+manufacture.getItemNo()+" already present.");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
		}
		
		if(validator.size() > 0){
			manufacture.setMsgs(validator);
			RestResponse<ProductManufacturerDto> error = new RestResponse<ProductManufacturerDto>(false,"Server side validation fail",manufacture);
			response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(error, HttpStatus.EXPECTATION_FAILED);
			return response;
		}
		
		ProductManufacturerValue manufactureValue = helper.prepareProductManufacture(manufacture);
		
		try{
			if(manufacture.getReqType()!=null && "New".equals(manufacture.getReqType())){
				manufactureValue.setCreatedBy(userDetail.getUsername());
				manufactureValue.setCreatedOn(new Date());
				
				genericService.saveEntity(manufactureValue);
			}else{
				manufactureValue.setUpdatedBy(userDetail.getUsername());
				manufactureValue.setUpdatedOn(new Date());
				
				genericService.updateEntity(manufactureValue);
			}
			
			manufacture.setReqType("Edit");
			RestResponse<ProductManufacturerDto> success = new RestResponse<ProductManufacturerDto>(true,"Successful",manufacture);
			response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(success,HttpStatus.OK);
		}catch(Exception e){
			RestResponse<ProductManufacturerDto> error = new RestResponse<ProductManufacturerDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
		
	@RequestMapping(value = "deleteProductManufacture", method = RequestMethod.DELETE)
	public ResponseEntity<RestResponse<String>> deleteProductManufacture(HttpServletRequest request, @RequestBody ProductManufacturerValuePk pk){
		ResponseEntity<RestResponse<String>> response = null;
		
		if(pk == null){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			
			ProductManufacturerValue product = (ProductManufacturerValue) genericService.findEntity(new ProductManufacturerValue(), pk);
			
			if(product!=null){				
				genericService.removeEntity(product);
				
				RestResponse<String> success = new RestResponse<String>(true,"Successful",pk.getPartNo());
				response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
			}
			
		}catch(ApplicationException e){
			RestResponse<String> error = new RestResponse<String>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return response;
	}
	
	@RequestMapping(value = "getProductManufactureObj", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ProductManufacturerDto>> getProductManufacture(HttpServletRequest request, @RequestBody ProductManufacturerValuePk pk){
		ResponseEntity<RestResponse<ProductManufacturerDto>> response = null;
		MasterHelper helper = new MasterHelper();
		
		if(pk == null){
			RestResponse<ProductManufacturerDto> error = new RestResponse<ProductManufacturerDto>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			
			ProductManufacturerValue product = (ProductManufacturerValue) genericService.findEntity(new ProductManufacturerValue(), pk);
			ProductManufacturerDto dto = helper.prepareProductManufDto(product);
			
			if(product!=null){				
				RestResponse<ProductManufacturerDto> success = new RestResponse<ProductManufacturerDto>(true,"Successful",dto);
				response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(success,HttpStatus.OK);
			}
			
		}catch(ApplicationException e){
			RestResponse<ProductManufacturerDto> error = new RestResponse<ProductManufacturerDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<ProductManufacturerDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return response;
	}
	
	
	@RequestMapping(value = "/searchProductCount", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Long>> searchProductCount(HttpServletRequest request, @RequestBody ProductsDto view){
		ResponseEntity<RestResponse<Long>> response = null;
		MasterHelper helper = new MasterHelper();
		try{
			
			if("cancel".equals(view.getReqNavType())){
				view = (ProductsDto) getSessionMap().get("productView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("productView", view);
				//session.setAttribute("exporterView", view);
			}
			//remove later
			//view.setSbu(null);
			
			Long count = masterService.searchProductCount(view);
			getSessionMap().put("resultCountProductSearch", count);
			/*Collection<ConsigneeValue> results = masterService.searchConsignee(view);
			List<ConsigneeDto> views = helper.prepareConsigneeDtoList(results);*/
			RestResponse<Long> success = new RestResponse<Long>(true,"Successful",count);
			response = new ResponseEntity<RestResponse<Long>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<Long> error = new RestResponse<Long>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<Long>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/searchProductValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<GridResponseDto>> searchProductValue(HttpServletRequest req, @RequestBody ProductsDto view){
		ResponseEntity<RestResponse<GridResponseDto>> response = null;
		MasterHelper helper = new MasterHelper();
		
		ProductsDto searchView = (ProductsDto) getSessionMap().get("productView");
		Long count = (Long) getSessionMap().get("resultCountProductSearch");
		
		if(searchView == null)
			return null;
		
		searchView.setOffset(view.getOffset());
		searchView.setLimit(view.getLimit());
		
		if(!isEmpty(view.getSortBy()) && !isEmpty(view.getDirection())){
			searchView.setSortBy(helper.getProductSortCol(view.getSortBy()));
			searchView.setDirection(view.getDirection());
		}
		
		/*if(searchView.getOffset() == 0){
			searchView.setOffset(1);
		}*/
		
		
		
		try {
			
			//remove later
			//view.setSbu(null);
			Collection results = masterService.searchProductPagination(searchView);
			List<ProductsDto> views = helper.prepareProductTableView(results);
			
			GridResponseDto gridResponse = new GridResponseDto();
			
			gridResponse.setResults(views);
			gridResponse.setTotalResultCount(count);
			
			RestResponse<GridResponseDto> success = new RestResponse<GridResponseDto>(true,"Success",gridResponse);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(success,HttpStatus.OK);
			
		} catch (ApplicationException e) {
			e.printStackTrace();
			RestResponse<GridResponseDto> error = new RestResponse<GridResponseDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
				
	}
	
	/**
	 * Consignee start
	 */	
	/*@RequestMapping(value = "/searchConsigneeValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<List<ConsigneeDto>>> searchConsigneeValue(HttpServletRequest request, @RequestBody ConsigneeDto view){
		ResponseEntity<RestResponse<List<ConsigneeDto>>> response = null;
		MasterHelper helper = new MasterHelper();
		try{
			
			if("cancel".equals(view.getReqNavType())){
				view = (ConsigneeDto) getSessionMap().get("consigneeView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("consigneeView", view);
				//session.setAttribute("exporterView", view);
			}
			
			
			//remove later
			view.setSbuCode(null);
			
			Collection<ConsigneeValue> results = masterService.searchConsignee(view);
			List<ConsigneeDto> views = helper.prepareConsigneeDtoList(results);
			RestResponse<List<ConsigneeDto>> success = new RestResponse<List<ConsigneeDto>>(true,"Successful",views);
			response = new ResponseEntity<RestResponse<List<ConsigneeDto>>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<List<ConsigneeDto>> error = new RestResponse<List<ConsigneeDto>>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<List<ConsigneeDto>>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}*/
	
	@RequestMapping(value = "/searchConsigneeCount", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<Long>> searchConsigneeCount(HttpServletRequest request, @RequestBody ConsigneeDto view){
		ResponseEntity<RestResponse<Long>> response = null;
		MasterHelper helper = new MasterHelper();
		try{
			
			if("cancel".equals(view.getReqNavType())){
				view = (ConsigneeDto) getSessionMap().get("consigneeView");
				//view = (ExporterDto) session.getAttribute("exporterView");
			}else{
				getSessionMap().put("consigneeView", view);
				//session.setAttribute("exporterView", view);
			}
			
			
			//remove later
			//view.setSbuCode(null);
			
			Long count = masterService.searchConsigneeCount(view);
			getSessionMap().put("resultCountConsigneeSearch", count);
			/*Collection<ConsigneeValue> results = masterService.searchConsignee(view);
			List<ConsigneeDto> views = helper.prepareConsigneeDtoList(results);*/
			RestResponse<Long> success = new RestResponse<Long>(true,"Successful",count);
			response = new ResponseEntity<RestResponse<Long>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<Long> error = new RestResponse<Long>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<Long>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/searchConsigneeValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<GridResponseDto>> searchConsigneeValue(HttpServletRequest req, @RequestBody ConsigneeDto view){
		ResponseEntity<RestResponse<GridResponseDto>> response = null;
		MasterHelper helper = new MasterHelper();
		
		ConsigneeDto searchView = (ConsigneeDto) getSessionMap().get("consigneeView");
		Long count = (Long) getSessionMap().get("resultCountConsigneeSearch");
		
		if(searchView == null)
			return null;
		
		searchView.setOffset(view.getOffset());
		searchView.setLimit(view.getLimit());
		
		if(!isEmpty(view.getSortBy()) && !isEmpty(view.getDirection())){
			searchView.setSortBy(view.getSortBy());
			searchView.setDirection(view.getDirection());
		}
		
		/*if(searchView.getOffset() == 0){
			searchView.setOffset(1);
		}*/
		
		try {
			
			//remove later
			//view.setSbuCode(null);
			Collection<ConsigneeValue> results = masterService.searchConsgineePagination(searchView);
			List<ConsigneeDto> views = helper.prepareConsigneeDtoList(results);
			/*List listMap = new ArrayList();
			
			for(ConsigneeDto dto : views){
				Map map = new HashMap();
				
				map.put("consigneeId", dto.getConsigneeId());
				map.put("consigneeName", dto.getConsigneeName());
				map.put("consigneeCountryName", dto.getConsigneeCountryName());
				map.put("salesPerson", dto.getSalesPerson());
				map.put("typeOfConsignee", dto.getTypeOfConsignee());
				map.put("sbuCode", dto.getSbuCode());
				map.put("status", dto.getStatus());
				
				listMap.add(map);
			}*/
			
			GridResponseDto gridResponse = new GridResponseDto();
			
			gridResponse.setResults(views);
			gridResponse.setTotalResultCount(count);
			
			RestResponse<GridResponseDto> success = new RestResponse<GridResponseDto>(true,"Success",gridResponse);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(success,HttpStatus.OK);
			
		} catch (ApplicationException e) {
			e.printStackTrace();
			RestResponse<GridResponseDto> error = new RestResponse<GridResponseDto>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<GridResponseDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
				
	}
	
	
	
	@RequestMapping(value = "/saveConsigneeValue", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<ConsigneeDto>> saveConsigneeValue(HttpServletRequest request, @RequestBody ConsigneeDto consignee) throws ApplicationException{
		
		com.gtn.security.SecurityUserDetail userDetail = (com.gtn.security.SecurityUserDetail) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
				
		User user = userDetail.getUser();
		//consignee.setSbuCode(user.getSbu());
		
		ResponseEntity<RestResponse<ConsigneeDto>> response = null;
		MasterHelper helper = new MasterHelper();
		
		List<String> errors = helper.validateConsignee(consignee, genericService);
		
		if(errors.size() > 0){
			consignee.setMsgs(errors);
			RestResponse<ConsigneeDto> error = new RestResponse<ConsigneeDto>(false, "Consignee code missing or already present.", consignee);
			response = new ResponseEntity<RestResponse<ConsigneeDto>>(error, HttpStatus.EXPECTATION_FAILED);
		}else{
			ConsigneeValue consigneeValue = helper.prepareConsignee(consignee);
			
			try{
				if(consignee.getReqType()!=null && "New".equals(consignee.getReqType())){
					consigneeValue.setCreationDate(new Date());
					consigneeValue.setCreatedBy(userDetail.getUsername());
					
					ScreeningEntity screeningEntity = helper.prepareScreenEntityConsignee(consignee);
					
					//screen entity	
					String screenResultStr = watchListScreeningService.doWatchListScreening(screeningEntity);					
					WlsScreeningResponse screenResult = watchListScreeningService.translateXmlResponse(screenResultStr);
					
					if(screenResult.getHttsReturned().getHits() > 0){
						consigneeValue.setStatus("H");
					}else{
						consigneeValue.setStatus("A");
					}
					
					consignee.setStatus(consigneeValue.getStatus());
					
					//save audit entity
					Long auditNo = masterService.getDplAudit();
					DplAuditValue auditValue = helper.prepareAudit("CONSIGNEE", userDetail, auditNo, "T", consigneeValue.getStatus(), null, consigneeValue.getConsigneeName(), consigneeValue.getConsigneeId());
					genericService.saveEntity(auditValue);
	
					//save exporter
					genericService.saveEntity(consigneeValue);
					consignee.setReqType("Edit");
				}else{
					consigneeValue.setModifiedOn(new Date());
					consigneeValue.setModifiedBy(userDetail.getUsername());
					genericService.updateEntity(consigneeValue);
				}
				
				RestResponse<ConsigneeDto> success = new RestResponse<ConsigneeDto>(true,"Successful",consignee);
				response = new ResponseEntity<RestResponse<ConsigneeDto>>(success,HttpStatus.OK);
			}catch(Exception r){
				RestResponse<ConsigneeDto> error = new RestResponse<ConsigneeDto>(false,r.getCause().getMessage(),null);
				response = new ResponseEntity<RestResponse<ConsigneeDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/findConsignee", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<ConsigneeDto>> findConsignee(HttpServletRequest request){
		ResponseEntity<RestResponse<ConsigneeDto>> response = null;
		String consigneeId = request.getParameter("id");
		MasterHelper helper = new MasterHelper();
		
		if(isEmpty(consigneeId)){
			RestResponse<ConsigneeDto> error = new RestResponse<ConsigneeDto>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<ConsigneeDto>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			ConsigneeValue consignee = (ConsigneeValue) genericService.findEntity(new ConsigneeValue(), consigneeId);
			ConsigneeDto dto = helper.prepareConsigneeDto(consignee);
			
			dto.setReqType("Edit");
			
			RestResponse<ConsigneeDto> success = new RestResponse<ConsigneeDto>(true,"Successful",dto);
			response = new ResponseEntity<RestResponse<ConsigneeDto>>(success,HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<ConsigneeDto> error = new RestResponse<ConsigneeDto>(false,e.getCause().getMessage(),null);
			response = new ResponseEntity<RestResponse<ConsigneeDto>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	//
	@RequestMapping(value = "/deleteConsignee", method = RequestMethod.DELETE)
	public ResponseEntity<RestResponse<String>> deleteConsignee(HttpServletRequest request){
		ResponseEntity<RestResponse<String>> response = null;
		String consigneeId = request.getParameter("id");
		
		if(isEmpty(consigneeId)){
			RestResponse<String> error = new RestResponse<String>(false,"Invalid Input.",null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.BAD_REQUEST);
			return response;
		}
		
		try{
			/*ExporterValue exporter = (ExporterValue) genericService.findEntity(new ExporterValue(), expCode);
			
			if(exporter!=null){
				
				genericService.removeEntity(exporter);
				
				RestResponse<String> success = new RestResponse<String>(true,"Successful",expCode);
				response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
			}*/
			
			masterService.removeExpCons(consigneeId, "consignee");
			RestResponse<String> success = new RestResponse<String>(true,"Successful",consigneeId);
			response = new ResponseEntity<RestResponse<String>>(success,HttpStatus.OK);
			
			ConsigneeDto searchView = (ConsigneeDto) getSessionMap().get("consigneeView");
			Long count = masterService.searchConsigneeCount(searchView);
			getSessionMap().put("resultCountConsigneeSearch", count);
		}catch(ApplicationException e){
			RestResponse<String> error = new RestResponse<String>(false,e.getMessage(),null);
			response = new ResponseEntity<RestResponse<String>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value="/getCountry", method=RequestMethod.GET)
	public Collection<Country> getCountry(HttpServletRequest request) throws ApplicationException{
		//List<Country> countries = new ArrayList<Country>();
		
		String param = request.getParameter("req");
		
		Collection<Country> countries = masterService.getCountry(param);
				
		return countries;
	}
	
	

	@RequestMapping(value = "/getStates", method = RequestMethod.GET)
	public ResponseEntity<RestResponse<Collection<State>>> getStates(HttpServletRequest request){
		ResponseEntity<RestResponse<Collection<State>>> response = null;
		
		String countryCode = request.getParameter("countryCode");
		String query = request.getParameter("qry");
		
		/*if(ExportOperationHelper.isEmpty(countryCode)){
			RestResponse<Collection<State>> error = new RestResponse<Collection<State>>(false,"Invalid object ID",null);
			response = new ResponseEntity<RestResponse<Collection<State>>>(error, HttpStatus.BAD_REQUEST);
			return response;
		}*/
		
		try{
			Collection<State> states = masterService.getStates(countryCode, query);
			
			RestResponse<Collection<State>> msg = new RestResponse<Collection<State>>(true,"success",states);
			return new ResponseEntity<RestResponse<Collection<State>>>(msg, HttpStatus.OK);
		}catch(ApplicationException e){
			RestResponse<Collection<State>> error = new RestResponse<Collection<State>>(false,"Error getting states",null);
			response = new ResponseEntity<RestResponse<Collection<State>>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	
	private boolean isEmpty(String s){
		if(s==null || s.isEmpty()){
			return true;
		}
		return false;
	}
	
}
