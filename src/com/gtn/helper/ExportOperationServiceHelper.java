package com.gtn.helper;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.gtn.dto.ShipmentDto;
import com.gtn.util.DateUtil;

@Component("exportOperationServiceHelper")
public class ExportOperationServiceHelper {

	public String prepareSearchShipmentQuery(ShipmentDto view, String mode, Object[] params){
		
		StringBuilder queryString = new StringBuilder();
		int arrayIndex = 0;
		
		if("DATA".equals(mode)){
			queryString.append("select Object(c) from Shipment c where ");
		}else{
			queryString.append("select COUNT(c) from Shipment c where  ");
		}
		
		
		if(view.getExportDate()!=null){
			queryString.append("c.exportDate = '").append(DateUtil.format(view.getExportDate(), "yyyy-MM-dd"));
			queryString.append("' and ");
		}
		if(!isEmpty(view.getCountryOfExportCode())){
			queryString.append("c.countryOfExportCode = '").append(view.getCountryOfExportCode());
			queryString.append("' and ");
		}
		
		if(!isEmpty(view.getShipmentNo())){
			queryString.append("upper(c.shipmentNo) like '%").append(view.getShipmentNo().toUpperCase()).append("%");
			queryString.append("' and ");
		}
		if(!isEmpty(view.getItn())){
			queryString.append("upper(c.itn) like '%").append(view.getItn().toUpperCase()).append("%");
			queryString.append("' and ");
		}
		
		if(!isEmpty(view.getCountryOfUltConsigneeCode())){
			queryString.append("c.countryOfUltConsigneeCode = '").append(view.getCountryOfUltConsigneeCode());
			queryString.append("' and ");
		}
		if(!isEmpty(view.getSbuCode())){
			queryString.append("c.sbuCode = '").append(view.getSbuCode());
			queryString.append("' and ");
		}
		
		queryString.append(" c.shipmentNo is not null ");
		
		
		if("DATA".equals(mode)){
			if(!isEmpty(view.getSortBy())){
				queryString.append("order by ").append(view.getSortBy()).append(" ").append(view.getDirection());
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
}
