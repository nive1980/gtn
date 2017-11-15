package com.gtn.helper;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.ExporterDto;
import com.gtn.dto.ProductsDto;

@Repository("masterServiceHelper")
public class MasterServiceHelper {

	public String prepareSearchExporterQuery(ExporterDto view, String mode, Object[] params){
		
		StringBuilder queryString = new StringBuilder();
		int arrayIndex = 0;
		
		if("DATA".equals(mode)){
			queryString.append("Select Object(p) from ExporterValue p where ");
		}else{
			queryString.append("Select Count(p.expCode) from ExporterValue p where ");
		}
		
		if(!isEmpty(view.getExporterCode())){
			queryString.append("upper(p.expCode) like upper('%").append(view.getExporterCode()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getStatus())){
			queryString.append("p.status = '").append(view.getStatus()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getDepartment())){
			queryString.append("upper(p.expName) like upper('%").append(view.getDepartment()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getAddrLine1())){
			queryString.append("p.expAddr1 like upper('%").append(view.getAddrLine1()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getAddrLine2())){
			queryString.append("p.expAddr2 like upper('%").append(view.getAddrLine2()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getCity())){
			queryString.append("p.expCity like upper('%").append(view.getCity()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getState())){
			queryString.append("p.expState = upper('").append(view.getState()).append("')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getCountry())){
			queryString.append("p.expCountry = upper('").append(view.getCountry()).append("')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getTelephone())){
			queryString.append("p.phone like upper('%").append(view.getTelephone()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getFax())){
			queryString.append("p.fax like upper('%").append(view.getFax()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getSbu())){
			queryString.append("p.sbu = upper('").append(view.getSbu()).append("')");
			queryString.append(" and ");
		}
		
		if(!isEmpty(view.getActiveStr())){
			if(view.isActive()){
				queryString.append("p.active = '").append("Y").append("'");
			}else{
				queryString.append("p.active = '").append("N").append("'");
			}
			
			queryString.append(" and ");
		}
		
		queryString.append(" p.expCode is not null ");
		
		
		if("DATA".equals(mode)){
			if(!isEmpty(view.getSortBy())){
				queryString.append("order by ").append(view.getSortBy()).append(" ").append(view.getDirection());
			}
		}
		
		return queryString.toString();
	}

	public String prepareSearchConsigneeQuery(ConsigneeDto view, String mode, Object[] params){
		
		StringBuilder queryString = new StringBuilder();
		int arrayIndex = 0;
		
		
		if("DATA".equals(mode)){
			queryString.append("Select Object(p) from ConsigneeValue p where ");
		}else{
			queryString.append("Select Count(p.consigneeId) from ConsigneeValue p where ");
		}
		
		if(!isEmpty(view.getConsigneeId())){
			queryString.append("upper(p.consigneeId) like upper('%").append(view.getConsigneeId()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getSbuCode())){
			queryString.append("p.sbuCode = '").append(view.getSbuCode()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeName())){
			queryString.append("upper(p.consigneeName) like upper('%").append(view.getConsigneeName()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getStatus())){
			queryString.append("p.status = '").append(view.getStatus()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeAddr1())){
			queryString.append("upper(p.consigneeAddr1) like upper('%").append(view.getConsigneeAddr1()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeAddr2())){
			queryString.append("upper(p.consigneeAddr2) like upper('%").append(view.getConsigneeAddr2()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeCity())){
			queryString.append("upper(p.consigneeCity) like upper('%").append(view.getConsigneeCity()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeState())){
			queryString.append("upper(p.consigneeState) = upper('").append(view.getConsigneeState()).append("')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getConsigneeCountry())){
			queryString.append("p.consigneeCountry = '").append(view.getConsigneeCountry()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getPhone())){
			queryString.append("upper(p.phone) like upper('%").append(view.getPhone()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getActive())){
			queryString.append("p.active = '").append(view.getActive()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getTypeOfConsignee())){
			queryString.append("p.typeOfConsignee = '").append(view.getTypeOfConsignee()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getSalesPerson())){
			queryString.append("upper(p.salesPerson) like upper('%").append(view.getSalesPerson()).append("%')");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getOwnedByGovt())){
			queryString.append("p.ownedByGovt = '").append(view.getOwnedByGovt()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getAviationMilNucEndUse())){
			queryString.append("p.aviationMilNucEndUse = '").append(view.getAviationMilNucEndUse()).append("'");
			queryString.append(" and ");
		}
		
		
		if(!isEmpty(view.getUseForAes())){
			queryString.append("p.useForAes = '").append(view.getUseForAes()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getUseForDos())){
			queryString.append("p.useForDos = '").append(view.getUseForDos()).append("'");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getUseForDoc())){
			queryString.append("p.useForDoc = '").append(view.getUseForDoc()).append("'");
			queryString.append(" and ");
		}
		
		queryString.append(" p.consigneeId is not null and p.consigneeId != '' ");
		
		if("DATA".equals(mode)){
			if(!isEmpty(view.getSortBy())){
				queryString.append("order by ").append(view.getSortBy()).append(" ").append(view.getDirection());
			}
		}
		
		return queryString.toString();
	}
	
	
	public String prepareSearchProductQuery(ProductsDto view, String mode, Object[] params){
		
		StringBuilder queryString = new StringBuilder();
		int arrayIndex = 0;
		
		if("DATA".equals(mode)){
			queryString.append("select distinct p.part_no, p.export_class, p.import_class, p.MODEL_NO, p.NET_WEIGHT, p.HAZMAT, p.SBU_CODE, p.SKU_NO, p.CREATED_ON from PRODUCTS p left join PRODUCTS_MANUFACTURER m on m.product_id = p.part_no where ");
		}else{
			queryString.append("select count(distinct(p.part_no)) from PRODUCTS p left join PRODUCTS_MANUFACTURER m on m.product_id = p.part_no where ");
		}
		
		
		if(!isEmpty(view.getPartNo())){
			queryString.append("upper(p.PART_NO) like upper('%").append(view.getPartNo()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getSbu())){
			queryString.append("p.SBU_CODE = '").append(view.getSbu()).append("' ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getType())){
			queryString.append("p.PART_TYPE = '").append(view.getType()).append("' ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getMaterialType())){
			queryString.append("upper(p.MATERIAL_TYPE) like upper('%").append(view.getMaterialType()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getSkuNo())){
			queryString.append("upper(p.SKU_NO) like upper('%").append(view.getSkuNo()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getModelNo())){
			queryString.append("upper(p.MODEL_NO) like upper('%").append(view.getModelNo()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getExportClass())){
			queryString.append("upper(p.EXPORT_CLASS) like upper('%").append(view.getExportClass()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getImportClass())){
			queryString.append("upper(p.IMPORT_CLASS) like upper('%").append(view.getImportClass()).append("%') ");
			queryString.append(" and ");
		}
		if(!isEmpty(view.getPartDesc())){
			queryString.append("upper(p.PART_DESC) like upper('%").append(view.getPartDesc()).append("%') ");
			queryString.append(" and ");
		}
		
		
		if(!isEmpty(view.getHazmatStr())){
			if("Y".equals(view.getHazmatStr())){
				queryString.append("p.HAZMAT  = '1'");
			}else{
				queryString.append("p.HAZMAT  = '0'");
			}
			
			queryString.append(" and ");
		}
		
		
		if(view.getManufacture()!=null){
			if(!isEmpty(view.getManufacture().getManufactureId())){
				queryString.append("upper(m.MANUF_ID) like upper('%").append(view.getManufacture().getManufactureId()).append("%') ");
				queryString.append(" and ");
			}
			if(!isEmpty(view.getManufacture().getName())){
				queryString.append("upper(m.NAME) like upper('%").append(view.getManufacture().getName()).append("%') ");
				queryString.append(" and ");
			}
			if(!isEmpty(view.getManufacture().getCooCode())){
				queryString.append("upper(m.COO_CODE) = upper('").append(view.getManufacture().getCooCode()).append("') ");
				queryString.append(" and ");
			}
			if(!isEmpty(view.getManufacture().getContactName())){
				queryString.append("upper(m.CONTACT_NAME) = upper('").append(view.getManufacture().getContactName()).append("') ");
				queryString.append(" and ");
			}
		}
				
		queryString.append(" p.PART_NO is not null ");
		
		if("DATA".equals(mode)){
			if(!isEmpty(view.getSortBy())){
				queryString.append(" order by ").append(view.getSortBy()).append(" ").append(view.getDirection());
			}
		}
		
		return queryString.toString();
	}
	
	public boolean isEmpty(Object object) {

		if (object == null)
			return true;

		if(object instanceof Collection)
			return ((Collection)object).isEmpty();
		
		if (object instanceof String){
			String s = (String) object;
			if("null".equals(s)){
				return true;
			}else if("undefined".equals(s)){
				return true;
			}
		}
		
		if (object instanceof String)
			return (((String) object).trim().length() > 0) ? false : true;
		
		return false;
	}
	
	public int getStartPage(int pageNo, int chunkSize){
		int start = -1;
		
			//int start = view.getPage() * view.getLimit();
			//int nextLimit = view.getLimit() + start;
			
		if(pageNo <= 1){
			start = 0;
		}else if(pageNo > 1){
			start = (pageNo - 1) * chunkSize;
		}
		
		if(start == -1){
			start = 0;
		}
		
		return start;
	}
	
	private String getDir(String sortBy){
		if(sortBy.startsWith("-")){
			return "DESC";
		}else{
			return "ASC";
		}
	}

	private String trimStr(String s){
		if(s.startsWith("-")){
			return s.substring(1);
		}else{
			return s;
		}
	}
}
