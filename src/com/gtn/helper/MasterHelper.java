package com.gtn.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.DplAuditDto;
import com.gtn.dto.DplAuditReasonView;
import com.gtn.dto.DplReasonDto;
import com.gtn.dto.ExporterDto;
import com.gtn.dto.ProductManufacturerDto;
import com.gtn.dto.ProductsDto;
import com.gtn.dto.ScreeningEntity;
import com.gtn.exception.ApplicationException;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.DplAuditValue;
import com.gtn.model.DplReasonValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ProductManufacturerValue;
import com.gtn.model.ProductValue;
import com.gtn.model.SbuConfigValue;
import com.gtn.security.SecurityUserDetail;
import com.gtn.service.CommonDataService;
import com.gtn.service.GenericService;
import com.gtn.service.MasterService;
import com.gtn.util.Constants;

public class MasterHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(MasterHelper.class);
	
	public List<String> validateExporter(ExporterDto exporter, GenericService genericService) throws ApplicationException{
		List<String> errors = new ArrayList<String>();
		
		if(isEmpty(exporter.getExporterCode())){
			errors.add("Exporter code is required.");
		}else if(exporter.getType()!=null && !"Edit".equals(exporter.getType())){
			ExporterValue exporterValue = (ExporterValue) genericService.findEntity(new ExporterValue(), exporter.getExporterCode());
			if(exporterValue!=null){
				errors.add("Exporter code already present.");
			}
		}
		
		return errors;
	}
	
	public ExporterValue prepareExporter(ExporterDto dto){
		ExporterValue entity = new ExporterValue();
		
		entity.setActive(dto.isActive()==true?"Y":"N");
		entity.setUseForAes(dto.isAes()==true?"Y":"N");
		entity.setUseForDoc(dto.isDoc()==true?"Y":"N");
		entity.setUseForDos(dto.isDos()==true?"Y":"N");
		
		entity.setExpCode(dto.getExporterCode());
		entity.setStatus(dto.getStatus());
		entity.setExpName(dto.getDepartment());
		entity.setExpCity(dto.getCity());
		entity.setExpState(dto.getState());
		entity.setExpStateName(dto.getStateName());
		entity.setExpAddr1(dto.getAddrLine1());
		entity.setExpAddr2(dto.getAddrLine2());
		entity.setExpCountry(dto.getCountry());
		entity.setExpCountryName(dto.getCountryName());
		entity.setPhone(dto.getTelephone());
		entity.setZip(dto.getZipCode());
		entity.setFax(dto.getFax());

		entity.setShipperAuthorizationSymbol(dto.getShipperAuthSymbol());
		entity.setTransmitterId(dto.getTransmitterId());
		
		entity.setFilerExpIdType(dto.getUsppIdType());
		entity.setFilerExpId(dto.getUsppId());
		entity.setFilerId(dto.getFilerId());
		entity.setFilerIdType(dto.getFilerTypeId());
		
		entity.setDocRegistrationDate(dto.getDocRegistrationDate());
		entity.setDocRegistrationExpiryDate(dto.getDocRegistrationExpDate());
		entity.setDocFacilityRegistrationNumber(dto.getDocFacilityRegNo());
		entity.setDosRegistrationDate(dto.getDosRegistrationDate());
		entity.setDosRegistrationExpiryDate(dto.getDosRegistrationExpDate());
		entity.setDosAgreementExpiryDate(dto.getDosAgreementExpDate());
		entity.setDosRegistrationNumber(dto.getDosRegistrationNo());
		
		entity.setEmail(dto.getEmail());
		entity.setSbu(dto.getSbu());
		
		return entity;
	}
	
	public ExporterDto prepareExporterView(ExporterValue entity){
		ExporterDto dto = new ExporterDto();
		
		dto.setActive((entity.getActive() != null && entity.getActive().equals("Y"))?true:false);
		dto.setAes((entity.getUseForAes()!=null && entity.getUseForAes().equals("Y"))?true:false);
		
		dto.setDos((entity.getUseForDos()!=null && entity.getUseForDos().equals("Y"))?true:false);
		dto.setDoc((entity.getUseForDoc()!=null && entity.getUseForDoc().equals("Y"))?true:false);
		
		dto.setExporterCode(entity.getExpCode());
		dto.setStatus(entity.getStatus());
		dto.setDepartment(entity.getExpName());
		dto.setCity(entity.getExpCity());
		dto.setState(entity.getExpState());
		dto.setStateName(entity.getExpStateName());
		dto.setAddrLine1(entity.getExpAddr1());
		dto.setAddrLine2(entity.getExpAddr2());
		dto.setCountry(entity.getExpCountry());
		dto.setCountryName(entity.getExpCountryName());
		dto.setTelephone(entity.getPhone());
		dto.setZipCode(entity.getZip());
		dto.setFax(entity.getFax());

		dto.setShipperAuthSymbol(entity.getShipperAuthorizationSymbol());
		dto.setTransmitterId(entity.getTransmitterId());
		
		dto.setFilerTypeId(entity.getFilerIdType());
		dto.setFilerId(entity.getFilerId());
		dto.setUsppId(entity.getFilerExpId());
		dto.setUsppIdType(entity.getFilerExpIdType());
		
		dto.setDocRegistrationDate(entity.getDocRegistrationDate());
		dto.setDocRegistrationExpDate(entity.getDocRegistrationExpiryDate());
		dto.setDocFacilityRegNo (entity.getDocFacilityRegistrationNumber());
		dto.setDosRegistrationDate (entity.getDosRegistrationDate());
		dto.setDosRegistrationExpDate(entity.getDosRegistrationExpiryDate ());
		dto.setDosAgreementExpDate (entity.getDosAgreementExpiryDate());
		dto.setDosRegistrationNo (entity.getDosRegistrationNumber());
		dto.setSbu(entity.getSbu());
		dto.setEmail(entity.getEmail());
		
		dto.setDelete(entity.getExpCode());
		
		return dto;
	}
	
	public ScreeningEntity prepareScreenEntity(ExporterDto dto){
		ScreeningEntity screeningEntity = new ScreeningEntity();
		
		screeningEntity.setAddressLine1(dto.getAddrLine1());
		screeningEntity.setAddressLine2(dto.getAddrLine2());
		screeningEntity.setCity(dto.getCity());
		screeningEntity.setCountry(dto.getCountry());
		screeningEntity.setEntityId(dto.getExporterCode());
		screeningEntity.setEntityType("Exporter");
		screeningEntity.setName(dto.getDepartment());
		screeningEntity.setOrgType("Organization");
		screeningEntity.setState(dto.getState());
		screeningEntity.setZip(dto.getZipCode());
		
		return screeningEntity;
	}

	public DplAuditValue prepareAudit(String type, SecurityUserDetail userDetail, Long auditNo, String oldStatus, String newStatus, 
			String otherReason, String partyName, String tableKey){
		DplAuditValue audit = new DplAuditValue();
		
		audit.setActmgrName(null);
		audit.setCreatedBy(userDetail.getUsername());
		audit.setCreatedOn(new Date());
		audit.setDplAuditNo(auditNo);
		audit.setEntityName(type);
		audit.setFieldType(null);
		audit.setNewStatus(newStatus);
		audit.setOldStatus(oldStatus);
		audit.setOtherReason(otherReason);
		audit.setPartyName(partyName);
		audit.setSbuCode(userDetail.getUser().getSbu());
		audit.setTablekey(tableKey);
		audit.setTablename(type);
		
		return audit;
	}
	
	public Collection<DplAuditDto> prepareAudits(Collection<DplAuditValue> audits){
		Collection<DplAuditDto> views = new ArrayList<DplAuditDto>(audits.size());
		
		for(DplAuditValue audit : audits){
			DplAuditDto view = new DplAuditDto();
			view.setCreatedOn(audit.getCreatedOn());
			view.setCreatedBy(audit.getCreatedBy());
			view.setDplAuditNo(audit.getDplAuditNo());
			view.setPartyName(audit.getPartyName());
			view.setOldStatus(getStatus(audit.getOldStatus()));
			view.setNewStatus(getStatus(audit.getNewStatus()));
			
			views.add(view);
		}
		
		return views;
	}
	
	public Collection<DplReasonDto> prepareDplReason(Collection<DplReasonValue> reasons){
		Collection<DplReasonDto> views = new ArrayList<DplReasonDto>(reasons.size());
		
		for(DplReasonValue reason : reasons){
			DplReasonDto view = new DplReasonDto();
			
			view.setReasonCode(reason.getReasonCode());
			view.setReason(reason.getReason());
			view.setDenyOverride(reason.getDenyOverride());
			view.setActive(reason.getActive());
			
			views.add(view);
		}
		
		return views;
	}
	
	public Collection<DplAuditReasonView> prepareAuditsHistory(Collection<DplAuditValue> audits, MasterService masterService) throws ApplicationException{
		Collection<DplAuditReasonView> views = new ArrayList<DplAuditReasonView>();
		
		for(DplAuditValue audit: audits){
			DplAuditReasonView view = new DplAuditReasonView();
			
			view.setAuditValue(audit);
			view.setReasons(new ArrayList<DplReasonDto>());
			
			Collection<Object[]> reasons = masterService.getAuditReasons(audit.getDplAuditNo());
			
			
			for(Object[] reason: reasons){
				DplReasonDto reasonDto = new DplReasonDto();
				reasonDto.setReason((String) reason[1]);
				reasonDto.setReasonCode((String) reason[0]);
				
				view.getReasons().add(reasonDto);
			}
			
			views.add(view);
		}
		
		return views;
	}
	
	public List<String> validateProduct(ProductsDto product){
		List<String> errors = new ArrayList<String>();
		
		if(isEmpty(product.getPartNo())){
			errors.add("Part number is required.");
		}
		if(isEmpty(product.getPartDesc())){
			errors.add("Part description is required.");
		}
		if(product.getUnitPrice()!=null && !NumberUtils.isNumber(product.getUnitPrice().toString())){
			errors.add("Please enter valid unit price.");
		}
		if(product.getNetWeight()!=null && !NumberUtils.isNumber(product.getNetWeight().toString())){
			errors.add("Please enter valid net weight.");
		}
		
		return errors;
	}
	
	public ProductValue prepareProduct(ProductsDto dto){
		ProductValue product = new ProductValue();
		
		product.setCurrency(dto.getCurrency());
		product.setDescription(dto.getPartDesc());
		product.setExportClass(dto.getExportClass());
		product.setFlashPointTemp(dto.getFlashPointTemp());
		product.setHazmat(dto.isHazmat());
		if(dto.isHazmat()){
			product.setHazmatCode(dto.getHazmatCode());
			product.setHazmatContactName(dto.getHazmatContactName());
			product.setHazmatContactPhone(dto.getHazmatContactPhone());
			product.setHazmatDesc(dto.getHazmatDesc());
		}
		
		product.setImportClass(dto.getImportClass());
		product.setMaterialType(dto.getMaterialType());
		product.setModelNo(dto.getModelNo());
		product.setNetWeight(dto.getNetWeight());
		product.setPartNo(dto.getPartNo());
		product.setSbuCode(dto.getSbu());
		product.setSkuNo(dto.getSkuNo());
		product.setType(dto.getType());
		product.setUnitPrice(dto.getUnitPrice());
		product.setUomForPrice(dto.getUomForPrice());
		
		return product;
	}
	
	public List<ProductsDto> prepareProductTableView(Collection<Object[]> products){
		List<ProductsDto> views = new ArrayList<ProductsDto>();
		
		for(Object[] value: products){
			views.add(prepareProductDto(value));
		}
		
		return views;
	}
	
	public ProductsDto prepareProductDto(Object[] value){
		ProductsDto dto = new ProductsDto();
		
		dto.setPartNo((String) value[0]);
		dto.setExportClass((String) value[1]);
		dto.setImportClass((String) value[2]);
		dto.setModelNo((String) value[3]);
		if(value[5]!=null){
			String hazmat = (String) value[5];
			
			if("1".equals(hazmat)){
				dto.setHazmat(true);
			}else if("0".equals(hazmat)){
				dto.setHazmat(false);
			}
		}
		
		dto.setSbu((String) value[6]);
		dto.setSkuNo((String) value[7]);
		dto.setCreatedOn((Date) value[8]);
		dto.setDelete(dto.getPartNo());
		
		return dto;
	}
	
	public ProductsDto prepareProductValueDto(ProductValue value){
		ProductsDto dto = new ProductsDto();
		
		dto.setPartNo(value.getPartNo());
		dto.setPartDesc(value.getDescription());
		dto.setType(value.getType());
		dto.setMaterialType(value.getMaterialType());
		dto.setUnitPrice(value.getUnitPrice());
		dto.setCurrency(value.getCurrency());
		dto.setSkuNo(value.getSkuNo());
		dto.setModelNo(value.getModelNo());
		dto.setNetWeight(value.getNetWeight());
		dto.setUomForPrice(value.getUomForPrice());
		dto.setExportClass(value.getExportClass());
		dto.setImportClass(value.getImportClass());
		dto.setHazmat(value.isHazmat());
		
		if(value.isHazmat()){
			dto.setHazmatCode(value.getHazmatCode());
			dto.setHazmatContactName(value.getHazmatContactName());
			dto.setHazmatDesc(value.getHazmatDesc());
			dto.setFlashPointTemp(value.getFlashPointTemp());
			dto.setHazmatContactPhone(value.getHazmatContactPhone());
		}
		
		dto.setSbu(value.getSbuCode());
		dto.setSkuNo(value.getSkuNo());
		dto.setCreatedBy(value.getCreatedBy());
		dto.setCreatedOn(value.getCreatedOn());
		dto.setUpdatedBy(value.getUpdatedBy());
		dto.setUpdatedOn(value.getUpdatedOn());
		
		return dto;
	}
	
	public List<ProductManufacturerDto> prepareProductManufDtoList(Collection<ProductManufacturerValue> productManufValue){
		List<ProductManufacturerDto> result = new ArrayList<ProductManufacturerDto>(productManufValue.size());
		
		for(ProductManufacturerValue value : productManufValue){
			result.add(prepareProductManufDto(value));
		}
		
		return result;
	}
	
	public ProductManufacturerDto prepareProductManufDto(ProductManufacturerValue value){
		ProductManufacturerDto dto = new ProductManufacturerDto();
		
		dto.setAssistBalance(value.getAssistBalance());
		dto.setAssistValue(value.getAssistValue());
		dto.setContactName(value.getContactName());
		dto.setCooCode(value.getCooCode());
		dto.setCooName(value.getCooName());
		dto.setCreatedBy(value.getCreatedBy());
		dto.setCreatedOn(value.getCreatedOn());
		dto.setDefaultManufacturer(value.isDefaultManufacturer());
		dto.setEmail(value.getEmail());
		dto.setItemNo(value.getItemNo());
		dto.setManufactureId(value.getManufactureId());
		dto.setManufPartNo(value.getManufPartNo());
		dto.setModelNo(value.getModelNo());
		dto.setName(value.getName());
		dto.setPartNo(value.getPartNo());
		dto.setTelephone(value.getTelephone());
		dto.setUpdatedBy(value.getUpdatedBy());
		dto.setUpdatedOn(value.getUpdatedOn());
		dto.setDelete(dto.getItemNo()+"");
		
		return dto;
	}
	
	public List<String> validateProductManufacture(ProductManufacturerDto dto){
		List<String> errors = new ArrayList<String>();
		
		if(isEmpty(dto.getPartNo())){
			errors.add("Part nnumber is required.");
		}
		if(dto.getItemNo() <= 0){
			errors.add("Item nnumber is required.");
		}
		if(isEmpty(dto.getManufactureId())){
			errors.add("Manufacture id is required.");
		}
		
		if(!isEmpty(dto.getEmail()) && !ExportOperationHelper.validateEmail(dto.getEmail())){
			errors.add("Please enter valid email id.");
		}
		
		if(dto.getAssistBalance()!=null && !NumberUtils.isNumber(dto.getAssistBalance().toString())){
			errors.add("Please enter valid assist balance.");
		}
		if(dto.getAssistValue()!=null && !NumberUtils.isNumber(dto.getAssistValue().toString())){
			errors.add("Please enter valid assist value.");
		}
		
		return errors;
	}
	
	public ProductManufacturerValue prepareProductManufacture(ProductManufacturerDto dto){
		ProductManufacturerValue entity = new ProductManufacturerValue();
		
		entity.setAssistBalance(dto.getAssistBalance());
		entity.setAssistValue(dto.getAssistValue());
		entity.setContactName(dto.getContactName());
		entity.setCooCode(dto.getCooCode());
		entity.setCooName(dto.getCooName());
		entity.setDefaultManufacturer(dto.isDefaultManufacturer());
		entity.setEmail(dto.getEmail());
		entity.setItemNo(dto.getItemNo());
		entity.setManufactureId(dto.getManufactureId());
		entity.setManufPartNo(dto.getManufPartNo());
		entity.setModelNo(dto.getModelNo());
		entity.setName(dto.getName());
		entity.setPartNo(dto.getPartNo());
		entity.setTelephone(dto.getTelephone());
		
		return entity;
	}
	
	public List<ConsigneeDto> prepareConsigneeDtoList(Collection<ConsigneeValue> consignee){
		List<ConsigneeDto> consigneeList = new ArrayList<ConsigneeDto>();
		
		for(ConsigneeValue cons: consignee){
			ConsigneeDto dto = prepareConsigneeDto(cons);
			
			dto.setStatus(getStatus(dto.getStatus()));
			dto.setDelete(dto.getConsigneeId());
			//dto.setDelete("<a href='javascript:void(0)' ng-click='alert(122);deleteConsignee(consigneeId, $event)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>");
			
			/*if(dto.getConsigneeId() == null){
				dto.setConsigneeId("");
			}
			if(dto.getConsigneeName() == null){
				dto.setConsigneeName("");
			}
			if(dto.getConsigneeCountryName() == null){
				dto.setConsigneeCountryName("");
			}
			if(dto.getSalesPerson() == null){
				dto.setSalesPerson("");
			}
			if(dto.getTypeOfConsignee() == null){
				dto.setTypeOfConsignee("");
			}
			if(dto.getSbuCode() == null){
				dto.setSbuCode("");
			}
			if(dto.getStatus() == null){
				dto.setStatus("");
			}*/
			
			consigneeList.add(dto);
		}
		
		return consigneeList;
	}
	
	public ConsigneeDto prepareConsigneeDto(ConsigneeValue entity){
		ConsigneeDto dto = new ConsigneeDto();
		
		BeanUtils.copyProperties(entity, dto);
		
		/*dto.setActive(entity.getActive());
		dto.setAviationMilNucEndUse(entity.getAviationMilNucEndUse());
		dto.setConsigneeAddr1(entity.getConsigneeAddr1());
		dto.setConsigneeAddr2(entity.getConsigneeAddr2());
		dto.setConsigneeCity(entity.getConsigneeCity());
		dto.setConsigneeCountry(entity.getConsigneeCountry());
		dto.setConsigneeCountryName(entity.getConsigneeCountryName());
		dto.setConsigneeId(entity.getConsigneeId());
		dto.setConsigneeName(entity.getConsigneeName());
		dto.setConsigneeState(entity.getConsigneeState());
		dto.setOwnedByGovt(entity.getOwnedByGovt());
		dto.setPhone(entity.getPhone());
		dto.setSalesPerson(entity.getSalesPerson());
		dto.setSbuCode(entity.getSbuCode());
		dto.setStatus(entity.getStatus());
		dto.setTypeOfConsignee(entity.getTypeOfConsignee());
		dto.setUseForAes(entity.getUseForAes());
		dto.setUseForDos(entity.getUseForDos());
		dto.setUseForDoc(entity.getUseForDoc());
		dto.setZip(entity.getZip());*/
		
		return dto;
	}
	
	public List<String> validateConsignee(ConsigneeDto consignee, GenericService genericService) throws ApplicationException{
		List<String> errors = new ArrayList<String>();
		
		if(isEmpty(consignee.getConsigneeId())){
			errors.add("Consignee ID is required.");
		}else if(consignee.getReqType()!=null && !"Edit".equals(consignee.getReqType())){
			ConsigneeValue consigneeValue = (ConsigneeValue) genericService.findEntity(new ConsigneeValue(), consignee.getConsigneeId());
			if(consigneeValue!=null){
				errors.add("Consignee ID already present.");
			}
		}
		
		if(isEmpty(consignee.getConsigneeName())){
			errors.add("Consignee name is required.");
		}
		if(isEmpty(consignee.getConsigneeName())){
			errors.add("Consignee name is required.");
		}
		if(!isEmpty(consignee.getEmail()) && !ExportOperationHelper.validateEmail(consignee.getEmail())){
			errors.add("Please enter valid email id.");
		}
		
		return errors;
	}
	
	public String getProductSortCol(String fieldName){
	
        if("partNo".equals(fieldName)){
        	return "PART_NO";
        }
		if("exportClass".equals(fieldName)){
        	return "EXPORT_CLASS";
        }if("importClass".equals(fieldName)){
        	return "IMPORT_CLASS";
        }if("modelNo".equals(fieldName)){
        	return "MODEL_NO";
        }if("hazmat".equals(fieldName)){
        	return "HAZMAT";
        }if("sbu".equals(fieldName)){
        	return "SBU_CODE";
        }if("skuNo".equals(fieldName)){
        	return "SKU_NO";
        }if("createdOn".equals(fieldName)){
        	return "CREATED_ON";
        }
        
        return fieldName;
	}
	
	public String mapEntityCol(String fieldName){
		
		if("exporterCode".equals(fieldName)){
        	return "expCode";
        }
		if("department".equals(fieldName)){
        	return "expName";
        }if("stateName".equals(fieldName)){
        	return "expStateName";
        }if("countryName".equals(fieldName)){
        	return "expCountryName";
        }
        
        return fieldName;
		
	}
	
	public ConsigneeValue prepareConsignee(ConsigneeDto dto){
		ConsigneeValue value = new ConsigneeValue();
		
		BeanUtils.copyProperties(dto, value);
		return value;
	}
	
	public ScreeningEntity prepareScreenEntityConsignee(ConsigneeDto dto){
		ScreeningEntity screeningEntity = new ScreeningEntity();
		
		screeningEntity.setAddressLine1(dto.getConsigneeAddr1());
		screeningEntity.setAddressLine2(dto.getConsigneeAddr2());
		screeningEntity.setCity(dto.getConsigneeCity());
		screeningEntity.setCountry(dto.getConsigneeCountry());
		screeningEntity.setEntityId(dto.getConsigneeId());
		screeningEntity.setEntityType("Consignee");
		screeningEntity.setName(dto.getConsigneeName());
		screeningEntity.setOrgType("Organization");
		screeningEntity.setState(dto.getConsigneeState());
		screeningEntity.setZip(dto.getZip());
		
		return screeningEntity;
	}
	
	public SbuConfigValue getSbuConfigParam(String sbu, String param, CommonDataService commonDataService){
		SbuConfigValue confValue = null;
		try {
			confValue = commonDataService.getSbuConf(sbu, param);
			return confValue;
		} catch (Exception e) {
			logger.error("MasterHelper: Error reading sbu param : ", e);
			e.printStackTrace();
		}
		return null;
	}
	
	private String getStatus(String code){
		if(isEmpty(code))
			return null;
		
		if("T".equals(code)){
			return "To Be Screened";
		}else if("H".equals(code)){
			return "On Hold";
		}else if("A".equals(code)){
			return "Approved";
		}else if("D".equals(code)){
			return "Denied";
		}else if("O".equals(code)){
			return "Overriden";
		}
		
		return "";
	}
	
	private boolean isEmpty(String s){
		if(s==null || s.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
