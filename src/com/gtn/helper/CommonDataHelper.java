package com.gtn.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gtn.exception.ApplicationException;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.Country;
import com.gtn.model.ExporterValue;
import com.gtn.model.FreightForwarderValue;
import com.gtn.model.ModeOfTransportValue;
import com.gtn.model.SchBValue;
import com.gtn.service.CommonDataService;
import com.gtn.service.GenericService;

@Component
public class CommonDataHelper {

	@Autowired
	private CommonDataService service;
	
	@Autowired
	private GenericService genericService;
	
	public boolean validateCountry(String countryCode) throws ApplicationException{
		/*boolean countryValid = service.validateCountry(countryCode);
		return countryValid;*/
		
		Country country = (Country) genericService.findEntity(new Country(), countryCode);
		if(country == null)
			return false;
		else
			return true;
	}
	
	public boolean validateMot(String motCode) throws ApplicationException{
		/*boolean motValid = service.validateCountry(motCode);
		return motValid;*/
		
		ModeOfTransportValue mot = (ModeOfTransportValue) genericService.findEntity(new ModeOfTransportValue(), motCode);
		if(mot == null)
			return false;
		else
			return true;
	}
	
	public boolean validateExporter(String exporterCode) throws ApplicationException{
		/*boolean exporterValid = service.validateExporter(exporterCode);
		return exporterValid;*/
		
		ExporterValue exporter = (ExporterValue) genericService.findEntity(new ExporterValue(), exporterCode);
		if(exporter == null)
			return false;
		else
			return true;
	}
	
	public boolean validateConsignee(String consigneeCode) throws ApplicationException{
		/*boolean consigneeValid = service.validateConsignee(consigneeCode);
		return consigneeValid;*/
		
		ConsigneeValue consignee = (ConsigneeValue) genericService.findEntity(new ConsigneeValue(), consigneeCode);
		if(consignee == null)
			return false;
		else
			return true;
	}
	
	public boolean validateFF(String ffCode) throws ApplicationException{
		/*boolean ffValid = service.validateConsignee(ffCode);
		return ffValid;*/
		
		FreightForwarderValue ff = (FreightForwarderValue) genericService.findEntity(new FreightForwarderValue(), ffCode);
		if(ff == null)
			return false;
		else
			return true;
	}
	
	public boolean validateSchb(String schbCode) throws ApplicationException{
		/*boolean schbValid = service.validateSchb(schbCode);
		return schbValid;*/
		
		SchBValue schb = (SchBValue) genericService.findEntity(new SchBValue(), schbCode);
		if(schb == null)
			return false;
		else
			return true;
	}
	/**
	 * check if a string is empty
	 * @params
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean checkContainSpecialChar(String inp){
		
		Pattern special = Pattern.compile ("[(+?%#&~`@^*(){}\\[\\]|:;',)]");
		Matcher hasSpecial = special.matcher(inp);
		
		return hasSpecial.find();
	}
	
	public boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) && c!=' ') {
	            return false;
	        }
	    }

	    return true;
	}
}
