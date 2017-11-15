package com.gtn.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gtn.dto.ValidationErrorDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.SbuConfigValue;
import com.gtn.model.Shipment;
import com.gtn.model.ShipmentItem;
import com.gtn.service.CommonDataService;
import com.gtn.service.ExportOperationService;
import com.gtn.util.Constants;



@Component
public class AESDataValidation {

	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ExportOperationService exportService;
	
	@Autowired
	private CommonDataHelper helper;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AESDataValidation.class);
	
	/**
	 * This method is used to Validate AES filings
	 * @param shipment
	 * @param sbu
	 * @return
	 * @throws ApplicationException 
	 */
	public Map<String, List<String>> validateAesFiling(Shipment shipment, String sbu) throws ApplicationException{
		List<String> errors = new ArrayList<String>();
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		
		if(shipment == null){
			return null;
		}
		
		//validate shipment data
		List<String> shipmentErrors = validateShipmentData(shipment, sbu);
		errors.addAll(shipmentErrors);
		errorMap.put("shipment", shipmentErrors);
		
		//validate invoice data
		List<String> invoiceErrors =validateInvoiceData(shipment, sbu);
		errors.addAll(invoiceErrors);
		errorMap.put("invoice", invoiceErrors);
		
		//validate Item data
		List<String> itemErrors =validateItemData(shipment, sbu);
		errors.addAll(itemErrors);
		errorMap.put("items", itemErrors);
		
		//validate AES
		List<String> aesErrors =validateAES(shipment, sbu);
		errors.addAll(aesErrors);
		errorMap.put("aes", aesErrors);
		
		return errorMap;
	}
	
	/**
	 * This method validates Shipment data for AES
	 * @param shipment
	 * @param errors
	 * @param sbu
	 * @throws ApplicationException 
	 */
	private List<String> validateShipmentData(Shipment shipment, String sbu) throws ApplicationException{
		 List<String> errors = new ArrayList<String>();
		 
		String aesValidation = getEnhancedValidationFlag(sbu);
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesShipmentData(shipment, errors);
		}
		
		if(!isEmpty(shipment.getExporterCode())){
			boolean validExporter = helper.validateExporter(shipment.getCountryOfExportCode());
			if(!validExporter){
				errors.add("Invalid Exporter Code.");
			}
		}
		if(!isEmpty(shipment.getUltConsigneeCode())){
			boolean validConsignee = helper.validateConsignee(shipment.getUltConsigneeCode());
			if(!validConsignee){
				errors.add("Invalid Ultimate Consignee Code.");
			}
		}
		if(!isEmpty(shipment.getInterConsigneeCode())){
			boolean validInterConsignee = helper.validateConsignee(shipment.getInterConsigneeCode());
			if(!validInterConsignee){
				errors.add("Invalid Intermediate Consignee Code.");
			}
		}
		
		if(!isEmpty(shipment.getFreightForwaderCode())){
			boolean validFF = helper.validateConsignee(shipment.getFreightForwaderCode());
			if(!validFF){
				errors.add("Invalid Forwarding Agent Code.");
			}
		}
		
		try{
			boolean validShipCtry = helper.validateCountry(shipment.getCountryOfExportCode());
			if(!validShipCtry){
				errors.add("Invalid Country Shippped From.");
			}
		}catch(Exception e){
			logger.info("AESDataValidation: Error validating shipped from country code.");
			e.printStackTrace();
		}
		
		try{
			boolean validMot = helper.validateMot(shipment.getMotCode());
			if(!validMot){
				errors.add("Invalid Mode of Transport.");
			}
		}catch(Exception e){
			logger.info("AESDataValidation: Error validating mode of transport code.");
			e.printStackTrace();
		}
		return errors;
	}
	
	/**
	 * this method validates advance shipment date for AES
	 * @param shipment
	 * @param errors
	 */
	private void validateAdvAesShipmentData(Shipment shipment, List<String> errors){
		if(isEmpty(shipment.getCountryOfUltConsigneeCode())){
			errors.add("Missing or Incomplete Country of Ultimate destination details.");
		}
		if(isEmpty(shipment.getUltConsigneeName()) || isEmpty(shipment.getUltConsigneeAddrLine1()) ||
				isEmpty(shipment.getUltConsigneeCity()) || isEmpty(shipment.getUltConsigneeCountryCode())){
			errors.add("Missing or Incomplete Ultimate Consignee details.");
		}
		if(isEmpty(shipment.getFreightForwaderName()) || isEmpty(shipment.getFreightForwaderAddrLine1()) ||
				isEmpty(shipment.getFreightForwaderCity()) || isEmpty(shipment.getFreightForwaderCountryCode())){
			errors.add("Missing or Incomplete Freight Forwarder details.");
		}
	}
	
	/**
	 * This method validates Invoice data
	 * @param shipment
	 * @param errors
	 * @param sbu
	 * @throws ApplicationException 
	 */
	private List<String> validateInvoiceData(Shipment shipment, String sbu) throws ApplicationException{
		List<String> errors = new ArrayList<String>();
		
		String aesValidation = getEnhancedValidationFlag(sbu);
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesInvoiceData(shipment, errors);
		}
		
		if(!isEmpty(shipment.getShipToCode())){
			boolean validShipTo = helper.validateConsignee(shipment.getShipToCode());
			if(!validShipTo){
				errors.add("Invalid Ship To Party.");
			}
		}
		
		if(!isEmpty(shipment.getBillToCode())){
			boolean validBillTo = helper.validateConsignee(shipment.getBillToCode());
			if(!validBillTo){
				errors.add("Invalid Bill To Party.");
			}
		}
		
		if(!isEmpty(shipment.getFreightForwaderCode())){
			boolean validFF = helper.validateConsignee(shipment.getFreightForwaderCode());
			if(!validFF){
				errors.add("Invalid Forwarding Agent Code.");
			}
		}
		
		if(!isEmpty(shipment.getCountryOfUltConsigneeCode())){
			boolean validUltCongCtry = helper.validateCountry(shipment.getCountryOfUltConsigneeCode());
			if(!validUltCongCtry){
				errors.add("Invalid Country of Ultimate Destination.");
			}
		}
		return errors;
	}
	
	private void validateAdvAesInvoiceData(Shipment shipment, List<String> errors){
		if(isEmpty(shipment.getShipToName()) || isEmpty(shipment.getShipToAddr1()) ||
				isEmpty(shipment.getShipToCountry()) || isEmpty(shipment.getShipToCity())){
			errors.add("Missing or Incomplete Ship To details.");
		}
		
		if(isEmpty(shipment.getBillToName()) || isEmpty(shipment.getBillToAddr1()) ||
				isEmpty(shipment.getBillToCountry()) || isEmpty(shipment.getBillToCity())){
			errors.add("Missing or Incomplete Bill To details.");
		}
		
		if(isEmpty(shipment.getFreightForwaderName()) || isEmpty(shipment.getFreightForwaderAddrLine1()) ||
				isEmpty(shipment.getFreightForwaderCity()) || isEmpty(shipment.getFreightForwaderCountryCode())){
			errors.add("Missing or Incomplete Freight Forwarder details.");
		}
	}
	
	
	public List<String> validateItemData(Shipment shipment, String sbu) throws ApplicationException{
		List<String> errors = new ArrayList<String>();
		String aesValidation = getEnhancedValidationFlag(sbu);	
		Collection<ShipmentItem> items = exportService.getShipmentItem(shipment.getId());
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesItemData(shipment, errors, items);
		}
		
		if(items!=null && items.size() > 0){
			Iterator<ShipmentItem> it = items.iterator();
			
			while(it.hasNext()){
				ShipmentItem item = it.next();
				
				if(!isEmpty(item.getCoo()) && !helper.validateCountry(item.getCoo())){
					errors.add("Country of Origin is invalid for part number "+item.getPartNo());
				}
				
				if(!isEmpty(item.getHtsSchedule()) && !helper.validateSchb(item.getHtsSchedule())){
					errors.add("HTS/Schedule B is invalid for part number "+item.getPartNo());
				}
			}
		}
		return errors;
	}
	
	
	public void validateAdvAesItemData(Shipment shipment, List<String> errors, Collection<ShipmentItem> items) throws ApplicationException{
		
		if(items!=null && items.size() > 0){
			Iterator<ShipmentItem> it = items.iterator();
			
			while(it.hasNext()){
				ShipmentItem item = it.next();
				if(isEmpty(item.getPartNo())){
					errors.add("Missing or Incomplete Part Number details for part number "+item.getPartNo());
				}
				if(isEmpty(item.getPartDescription())){
					errors.add("Missing or Incomplete Part Description details for part number "+item.getPartNo());
				}
				if(isEmpty(item.getEccn())){
					errors.add("Missing or Incomplete Classification Number details for part number "+item.getPartNo());
				}
				if(isEmpty(item.getHtsSchedule())){
					errors.add("Missing or Incomplete HTS/ScheduleB Number details for part number "+item.getPartNo());
				}
				if(isEmpty(item.getCoo())){
					errors.add("Missing or Incomplete Country of Origin details for part number "+item.getPartNo());
				}
			}
		}
		
	}
	
	
	private List<String> validateAES(Shipment shipment, String sbu) throws ApplicationException{
		if(shipment==null)
			return null;
		
		List<String> errors = new ArrayList<String>();
		
		if(isEmpty(shipment.getAesOption())){
			errors.add("AES Option is missing.");
		}
		
		if(shipment.getShipmentNo().length() > 17){
			errors.add("ShipmentNo length is greater than 17 characters.");
		}
		
		if(helper.checkContainSpecialChar(shipment.getShipmentNo())){
			errors.add("Shipment No can have only   - / . special characters.");
		}
		
		if(!isEmpty(shipment.getAesOption())){
			//pending - state of origin check(present in custom filing screen)
			
			if(isEmpty(shipment.getUsppiPortOfExport())){
				errors.add("Port of Export is missing.");
			}
			if(isEmpty(shipment.getCountryOfUltConsigneeCode())){
				errors.add("Country Of Ultimate Destination Code is missing.");
			}
			if(isEmpty(shipment.getModeOfTransportCode())){
				errors.add("Mode of Transportation is missing.");
			}
			
			//pending - 4.e - Need to integrate mode of transport
			
			if(shipment.getExportDate() == null){
				errors.add("Date of Export is missing.");
			}
			
			if(isEmpty(shipment.getExporterName())){
				errors.add("Exporter / USPPI Details are missing.");
			}else{
				if(isEmpty(shipment.getExporterAddressLine1())){
					errors.add("Exporter / USPPI Address1 is missing.");
				}if(isEmpty(shipment.getExporterCity())){
					errors.add("Exporter / USPPI City is missing.");
				}if(isEmpty(shipment.getExporterState())){
					errors.add("Exporter / USPPI State is missing.");
				}if(isEmpty(shipment.getExporterZip())){
					errors.add("Exporter / USPPI Zip is missing.");
				}if(isEmpty(shipment.getUsppiExporterId())){
					errors.add("Exporter / USPPI Filer ID (USPPI Number) is missing.");
				}if(isEmpty(shipment.getUsppiExporterIdType())){
					errors.add("Exporter / USPPI Filer ID Type is missing.");
				}if(!isEmpty(shipment.getUsppiExporterIdType()) && "D".equals(shipment.getUsppiExporterIdType())){
					errors.add("Exporter / USPPI Filer ID Type must be EIN or Foreign.");
				}if(isEmpty(shipment.getExporterLastName())){
					errors.add("Exporter / USPPI Contact Last Name is missing.");
				}if(isEmpty(shipment.getExporterFirstName())){
					errors.add("Exporter / USPPI Contact First Name is missing.");
				}if(isEmpty(shipment.getExporterPhoneNo())){
					errors.add("Exporter / USPPI Contact Phone number is missing.");
				}else{
					if(shipment.getExporterPhoneNo().length() > 10){
						errors.add("Exporter / USPPI Contact Phone number must contain 10 characters.");
					}if(!shipment.getExporterPhoneNo().matches("[0-9]+")){
						errors.add("Exporter / USPPI Contact Phone number contains invalid characters.");
					}
				}
			}// if exporter name is present
			
			if(isEmpty(shipment.getUltConsigneeName())){
				errors.add("Ultimate Consignee Name is missing.");
			}else{
				if(isEmpty(shipment.getUltConsigneeAddrLine1())){
					errors.add("Ultimate Consignee Address Line1 is missing.");
				}if(isEmpty(shipment.getUltConsigneeCity())){
					errors.add("Ultimate Consignee City is missing.");
				}if(isEmpty(shipment.getUltConsigneeCountryCode())){
					errors.add("Ultimate Consignee Country is missing.");
				}else if("MX".equals(shipment.getUltConsigneeCountryCode()) && isEmpty(shipment.getUltConsigneeState())){
					errors.add("Ultimate Consignee State Code required for Ultimate Consignee Country Code MX.");
				}
				
				if(isEmpty(shipment.getUltConsigneeName()) && !isEmpty(shipment.getUltConsigneePhone())){
					errors.add("Ultimate Consignee Contact Name is missing.");
				}
				if(!isEmpty(shipment.getUltConsigneeName()) && isEmpty(shipment.getUltConsigneePhone())){
					errors.add("Ultimate Consignee Contact Phone number is missing.");
				}
				
				if(!isEmpty(shipment.getUltConsigneeName()) && !isEmpty(shipment.getUltConsigneePhone())){
					if(shipment.getUltConsigneePhone().length() > 11){
						errors.add("Ultimate Consignee Contact Phone number Length exceeds 11 digits.");
					}if(!shipment.getUltConsigneePhone().matches("[0-9]+")){
						errors.add("Ultimate Consignee Contact Phone number contains invalid characters.");
					}
				}
				
				//4.j.8. pending - FTR effective date
	
			}// if ultimate consigne name is present
			
			if(!isEmpty(shipment.getInterConsigneeName())){

				if(isEmpty(shipment.getInterConsigneeAddrLine1())){
					errors.add("Intermediate Consignee Address Line1 is missing.");
				}if(isEmpty(shipment.getInterConsigneeCity())){
					errors.add("Intermediate Consignee City is missing.");
				}if(isEmpty(shipment.getInterConsigneeCountryCode())){
					errors.add("Intermediate Consignee Country is missing.");
				}else if("MX".equals(shipment.getInterConsigneeCountryCode()) && isEmpty(shipment.getInterConsigneeState())){
					errors.add("Intermediate Consignee State Code required for Intermediate Consignee Country Code MX.");
				}
				
				if(isEmpty(shipment.getInterConsigneeName()) && !isEmpty(shipment.getInterConsigneePhone())){
					errors.add("Intermediate Consignee Contact Name is missing.");
				}
				if(!isEmpty(shipment.getInterConsigneeName()) && isEmpty(shipment.getInterConsigneePhone())){
					errors.add("Intermediate Consignee Contact Phone number is missing.");
				}
				
				if(!isEmpty(shipment.getInterConsigneeName()) && !isEmpty(shipment.getInterConsigneePhone())){
					if(shipment.getInterConsigneePhone().length() > 11){
						errors.add("Intermediate Consignee Contact Phone number Length exceeds 11 digits.");
					}if(!shipment.getInterConsigneePhone().matches("[0-9]+")){
						errors.add("Intermediate Consignee Contact Phone number contains invalid characters.");
					}
				}
				
			}// if inter consignee name present
			
			if(!isEmpty(shipment.getFreightForwaderName()) && "1".equals(shipment.getTransmitToAes())){
				if(isEmpty(shipment.getFreightForwaderAddrLine1())){
					errors.add("Forwarding Agent Address Line1 is missing.");
				}if(isEmpty(shipment.getFreightForwaderCity())){
					errors.add("Forwarding Agent City is missing.");
				}if(isEmpty(shipment.getFreightForwaderState())){
					errors.add("Forwarding Agent State is missing.");
				}if(isEmpty(shipment.getFreightForwaderCountryCode())){
					errors.add("Forwarding Agent Country is missing.");
				}if(isEmpty(shipment.getFreightForwaderZip())){
					errors.add("Forwarding Agent Zip Code is missing.");
				}if(isEmpty(shipment.getFreightForwaderName())){
					errors.add("Forwarding Agent Contact Name is missing.");
				}else{
					if(isEmpty(shipment.getFreightForwaderLastName()) || !helper.isAlpha(shipment.getFreightForwaderLastName())){
						errors.add("Forwarding Agent Contact Last Name is missing.");
					}
				}
				if(isEmpty(shipment.getFreightForwaderPhone())){
					errors.add("Forwarding Agent Contact Phone is missing.");
				}else{
					if(shipment.getFreightForwaderPhone().length() > 10){
						errors.add("Forwarding Agent Contact Phone Number must contain 10 characters.");
					}
					if(!shipment.getFreightForwaderPhone().matches("[0-9]+")){
						errors.add("Forwarding Agent Primary Contact Phone Number contains invalid characters.");
					}
				}
				
				if(isEmpty(shipment.getFfFilerIdNo())){
					errors.add("Forwarding Agent Filer ID is missing.");
				}else if(shipment.getFfFilerIdNo().length() != 9 || shipment.getFfFilerIdNo().length() != 11){
					errors.add("Forwarding Agent ID number must contain 9 or 11 characters.");
				}
				if(isEmpty(shipment.getFfFilerIdType())){
					errors.add("Forwarding Agent Filer Type is missing.");
				}
			}// if FF name is present
			
			if(isEmpty(shipment.getRelatedPartTransaction())){
				errors.add("No Parties To Transaction is selected.");
			}
			if(isEmpty(shipment.getShipmentRefNo())){
				errors.add("Shipment Reference No is missing.");
			}
			
			
			
			
			if(isEmpty(shipment.getHazdarous())){
				errors.add("Hazardous Materials option is not selected.");
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("10") || shipment.getMotCode().equals("11"))){
				if(isEmpty(shipment.getPortOfUnlandingCode())){
					errors.add("Port of Unloading is required for the selected Method of Transportation.");
				}
				if(isEmpty(shipment.getTransportRefNo())){
					errors.add("Transport Reference Number is required for the selected Method of Transportation.");
				}
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("40") || shipment.getMotCode().equals("41"))
					&& !isEmpty(shipment.getTransportRefNo())){
				Pattern p = Pattern.compile("\\w{3}-\\w{8}");
				Matcher m = p.matcher(shipment.getTransportRefNo());
				
				if(m.find()){
					errors.add("Transportation Ref. No needs to be in the format XXX-XXXXXXXX when Mode of Transport is Air.");
				}				
			}
			if(!isEmpty(shipment.getCountryOfUltConsigneeCode()) && "PR".equals(shipment.getUltConsigneeCountryCode()) && isEmpty(shipment.getPortOfUnlandingCode())){
				errors.add("When Country Of Ultimate Destination is Puerto Rico, Port of Unloading is required.");
			}
			if(!isEmpty(shipment.getUsppiExporterIdType()) && "E".equals(shipment.getUsppiExporterIdType()) && 
					!isEmpty(shipment.getUsppiExporterId()) && helper.checkContainSpecialChar(shipment.getUsppiExporterId())){
				errors.add("EIN number for Freight Forwarder should not have special characters.");
			}
			if(!isEmpty(shipment.getTransmitToAes()) && "1".equals(shipment.getTransmitToAes()) && 
					!isEmpty(shipment.getFfFilerIdNo()) && helper.checkContainSpecialChar(shipment.getFfFilerIdNo())){
				errors.add("EIN number for Freight Forwarder should not have special characters.");
			}
			if(!isEmpty(shipment.getEntryNumber()) && shipment.getEntryNumber().length() < 9){
				errors.add("Entry No needs to be greater than or equal to 9 characters.");
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("10") || shipment.getMotCode().equals("11")) &&
					isEmpty(shipment.getConveyanceName())){
				errors.add("Conveyance name is required for the selected mode of transport.");
			}
						
			//items validation start
			Collection<ShipmentItem> items = exportService.getShipmentItem(shipment.getId());
			
			if(items!=null && items.size() > 0){
				
				Iterator<ShipmentItem> it = items.iterator();
				
				while(it.hasNext()){
					ShipmentItem item = it.next();
					
					if(item.getNetWeight() == null){
						errors.add("Primary Net Quantity is missing for item no : "+item.getItemNo());
					}
					
					//pending - EEI value
					//pending - license code
					//pending - if a license exception or ITAR exemption is present and license code is missing then add error 'License Code is missing.'
					//pending - if the license exception and license code is missing then  add error 'License Code is missing.'
					
					if(!isEmpty(shipment.getMotCode())){						
						if("10".equals(shipment.getMotCode()) || "11".equals(shipment.getMotCode()) || "12".equals(shipment.getMotCode())
								|| "20".equals(shipment.getMotCode()) || "21".equals(shipment.getMotCode()) || "30".equals(shipment.getMotCode())
								|| "31".equals(shipment.getMotCode()) || "40".equals(shipment.getMotCode()) || "41".equals(shipment.getMotCode())){
							if(isEmpty(item.getGrossWeight()) || item.getGrossWeight() <= 0){
								errors.add("Gross Weight is missing for item no : "+item.getItemNo());
							}
						}else{
							if(!isEmpty(item.getGrossWeight())){
								errors.add("Gross Weight is not allowed for the selected mode of transportation for item no : "+item.getItemNo());
							}
						}
					}
					
					//pending - primary unit 5.h
					//pending - license code - 5.i
					
				}
				
			}//if items found
			
			
		}// if AES option is selected
		return errors;
	}
	
	private String getEnhancedValidationFlag(String sbu){
		SbuConfigValue confValue = null;
		try {
			confValue = commonDataService.getSbuConf(sbu, Constants.AES_ADVANCE_VALIDATION_FLAG);
			if(confValue!=null){
				if("Y".equalsIgnoreCase(confValue.getParamValue())){
					return "Y";
				}else{
					return "N";
				}
			}else{
				logger.info("AESDataValidation: Error AES_ADVANCE_VALIDATION_FLAG not found.");
			}
		} catch (Exception e) {
			logger.error("AESDataValidation: Error reading AES_ADVANCE_VALIDATION_FLAG.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isEmpty(Double s){
		if(s!=null){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	
	
	///////////////////////// Validation Results for UI ///////////////////////
	/**
	 * This method is used to Validate AES filings
	 * @param shipment
	 * @param sbu
	 * @return
	 * @throws ApplicationException 
	 */
	public Map<String, List<ValidationErrorDto>> validateAesFilingUI(Shipment shipment, String sbu) throws ApplicationException{
		List<ValidationErrorDto> errors = new ArrayList<ValidationErrorDto>();
		Map<String, List<ValidationErrorDto>> errorMap = new HashMap<String, List<ValidationErrorDto>>();
		
		if(shipment == null){
			return null;
		}
		
		//validate shipment data
		List<ValidationErrorDto> shipmentErrors = validateShipmentDataUI(shipment, sbu);
		errors.addAll(shipmentErrors);
		errorMap.put("shipment", shipmentErrors);
		
		//validate invoice data
		List<ValidationErrorDto> invoiceErrors =validateInvoiceDataUI(shipment, sbu);
		errors.addAll(invoiceErrors);
		errorMap.put("invoice", invoiceErrors);
		
		//validate Item data
		List<ValidationErrorDto> itemErrors =validateItemDataUI(shipment, sbu);
		errors.addAll(itemErrors);
		errorMap.put("items", itemErrors);
		
		//validate AES
		List<ValidationErrorDto> aesErrors =validateAESUI(shipment, sbu);
		errors.addAll(aesErrors);
		errorMap.put("aes", aesErrors);
		
		return errorMap;
	}
	
	/**
	 * This method validates Shipment data for AES
	 * @param shipment
	 * @param errors
	 * @param sbu
	 * @throws ApplicationException 
	 */
	private List<ValidationErrorDto> validateShipmentDataUI(Shipment shipment, String sbu) throws ApplicationException{
		 List<ValidationErrorDto> errors = new ArrayList<ValidationErrorDto>();
		 
		String aesValidation = getEnhancedValidationFlag(sbu);
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesShipmentDataUI(shipment, errors);
		}
		
		if(!isEmpty(shipment.getExporterCode())){
			boolean validExporter = helper.validateExporter(shipment.getCountryOfExportCode());
			if(!validExporter){
				errors.add(populateError("createExporter", "exporter_code", "Invalid Exporter Code.", null, null, null));
				//
			}
		}
		if(!isEmpty(shipment.getUltConsigneeCode())){
			boolean validConsignee = helper.validateConsignee(shipment.getUltConsigneeCode());
			if(!validConsignee){
				errors.add(populateError("utlimateConsignee", "ult_consginee_code", "Invalid Ultimate Consignee Code.", null, null, null));
			}
		}
		if(!isEmpty(shipment.getInterConsigneeCode())){
			boolean validInterConsignee = helper.validateConsignee(shipment.getInterConsigneeCode());
			if(!validInterConsignee){
				errors.add(populateError("intermediateConsignee", "inter_consginee_code", "Invalid Intermediate Consignee Code.", null, null, null));
			}
		}
		
		if(!isEmpty(shipment.getFreightForwaderCode())){
			boolean validFF = helper.validateConsignee(shipment.getFreightForwaderCode());
			if(!validFF){
				errors.add(populateError("freightForwarder", "ff_code", "Invalid Forwarding Agent Code.", null, null, null));
			}
		}
		
		try{
			boolean validShipCtry = helper.validateCountry(shipment.getCountryOfExportCode());
			if(!validShipCtry){
				errors.add(populateError("createExporter", "export_country", "Invalid Country Shippped From.", null, null, null));
			}
		}catch(Exception e){
			logger.info("AESDataValidation: Error validating shipped from country code.");
			e.printStackTrace();
		}
		
		try{
			if(!isEmpty(shipment.getMotCode())){
				boolean validMot = helper.validateMot(shipment.getMotCode());
				if(!validMot){
					errors.add(populateError("createExporter", "mode_transport", "Invalid Mode of Transport.", null, null, null));
				}
			}
			
		}catch(Exception e){
			logger.info("AESDataValidation: Error validating mode of transport code.");
			e.printStackTrace();
		}
		return errors;
	}
	
	/**
	 * this method validates advance shipment date for AES
	 * @param shipment
	 * @param errors
	 */
	private void validateAdvAesShipmentDataUI(Shipment shipment, List<ValidationErrorDto> errors){
		if(isEmpty(shipment.getCountryOfUltConsigneeCode())){
			errors.add(populateError("", "", "Missing or Incomplete Country of Ultimate destination details.", null, null, null));
		}
		if(isEmpty(shipment.getUltConsigneeName()) || isEmpty(shipment.getUltConsigneeAddrLine1()) ||
				isEmpty(shipment.getUltConsigneeCity()) || isEmpty(shipment.getUltConsigneeCountryCode())){
			errors.add(populateError("utlimateConsignee", "", "Missing or Incomplete Ultimate Consignee details.", null, null, null));
		}
		if(isEmpty(shipment.getFreightForwaderName()) || isEmpty(shipment.getFreightForwaderAddrLine1()) ||
				isEmpty(shipment.getFreightForwaderCity()) || isEmpty(shipment.getFreightForwaderCountryCode())){
			errors.add(populateError("freightForwarder", "", "Missing or Incomplete Freight Forwarder details.", null, null, null));
		}
	}
	
	/**
	 * This method validates Invoice data
	 * @param shipment
	 * @param errors
	 * @param sbu
	 * @throws ApplicationException 
	 */
	private List<ValidationErrorDto> validateInvoiceDataUI(Shipment shipment, String sbu) throws ApplicationException{
		List<ValidationErrorDto> errors = new ArrayList<ValidationErrorDto>();
		
		String aesValidation = getEnhancedValidationFlag(sbu);
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesInvoiceDataUI(shipment, errors);
		}
		
		if(!isEmpty(shipment.getShipToCode())){
			boolean validShipTo = helper.validateConsignee(shipment.getShipToCode());
			if(!validShipTo){
				errors.add(populateError("utlimateConsignee", "ult_consginee_code", "Invalid Ship To Party.", null, null, null));
			}
		}
		
		if(!isEmpty(shipment.getBillToCode())){
			boolean validBillTo = helper.validateConsignee(shipment.getBillToCode());
			if(!validBillTo){
				errors.add(populateError("utlimateConsignee", "ult_consginee_code", "Invalid Bill To Party.", null, null, null));
			}
		}
		
		if(!isEmpty(shipment.getFreightForwaderCode())){
			boolean validFF = helper.validateConsignee(shipment.getFreightForwaderCode());
			if(!validFF){
				errors.add(populateError("freightForwarder", "ff_code", "Invalid Forwarding Agent Code.", null, null, null));
			}
		}
		
		if(!isEmpty(shipment.getCountryOfUltConsigneeCode())){
			boolean validUltCongCtry = helper.validateCountry(shipment.getCountryOfUltConsigneeCode());
			if(!validUltCongCtry){
				errors.add(populateError("utlimateConsignee", "export_country", "Invalid Country of Ultimate Destination.", null, null, null));
			}
		}
		return errors;
	}
	
	private void validateAdvAesInvoiceDataUI(Shipment shipment, List<ValidationErrorDto> errors){
		if(isEmpty(shipment.getShipToName()) || isEmpty(shipment.getShipToAddr1()) ||
				isEmpty(shipment.getShipToCountry()) || isEmpty(shipment.getShipToCity())){
			errors.add(populateError("utlimateConsignee", "", "Missing or Incomplete Ship To details.", null, null, null));
		}
		
		if(isEmpty(shipment.getBillToName()) || isEmpty(shipment.getBillToAddr1()) ||
				isEmpty(shipment.getBillToCountry()) || isEmpty(shipment.getBillToCity())){
			errors.add(populateError("utlimateConsignee", "", "Missing or Incomplete Bill To details.", null, null, null));
		}
		
		if(isEmpty(shipment.getFreightForwaderName()) || isEmpty(shipment.getFreightForwaderAddrLine1()) ||
				isEmpty(shipment.getFreightForwaderCity()) || isEmpty(shipment.getFreightForwaderCountryCode())){
			errors.add(populateError("freightForwarder", "", "Missing or Incomplete Freight Forwarder details.", null, null, null));
		}
	}
	
	
	public List<ValidationErrorDto> validateItemDataUI(Shipment shipment, String sbu) throws ApplicationException{
		List<ValidationErrorDto> errors = new ArrayList<ValidationErrorDto>();
		String aesValidation = getEnhancedValidationFlag(sbu);	
		Collection<ShipmentItem> items = exportService.getShipmentItem(shipment.getId());
		
		if(aesValidation!=null && "Y".equals(aesValidation)){
			validateAdvAesItemDataUI(shipment, errors, items);
		}
		
		if(items!=null && items.size() > 0){
			Iterator<ShipmentItem> it = items.iterator();
			
			while(it.hasNext()){
				ShipmentItem item = it.next();
				
				if(!isEmpty(item.getCooCode()) && !helper.validateCountry(item.getCooCode())){
					errors.add(populateError("createAddItem", "count_origin", "Country of Origin is invalid for part number "+item.getPartNo(), null, item.getId(), null));
				}
				
				if(!isEmpty(item.getHtsSchedule()) && !helper.validateSchb(item.getHtsSchedule())){
					errors.add(populateError("createAddItem", "hts_sch", "HTS/Schedule B is invalid for part number "+item.getPartNo(), null, item.getId(), null));
				}
			}
		}
		return errors;
	}
	
	
	public void validateAdvAesItemDataUI(Shipment shipment, List<ValidationErrorDto> errors, Collection<ShipmentItem> items) throws ApplicationException{
		
		if(items!=null && items.size() > 0){
			Iterator<ShipmentItem> it = items.iterator();
			
			while(it.hasNext()){
				ShipmentItem item = it.next();
				if(isEmpty(item.getPartNo())){
					errors.add(populateError("createAddItem", "port_num", "Missing or Incomplete Part Number details for part number "+item.getPartNo(), null, item.getId(), null));
				}
				if(isEmpty(item.getPartDescription())){
					errors.add(populateError("createAddItem", "products_desc", "Missing or Incomplete Part Description details for part number "+item.getPartNo(), null, item.getId(), null));
				}
				if(isEmpty(item.getEccn())){
					errors.add(populateError("createAddItem", "eccn_tt", "Missing or Incomplete Classification Number details for part number "+item.getPartNo(), null, item.getId(), null));
				}
				if(isEmpty(item.getHtsSchedule())){
					errors.add(populateError("createAddItem", "hts_sch", "Missing or Incomplete HTS/ScheduleB Number details for part number "+item.getPartNo(), null, item.getId(), null));
				}
				if(isEmpty(item.getCooCode())){
					errors.add(populateError("createAddItem", "count_origin", "Missing or Incomplete Country of Origin details for part number "+item.getPartNo(), null, item.getId(), null));
				}
			}
		}
		
	}
	
	
	private List<ValidationErrorDto> validateAESUI(Shipment shipment, String sbu) throws ApplicationException{
		if(shipment==null)
			return null;
		
		List<ValidationErrorDto> errors = new ArrayList<ValidationErrorDto>();
		
		if(isEmpty(shipment.getAesOption())){
			errors.add(populateError("bookingCustomFiling", "aesOption", "AES Option is missing.", null, null, null));
		}
		
		if(shipment.getShipmentNo().length() > 17){
			errors.add(populateError("", "", "ShipmentNo length is greater than 17 characters.", null, null, null));
		}
		
		if(helper.checkContainSpecialChar(shipment.getShipmentNo())){
			errors.add(populateError("", "", "Shipment No can have only   - / . special characters.", null, null, null));
		}
		
		if(!isEmpty(shipment.getAesOption())){
			//pending - state of origin check(present in custom filing screen)
			
			if(isEmpty(shipment.getStateOfOrigin())){
				errors.add(populateError("bookingCustomFiling", "stateOfOrigin", "State of Origin is missing.", null, null, null));
			}
			
			if(isEmpty(shipment.getPortOfExportCode())){
				errors.add(populateError("bookingCustomFiling", "portOfExportName", "Port of Export is missing.", null, null, null));
			}
			if(isEmpty(shipment.getCountryOfUltConsigneeCode())){
				errors.add(populateError("utlimateConsignee", "export_country", "Country Of Ultimate Destination Code is missing.", null, null, null));
			}
			if(isEmpty(shipment.getModeOfTransportCode())){
				errors.add(populateError("createExporter", "mode_transport", "Mode of Transportation is missing.", null, null, null));
			}else{
				if(!"50".equals(shipment.getMotCode()) && !"60".equals(shipment.getMotCode()) && !"32".equals(shipment.getMotCode())
						&& !"33".equals(shipment.getMotCode()) && !"34".equals(shipment.getMotCode()) && !"70".equals(shipment.getMotCode())){
					//pending - 4.e.1.a.
					if(isEmpty(shipment.getShipmentCarrierName())){
						errors.add(populateError("bookingCustomFiling", "shipmentCarrierName", "Carrier Name must be present when Mode of Transportation selected is not MAIL.", null, null, null));
					}else{
						
					}
					
				}
			}
			
			
			if(shipment.getExportDate() == null){
				errors.add(populateError("", "", "Date of Export is missing.", null, null, null));
			}
			
			if(isEmpty(shipment.getExporterName())){
				errors.add(populateError("createExporter", "name", "Exporter / USPPI Details are missing.", null, null, null));
			}else{
				if(isEmpty(shipment.getExporterAddressLine1())){
					errors.add(populateError("createExporter", "address_tt", "Exporter / USPPI Address1 is missing.", null, null, null));
				}if(isEmpty(shipment.getExporterCity())){
					errors.add(populateError("createExporter", "export_city", "Exporter / USPPI City is missing.", null, null, null));
				}if(isEmpty(shipment.getExporterState())){
					errors.add(populateError("createExporter", "export_state", "Exporter / USPPI State is missing.", null, null, null));
				}if(isEmpty(shipment.getExporterZip())){
					errors.add(populateError("createExporter", "export_postal", "Exporter / USPPI Zip is missing.", null, null, null));
				}if(isEmpty(shipment.getUsppiExporterId())){
					errors.add(populateError("createExporter", "us_export", "Exporter / USPPI Filer ID (USPPI Number) is missing.", null, null, null));
				}if(isEmpty(shipment.getUsppiExporterIdType())){
					errors.add(populateError("createExporter", "usppiExporterIdType", "Exporter / USPPI Filer ID Type is missing.", null, null, null));
				}if(!isEmpty(shipment.getUsppiExporterIdType()) && "D".equals(shipment.getUsppiExporterIdType())){
					errors.add(populateError("createExporter", "usppiExporterIdType", "Exporter / USPPI Filer ID Type must be EIN or Foreign.", null, null, null));
				}if(isEmpty(shipment.getExporterLastName())){
					errors.add(populateError("createExporter", "lastname", "Exporter / USPPI Contact Last Name is missing.", null, null, null));
				}if(isEmpty(shipment.getExporterFirstName())){
					errors.add(populateError("createExporter", "firstname", "Exporter / USPPI Contact First Name is missing.", null, null, null));
				}if(isEmpty(shipment.getExporterPhoneNo())){
					errors.add(populateError("createExporter", "phoneNo", "Exporter / USPPI Contact Phone number is missing.", null, null, null));
				}else{
					if(shipment.getExporterPhoneNo().length() > 10){
						errors.add(populateError("createExporter", "phoneNo", "Exporter / USPPI Contact Phone number must contain 10 characters.", null, null, null));
					}if(!shipment.getExporterPhoneNo().matches("[0-9]+")){
						errors.add(populateError("createExporter", "phoneNo", "Exporter / USPPI Contact Phone number contains invalid characters.", null, null, null));
					}
				}
			}// if exporter name is present
			
			if(isEmpty(shipment.getUltConsigneeName())){
				errors.add(populateError("utlimateConsignee", "name", "Ultimate Consignee Name is missing.", null, null, null));
			}else{
				if(isEmpty(shipment.getUltConsigneeAddrLine1())){
					errors.add(populateError("utlimateConsignee", "address_tt", "Ultimate Consignee Address Line1 is missing.", null, null, null));
				}if(isEmpty(shipment.getUltConsigneeCity())){
					errors.add(populateError("utlimateConsignee", "export_city", "Ultimate Consignee City is missing.", null, null, null));
				}if(isEmpty(shipment.getUltConsigneeCountryCode())){
					errors.add(populateError("utlimateConsignee", "export_country", "Ultimate Consignee Country is missing.", null, null, null));
				}else if("MX".equals(shipment.getUltConsigneeCountryCode()) && isEmpty(shipment.getUltConsigneeState())){
					errors.add(populateError("utlimateConsignee", "export_state", "Ultimate Consignee State Code required for Ultimate Consignee Country Code MX.", null, null, null));
				}
				
				if(isEmpty(shipment.getUltConsigneeName()) && !isEmpty(shipment.getUltConsigneePhone())){
					errors.add(populateError("utlimateConsignee", "name", "Ultimate Consignee Contact Name is missing.", null, null, null));
				}
				if(!isEmpty(shipment.getUltConsigneeName()) && isEmpty(shipment.getUltConsigneePhone())){
					errors.add(populateError("utlimateConsignee", "phoneNo", "Ultimate Consignee Contact Phone number is missing.", null, null, null));
				}
				
				if(!isEmpty(shipment.getUltConsigneeName()) && !isEmpty(shipment.getUltConsigneePhone())){
					if(shipment.getUltConsigneePhone().length() > 11){
						errors.add(populateError("utlimateConsignee", "phoneNo", "Ultimate Consignee Contact Phone number Length exceeds 11 digits.", null, null, null));
					}if(!shipment.getUltConsigneePhone().matches("[0-9]+")){
						errors.add(populateError("utlimateConsignee", "phoneNo", "Ultimate Consignee Contact Phone number contains invalid characters.", null, null, null));
					}
				}
								
				//4.j.8. pending - FTR effective date
	
			}// if ultimate consigne name is present
			
			if(!isEmpty(shipment.getInterConsigneeName())){

				if(isEmpty(shipment.getInterConsigneeAddrLine1())){
					errors.add(populateError("intermediateConsignee", "name", "Intermediate Consignee Address Line1 is missing.", null, null, null));
				}if(isEmpty(shipment.getInterConsigneeCity())){
					errors.add(populateError("intermediateConsignee", "export_city", "Intermediate Consignee City is missing.", null, null, null));
				}if(isEmpty(shipment.getInterConsigneeCountryCode())){
					errors.add(populateError("intermediateConsignee", "intermediate_country", "Intermediate Consignee Country is missing.", null, null, null));
				}else if("MX".equals(shipment.getInterConsigneeCountryCode()) && isEmpty(shipment.getInterConsigneeState())){
					errors.add(populateError("intermediateConsignee", "intermediate_state", "Intermediate Consignee State Code required for Intermediate Consignee Country Code MX.", null, null, null));
				}
				
				if(isEmpty(shipment.getInterConsigneeName()) && !isEmpty(shipment.getInterConsigneePhone())){
					errors.add(populateError("intermediateConsignee", "name", "Intermediate Consignee Contact Name is missing.", null, null, null));
				}
				if(!isEmpty(shipment.getInterConsigneeName()) && isEmpty(shipment.getInterConsigneePhone())){
					errors.add(populateError("intermediateConsignee", "phone_no", "Intermediate Consignee Contact Phone number is missing.", null, null, null));
				}
				
				if(!isEmpty(shipment.getInterConsigneeName()) && !isEmpty(shipment.getInterConsigneePhone())){
					if(shipment.getInterConsigneePhone().length() > 11){
						errors.add(populateError("intermediateConsignee", "phone_no", "Intermediate Consignee Contact Phone number Length exceeds 11 digits.", null, null, null));
					}if(!shipment.getInterConsigneePhone().matches("[0-9]+")){
						errors.add(populateError("intermediateConsignee", "phone_no", "Intermediate Consignee Contact Phone number contains invalid characters.", null, null, null));
					}
				}
				
			}// if inter consignee name present
			
			if(!isEmpty(shipment.getFreightForwaderName()) && "1".equals(shipment.getTransmitToAes())){
				if(isEmpty(shipment.getFreightForwaderAddrLine1())){
					errors.add(populateError("freightForwarder", "address_tt", "Forwarding Agent Address Line1 is missing.", null, null, null));
				}if(isEmpty(shipment.getFreightForwaderCity())){
					errors.add(populateError("freightForwarder", "export_city", "Forwarding Agent City is missing.", null, null, null));
				}if(isEmpty(shipment.getFreightForwaderState())){
					errors.add(populateError("freightForwarder", "ff_state", "Forwarding Agent State is missing.", null, null, null));
				}if(isEmpty(shipment.getFreightForwaderCountryCode())){
					errors.add(populateError("freightForwarder", "ff_country", "Forwarding Agent Country is missing.", null, null, null));
				}if(isEmpty(shipment.getFreightForwaderZip())){
					errors.add(populateError("freightForwarder", "export_postal", "Forwarding Agent Zip Code is missing.", null, null, null));
				}if(isEmpty(shipment.getFreightForwaderName())){
					errors.add(populateError("freightForwarder", "name", "Forwarding Agent Contact Name is missing.", null, null, null));
				}else{
					if(isEmpty(shipment.getFreightForwaderLastName()) || !helper.isAlpha(shipment.getFreightForwaderLastName())){
						errors.add(populateError("freightForwarder", "lastname", "Forwarding Agent Contact Last Name is missing.", null, null, null));
					}
				}
				if(isEmpty(shipment.getFreightForwaderPhone())){
					errors.add(populateError("freightForwarder", "phoneNo", "Forwarding Agent Contact Phone is missing.", null, null, null));
				}else{
					if(shipment.getFreightForwaderPhone().length() > 10){
						errors.add(populateError("freightForwarder", "phoneNo", "Forwarding Agent Contact Phone Number must contain 10 characters.", null, null, null));
					}
					if(!shipment.getFreightForwaderPhone().matches("[0-9]+")){
						errors.add(populateError("freightForwarder", "phoneNo", "Forwarding Agent Primary Contact Phone Number contains invalid characters.", null, null, null));
					}
				}
				
				if(isEmpty(shipment.getFfFilerIdNo())){
					errors.add(populateError("freightForwarder", "ffFilerIdNo", "Forwarding Agent Filer ID is missing.", null, null, null));
				}else if(shipment.getFfFilerIdNo().length() != 9 || shipment.getFfFilerIdNo().length() != 11){
					errors.add(populateError("freightForwarder", "ffFilerIdNo", "Forwarding Agent ID number must contain 9 or 11 characters.", null, null, null));
				}
				if(isEmpty(shipment.getFfFilerIdType())){
					errors.add(populateError("freightForwarder", "filer_id_type", "Forwarding Agent Filer Type is missing.", null, null, null));
				}
			}// if FF name is present
			
			if(isEmpty(shipment.getRelatedPrtyTxn())){
				errors.add(populateError("bookingCustomFiling", "relatedPrtyTxn", "No Parties To Transaction is selected.", null, null, null));
			}
			if(isEmpty(shipment.getShipmentRefNo())){
				errors.add(populateError("bookingCustomFiling", "shipmentRefNo", "Shipment Reference No is missing.", null, null, null));
			}
			
			
			if(isEmpty(shipment.getHazdarous())){
				errors.add(populateError("bookingCustomFiling", "hazdarous", "Hazardous Materials option is not selected.", null, null, null));
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("10") || shipment.getMotCode().equals("11"))){
				if(isEmpty(shipment.getPortOfUnloadCode())){
					errors.add(populateError("bookingCustomFiling", "portOfUnloadName", "Port of Unloading is required for the selected Method of Transportation.", null, null, null));
				}
				if(isEmpty(shipment.getTransportRefNo())){
					errors.add(populateError("bookingCustomFiling", "transportRefNo", "Transport Reference Number is required for the selected Method of Transportation.", null, null, null));
				}
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("40") || shipment.getMotCode().equals("41"))
					&& !isEmpty(shipment.getTransportRefNo())){
				Pattern p = Pattern.compile("\\w{3}-\\w{8}");
				Matcher m = p.matcher(shipment.getTransportRefNo());
				
				if(m.find()){
					errors.add(populateError("bookingCustomFiling", "transportRefNo", "Transportation Ref. No needs to be in the format XXX-XXXXXXXX when Mode of Transport is Air.", null, null, null));
				}				
			}
			if(!isEmpty(shipment.getCountryOfUltConsigneeCode()) && "PR".equals(shipment.getUltConsigneeCountryCode()) && isEmpty(shipment.getPortOfUnloadCode())){
				errors.add(populateError("bookingCustomFiling", "portOfUnloadName", "When Country Of Ultimate Destination is Puerto Rico, Port of Unloading is required.", null, null, null));
			}
			if(!isEmpty(shipment.getUsppiExporterIdType()) && "E".equals(shipment.getUsppiExporterIdType()) && 
					!isEmpty(shipment.getUsppiExporterId()) && helper.checkContainSpecialChar(shipment.getUsppiExporterId())){
				errors.add(populateError("freightForwarder", "ffFilerIdNo", "EIN number for Freight Forwarder should not have special characters.", null, null, null));
			}
			if(!isEmpty(shipment.getTransmitToAes()) && "1".equals(shipment.getTransmitToAes()) && 
					!isEmpty(shipment.getFfFilerIdNo()) && helper.checkContainSpecialChar(shipment.getFfFilerIdNo())){
				errors.add(populateError("freightForwarder", "ffFilerIdNo", "EIN number for Freight Forwarder should not have special characters.", null, null, null));
			}
			if(!isEmpty(shipment.getEntryNumber()) && shipment.getEntryNumber().length() < 9){
				errors.add(populateError("", "", "Entry No needs to be greater than or equal to 9 characters.", null, null, null));
			}
			if(!isEmpty(shipment.getMotCode()) && (shipment.getMotCode().equals("10") || shipment.getMotCode().equals("11")) &&
					isEmpty(shipment.getConveyanceName())){
				errors.add(populateError("bookingCustomFiling", "conveyanceName", "Conveyance name is required for the selected mode of transport.", null, null, null));
			}
						
			//items validation start
			Collection<ShipmentItem> items = exportService.getShipmentItem(shipment.getId());
			
			if(items!=null && items.size() > 0){
				
				Iterator<ShipmentItem> it = items.iterator();
				
				while(it.hasNext()){
					ShipmentItem item = it.next();
					
					if(item.getInvoiceQty() == null){
						errors.add(populateError("createAddItem", "aes_invoice", "Primary Net Quantity is missing for item no : "+item.getItemNo(), null, item.getId(), null));
					}
					if(isEmpty(item.getInvoiceValue()) || item.getInvoiceValue() < 1){
						errors.add(populateError("createAddItem", "invoice_value", "EEI Value is missing for item no : "+item.getItemNo(), null, item.getId(), null));
					}					
					if(!isEmpty(item.getLicenseNo()) && isEmpty(item.getLicenseCode())){
						errors.add(populateError("createAddItem", "licenseCode", "License Code is missing for item no : "+item.getItemNo(), null, item.getId(), null));
					}else if((!isEmpty(item.getBisLicenseException()) || !isEmpty(item.getItarExemption())) && isEmpty(item.getLicenseCode())){
						errors.add(populateError("createAddItem", "licenseCode", "License Code is missing for item no : "+item.getItemNo(), null, item.getId(), null));
					}else if(!isEmpty(item.getBisLicenseException()) && !isEmpty(item.getLicenseCode())){
						errors.add(populateError("createAddItem", "licenseCode", "License Code is missing for item no : "+item.getItemNo(), null, item.getId(), null));
					}
										
					if(!isEmpty(shipment.getMotCode())){						
						if("10".equals(shipment.getMotCode()) || "11".equals(shipment.getMotCode()) || "12".equals(shipment.getMotCode())
								|| "20".equals(shipment.getMotCode()) || "21".equals(shipment.getMotCode()) || "30".equals(shipment.getMotCode())
								|| "31".equals(shipment.getMotCode()) || "40".equals(shipment.getMotCode()) || "41".equals(shipment.getMotCode())){
							if(isEmpty(item.getGrossWeight()) || item.getGrossWeight() <= 0){
								errors.add(populateError("createAddItem", "gross_weight", "Gross Weight is missing for item no : "+item.getItemNo(), null, item.getId(), null));
							}
						}else{
							if(!isEmpty(item.getGrossWeight())){
								errors.add(populateError("createAddItem", "gross_weight", "Gross Weight is not allowed for the selected mode of transportation for item no : "+item.getItemNo(), null, item.getId(), null));
							}
						}
					}
					
					String primatyUom = "AGG/AUG/CGM/CKG/CTN/CYK/G/IRG/KG/KTS/OSG/PDG/PTG/RHG/RUG/T";
					String[] uomArr = primatyUom.split("/");
					boolean fnd = false;
					if(!isEmpty(item.getHtsScheduleUom())){
						for(String uom : uomArr){
							if(uom.equalsIgnoreCase(item.getHtsScheduleUom())){
								fnd = true;
							}
						}
					}
					
					if(fnd && isEmpty(item.getNetWeight())){
						errors.add(populateError("createAddItem", "net_weight", "When the HTS UNIT of Measure is a Weight, then the Item Net weight is required for reporting quantity to AES for item no : "+item.getItemNo(), null, item.getId(), null));
					}
					
					String licenseCodes = "SAG/SCA/S00/S05/S61/S73/S85/S94/SAU/SGB";
					String[] licenseArr = licenseCodes.split("/");
					fnd = false;
					if(!isEmpty(item.getLicenseCode())){
						for(String license: licenseArr){
							if(license.equalsIgnoreCase(item.getLicenseCode())){
								fnd = true;
							}
						}
					
						if(fnd){
							if(item.getDdtcQuantity() == null){
								errors.add(populateError("createAddItem", "ddtcQuantity", "DDTC quantity is missing for reporting quantity to AES for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							if(isEmpty(item.getDdtcUom())){
								errors.add(populateError("createAddItem", "ddtcUom", "DDTC Unit Of Measure Code is missing for reporting quantity to AES for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							
							if(isEmpty(item.getUsmlCategory())){
								errors.add(populateError("createAddItem", "ddtcUom", "USML Category Code is missing for reporting quantity to AES for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							if(isEmpty(item.getSmeIndicator())){
								errors.add(populateError("createAddItem", "smeIndicator", "SME Indicator is missing for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							if(isEmpty(item.getDdtcRegistrationNo())){
								errors.add(populateError("createAddItem", "ddtcRegistrationNo", "Registration number is missing for item no : "+item.getItemNo(), null, item.getId(), null));
							}
														
							if("SCA".equalsIgnoreCase(item.getLicenseCode()) || "S00".equalsIgnoreCase(item.getLicenseCode())
									|| "SAU".equalsIgnoreCase(item.getLicenseCode()) || "SGB".equalsIgnoreCase(item.getLicenseCode())){
								if(isEmpty(item.getDdtcEligibilityParty())){
									errors.add(populateError("createAddItem", "ddtcEligibilityParty", "Eligible Party Indicator is missing for item no : "+item.getItemNo(), null, item.getId(), null));
								}
							}
							
							if("SCA".equalsIgnoreCase(item.getLicenseCode()) || "S00".equalsIgnoreCase(item.getLicenseCode())
									|| "SAU".equalsIgnoreCase(item.getLicenseCode()) || "SGB".equalsIgnoreCase(item.getLicenseCode())
									|| "SAG".equalsIgnoreCase(item.getLicenseCode())){
								if(isEmpty(item.getItarExemption())){
									errors.add(populateError("createAddItem", "itarExemption", "Itar Excemption is missing for item no : "+item.getItemNo(), null, item.getId(), null));
								}
							}
							
							if("S05".equalsIgnoreCase(item.getLicenseCode()) || "S61".equalsIgnoreCase(item.getLicenseCode())
									|| "S73".equalsIgnoreCase(item.getLicenseCode()) || "S85".equalsIgnoreCase(item.getLicenseCode())
									|| "S94".equalsIgnoreCase(item.getLicenseCode())){
								if(isEmpty(item.getLicenseNo())){
									errors.add(populateError("createAddItem", "licenseNo", "License Number is missing for item no : "+item.getItemNo(), null, item.getId(), null));
									}
								}
							
							}//5.i end	
							
							if(!isEmpty(item.getLicenseType()) && ("SAU".equalsIgnoreCase(item.getLicenseType()) || "SGB".equalsIgnoreCase(item.getLicenseType()))){
								if(isEmpty(item.getApprovedCommMemberNo())){
									errors.add(populateError("createAddItem", "approvedCommMemberNo", "An Approved Community Member # is required for the ITAR exemption being used for item no : "+item.getItemNo(), null, item.getId(), null));
								}
								if("SGB".equalsIgnoreCase(item.getLicenseType()) && !isEmpty(item.getApprovedCommMemberNo())){
									Pattern p = Pattern.compile("\\w{2}\\[0-9]{9}");
									Matcher m = p.matcher(item.getApprovedCommMemberNo());
									
									if(!m.find()){
										errors.add(populateError("createAddItem", "approvedCommMemberNo", "A Valid Approved Community Member # is required for the ITAR exemption being used for item no : "+item.getItemNo(), null, null, null));
									}	
								}else if("SAU".equalsIgnoreCase(item.getLicenseType()) && !isEmpty(item.getApprovedCommMemberNo())){
									Pattern p = Pattern.compile("\\w{3}\\[0-9]{8}");
									Matcher m = p.matcher(item.getApprovedCommMemberNo());
									
									if(!m.find()){
										errors.add(populateError("createAddItem", "approvedCommMemberNo", "A Valid Approved Community Member # is required for the ITAR exemption being used for item no : "+item.getItemNo(), null, null, null));
									}	
								}
							}else if(!isEmpty(item.getLicenseType()) && !isEmpty(item.getApprovedCommMemberNo())){
									errors.add(populateError("createAddItem", "approvedCommMemberNo", "Approved Community Member # is  not allowed for the License Type being used for item no : "+item.getItemNo(), null, item.getId(), null));	
							}
													
							if(!isEmpty(item.getLicenseNo()) && !item.getLicenseNo().matches("[A-Za-z0-9]+")){
								errors.add(populateError("createAddItem", "licenseNo", "License Number cannot contain any special characters for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							
							//pending - accented characters in item/EEI description
							
							if(!isEmpty(shipment.getAesOption()) && "4".equals(shipment.getAesOption())){
								boolean error = false;
								if(!isEmpty(item.getEccn()) && item.getEccn().startsWith("600")){
									error = true;
								}else if(!isEmpty(item.getBisLicenseException()) && ("STA".equalsIgnoreCase(item.getBisLicenseException())
										|| "VEU".equalsIgnoreCase(item.getBisLicenseException()))){
									error = true;
								}else if(!isEmpty(item.getLicenseCode()) && ("C59".equalsIgnoreCase(item.getLicenseCode())
										|| "C57".equalsIgnoreCase(item.getLicenseCode()))){
									error = true;
								}
								
								if(error){
									errors.add(populateError("createAddItem", "", "This Line Item cannot be filed on an OPTION 4 export shipment for item no : "+item.getItemNo(), null, item.getId(), null));
								}
							}
							if(!isEmpty(item.getLicenseNo()) && !isEmpty(item.getEccn()) && item.getEccn().startsWith("600")){
								errors.add(populateError("createAddItem", "licenseNo", "Invalid License No for 600 series ECCN for item no : "+item.getItemNo(), null, item.getId(), null));
							}
							
							//pending - 5.q.
							//pending - 5.r.
							//pending - 5.s.
							
							//pending - 5.t.
							/*if(shipment.getExportDate() != null && item.getFtrEffDate() != null 
									&& shipment.getExportDate().compareTo(item.getFtrEffDate()) > 0 && ){
								
							}*/
									
					}
				}// while loop on items
				
			}//if items found
			
			
		}// if AES option is selected
		return errors;
	}
	
	
	
	
	
	
	
	private ValidationErrorDto populateError(String url, String field, String msg, String section, Integer itemId, Integer cartonId){
		ValidationErrorDto error = new ValidationErrorDto(msg, url, field, section, itemId, cartonId);
		return error;
	}
}
