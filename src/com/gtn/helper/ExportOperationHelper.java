package com.gtn.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.gtn.configuration.ConnectionUtil;
import com.gtn.dto.LicenseScreeningInput;
import com.gtn.dto.ProductLSDetail;
import com.gtn.dto.ProductLSResponse;
import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.ShipmentDocEmailDto;
import com.gtn.dto.ShipmentDto;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Shipment;
import com.gtn.model.ShipmentCarton;
import com.gtn.model.ShipmentItem;
import com.gtn.service.ExportOperationService;
import com.gtn.service.LicenceScreeningService;
import com.gtn.service.WatchListScreeningService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

public class ExportOperationHelper {
	
	private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	static{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public static List<String> validateExporter(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		if(isEmpty(shipment.getExporterName())){
			op.add("Exporter name is required.");
		}
		if(isEmpty(shipment.getExporterFirstName())){
			op.add("Exporter first name is required.");
		}
		if(isEmpty(shipment.getExporterLastName())){
			op.add("Exporter last name is required.");
		}
		if(isEmpty(shipment.getExporterAddressLine1())){
			op.add("Exporter address is required.");
		}
		if(isEmpty(shipment.getExporterCity())){
			op.add("Exporter city is required.");
		}
		if(isEmpty(shipment.getExporterState())){
			op.add("Exporter state is required.");
		}
		if(isEmpty(shipment.getExporterCountry())){
			op.add("Exporter country is required.");
		}
		if(isEmpty(shipment.getExporterZip())){
			op.add("Exporter zip code is required.");
		}
		if(isEmpty(shipment.getExporterCompanyName())){
			op.add("Exporter company name is required.");
		}
		if(isEmpty(shipment.getExporterPhoneNo())){
			op.add("Exporter phone number is required.");
		}
		if(isEmpty(shipment.getExporterEmail())){
			op.add("Exporter email is required.");
		}else if(!validateEmail(shipment.getExporterEmail())){
			op.add("Please enter valid email ID.");
		}		
		
		return op;
	}
	
	/**
	 * This method is used to prepare exporter shipment object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareExporter(Shipment db, Shipment view){
		
		db.setExporterCode(view.getExporterCode());
		db.setExporterName(view.getExporterName());
		db.setExporterAddressLine1(view.getExporterAddressLine1());
		db.setExporterCity(view.getExporterCity());
		db.setExporterState(view.getExporterState());
		db.setExporterStateName(view.getExporterStateName());
		db.setExporterCountry(view.getExporterCountry());
		db.setExporterCountryName(view.getExporterCountryName());
		db.setExporterZip(view.getExporterZip());
		db.setExporterFirstName(view.getExporterFirstName());
		
		db.setExporterLastName(view.getExporterLastName());
		db.setExporterCompanyName(view.getExporterCompanyName());
		db.setExporterPhoneNo(view.getExporterPhoneNo());
		db.setExporterEmail(view.getExporterEmail());
		db.setUsppiMot(view.getUsppiMot());
		db.setUsppiPortOfExport(view.getUsppiPortOfExport());
		db.setUsppiExporterId(view.getUsppiExporterId());
		db.setUsppiExporterIdType(view.getUsppiExporterIdType());
		db.setUsspiFirstName(view.getUsspiFirstName());
		db.setUsspiLastName(view.getUsspiLastName());
		db.setUsspiPhone(view.getUsspiPhone());
		
		//db.setAesOption(view.getAesOption());
		
		return db;
	}
	
	public static List<String> validateUltConsignee(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		if(isEmpty(shipment.getUltConsigneeName())){
			op.add("Consignee name is required.");
		}
		if(isEmpty(shipment.getUltConsigneeFirstName())){
			op.add("Consignee first name is required.");
		}
		if(isEmpty(shipment.getUltConsigneeLastName())){
			op.add("Consignee last name is required.");
		}
		if(isEmpty(shipment.getUltConsigneeAddrLine1())){
			op.add("Consignee address is required.");
		}
		if(isEmpty(shipment.getUltConsigneeCity())){
			op.add("Consignee city is required.");
		}
		if(isEmpty(shipment.getUltConsigneeState())){
			op.add("Consignee state is required.");
		}
		if(isEmpty(shipment.getUltConsigneeCountryCode())){
			op.add("Consignee country is required.");
		}
		if(isEmpty(shipment.getUltConsigneeZip())){
			op.add("Consignee zip code is required.");
		}
		if(isEmpty(shipment.getUltConsigneeCompanyName())){
			op.add("Consignee company name is required.");
		}
		if(isEmpty(shipment.getUltConsigneePhone())){
			op.add("Consignee phone number is required.");
		}
		if(isEmpty(shipment.getUltConsigneeEmail())){
			op.add("Consignee email is required.");
		}else if(!validateEmail(shipment.getUltConsigneeEmail())){
			op.add("Please enter valid email ID.");
		}		
		
		return op;
	}
	
	/**
	 * Prepare Ultimate consignee address shipment object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareUltConsignee(Shipment db, Shipment view){
		
		db.setUltConsigneeName(view.getUltConsigneeName());
		db.setUltConsigneeFirstName(view.getUltConsigneeFirstName());
		db.setUltConsigneeLastName(view.getUltConsigneeLastName());
		db.setUltConsigneeAddrLine1(view.getUltConsigneeAddrLine1());
		db.setUltConsigneeAddrLine2(view.getUltConsigneeAddrLine2());
		db.setUltConsigneeCity(view.getUltConsigneeCity());
		db.setUltConsigneeState(view.getUltConsigneeState());
		
		db.setUltConsigneeStateName(view.getUltConsigneeStateName());
		
		db.setUltConsigneeCountryCode(view.getUltConsigneeCountryCode());
		db.setUltConsigneeCountryName(view.getUltConsigneeCountryName());
		db.setUltConsigneeZip(view.getUltConsigneeZip());
		db.setUltConsigneeCompanyName(view.getUltConsigneeCompanyName());
		db.setUltConsigneePhone(view.getUltConsigneePhone());
		db.setUltConsigneeEmail(view.getUltConsigneeEmail());
		db.setUltConsigneeType(view.getUltConsigneeType());
		db.setUltConsigneeCode(view.getUltConsigneeCode());
		
		return db;
	}

	
	public static List<String> validateInterConsignee(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		if(!isEmpty(shipment.getInterConsigneeEmail()) && !validateEmail(shipment.getInterConsigneeEmail())){
			op.add("Please enter valid email ID.");
		}		
		
		return op;
	}
	
	/**
	 * Prepare inter consignee address object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareInterConsignee(Shipment db, Shipment view){
		
		db.setInterConsigneeName(view.getInterConsigneeName());
		db.setInterConsigneeFirstName(view.getInterConsigneeFirstName());
		db.setInterConsigneeLastName(view.getInterConsigneeLastName());
		db.setInterConsigneeAddrLine1(view.getInterConsigneeAddrLine1());
		db.setInterConsigneeAddrLine2(view.getInterConsigneeAddrLine2());
		db.setInterConsigneeCity(view.getInterConsigneeCity());
		db.setInterConsigneeState(view.getInterConsigneeState());
		db.setInterConsigneeStateName(view.getInterConsigneeStateName());
		db.setInterConsigneeCountryCode(view.getInterConsigneeCountryCode());
		db.setInterConsigneeCountryName(view.getInterConsigneeCountryName());
		db.setInterConsigneeZip(view.getInterConsigneeZip());
		db.setInterConsigneeCompanyName(view.getInterConsigneeCompanyName());
		db.setInterConsigneePhone(view.getInterConsigneePhone());
		db.setInterConsigneeEmail(view.getInterConsigneeEmail());
		db.setInterConsigneeCode(view.getInterConsigneeCode());
		
		return db;
	}
	
	
	public static List<String> validateFreightFw(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		if(!isEmpty(shipment.getFreightForwaderEmail()) && !validateEmail(shipment.getFreightForwaderEmail())){
			op.add("Please enter valid email ID.");
		}		
		
		return op;
	}
	/**
	 * Prepare Freight forwarder Object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareFreightForwader(Shipment db, Shipment view){
		
		db.setFreightForwaderName(view.getFreightForwaderName());
		db.setFreightForwaderFirstName(view.getFreightForwaderFirstName());
		db.setFreightForwaderLastName(view.getFreightForwaderLastName());
		db.setFreightForwaderAddrLine1(view.getFreightForwaderAddrLine1());
		db.setFreightForwaderAddrLine2(view.getFreightForwaderAddrLine2());
		db.setFreightForwaderCity(view.getFreightForwaderCity());
		db.setFreightForwaderState(view.getFreightForwaderState());
		db.setFreightForwaderStateName(view.getFreightForwaderStateName());
		db.setFreightForwaderCountryCode(view.getFreightForwaderCountryCode());
		db.setFreightForwaderCountryName(view.getFreightForwaderCountryName());
		db.setFreightForwaderZip(view.getFreightForwaderZip());
		db.setFreightForwaderCompanyName(view.getFreightForwaderCompanyName());
		db.setFreightForwaderPhone(view.getFreightForwaderPhone());
		db.setFreightForwaderEmail(view.getFreightForwaderEmail());
		db.setFfFilerIdType(view.getFfFilerIdType());
		db.setFfFilerIdNo(view.getFfFilerIdNo());
		
		/*if(!isEmpty(view.getTransmitToAes())){
			if(view.getTransmitToAes().equals("true"))
				db.setTransmitToAes("1");
			else
				db.setTransmitToAes("0");
		}else{
			db.setTransmitToAes(null);
		}*/
		db.setTransmitToAes(view.getTransmitToAes());
		
		return db;
	}
	
	/**
	 * check if ult consignee address is changed and screening needs to be performed
	 * @param db
	 * @param view
	 * @return
	 */
	public boolean screeningReq(Shipment db, Shipment view){
						
		if(!view.getUltConsigneeFirstName().equals(db.getUltConsigneeFirstName())){
			return true;
		}
		if(!view.getUltConsigneeLastName().equals(db.getUltConsigneeLastName())){
			return true;
		}
		if(!view.getUltConsigneeCountryCode().equals(db.getUltConsigneeCountryCode())){
			return true;
		}
		if(!view.getUltConsigneeState().equals(db.getUltConsigneeState())){
			return true;
		}
		if(!view.getUltConsigneeCity().equals(db.getUltConsigneeCity())){
			return true;
		}
		if(!view.getUltConsigneeZip().equals(db.getUltConsigneeZip())){
			return true;
		}
		if(!view.getUltConsigneeAddrLine1().equals(db.getUltConsigneeAddrLine1())){
			return true;
		}				
		
		return false;
	}
	
	
	public static List<String> validateBilling(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		if(shipment.getConversionRate()!=null && !NumberUtils.isNumber(shipment.getConversionRate().toString())){
			op.add("Please enter valid conversion rate.");
		}		
		if(shipment.getChargeAmt()!=null && !NumberUtils.isNumber(shipment.getChargeAmt().toString())){
			op.add("Please enter valid charge amount.");
		}
		if(shipment.getShipmentValue()!=null && !NumberUtils.isNumber(shipment.getShipmentValue().toString())){
			op.add("Please enter valid shipment value.");
		}
		
		return op;
	}
	/**
	 * Prepare Shipment billing object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareShipmentBilling(Shipment db, Shipment view){
		
		db.setPaymentTerms(view.getPaymentTerms());
		db.setIncoTerms(view.getIncoTerms());
		db.setCurrency(view.getCurrency());
		db.setConversionRate(view.getConversionRate());
		db.setPoNumber(view.getPoNumber());
		db.setDropShipment(view.getDropShipment());
		db.setShipTo(view.getShipTo());
		db.setBillTo(view.getBillTo());
		db.setModeOfTransport(view.getModeOfTransport());
		db.setFreightChargesPaidBy(view.getFreightChargesPaidBy());
		db.setForeignVatPaidBy(view.getForeignVatPaidBy());
		db.setCustomDutyPaidBy(view.getCustomDutyPaidBy());
		db.setContainPersonalEffect(view.getContainPersonalEffect());
		db.setInsuranceRequired(view.getInsuranceRequired());
		db.setChargeDesc(view.getChargeDesc());
		db.setChargeAmt(view.getChargeAmt());
		db.setChargeToCustomer(view.getChargeToCustomer());
		db.setPrintOnDoc(view.getPrintOnDoc());
		db.setModeOfTransportCode(view.getModeOfTransportCode());
		db.setShipmentValue(view.getShipmentValue());
		
		return db;
	}
	
	public static List<String> validateItem(ShipmentItem item){
		List<String> op = new ArrayList<String>();
		
		if(isEmpty(item.getPartDescription())){
			op.add("Product description is required.");
		}
		if(isEmpty(item.getInvoiceQty())){
			op.add("AES quantity is required.");
		}
		if(isEmpty(item.getUnitePrice())){
			op.add("Unit price is required.");
		}
		if(isEmpty(item.getGrossWeight())){
			op.add("Gross weight is required.");
		}
		if(isEmpty(item.getNetWeight())){
			op.add("Net weight is required.");
		}
		
		return op;
	}
	
	public static List<String> validateCarton(ShipmentCarton carton){
		List<String> op = new ArrayList<String>();
		
		if(carton.getPackageNo() <= 0){
			op.add("Please enter valid number of packages.");
		}
		if(carton.getCartonNo() <= 0){
			op.add("Please enter valid carton number.");
		}
		if(isEmpty(carton.getDimUom())){
			op.add("Dimesnion uom is required.");
		}
		if(isEmpty(carton.getWeightUom())){
			op.add("Weight uom is required.");
		}
		
		if(isEmpty(carton.getLength())){
			op.add("Length is required.");
		}
		if(isEmpty(carton.getWidth())){
			op.add("Width is required.");
		}
		if(isEmpty(carton.getHeight())){
			op.add("Height is required.");
		}
		if(isEmpty(carton.getGrossWeight())){
			op.add("Gross weight is required.");
		}
		if(isEmpty(carton.getNetWeight())){
			op.add("Net weight is required.");
		}
		
		return op;
	}
	
	public ScreeningEntity prepareWlsScreeningInp(Shipment ultConsignee){
		ScreeningEntity entity = new ScreeningEntity();
		
		entity.setName(ultConsignee.getUltConsigneeFirstName()+" "+ultConsignee.getUltConsigneeLastName());
		//entity.setCountry(ultConsignee.getCountryOfUltConsigneeCode());
		entity.setCountry(ultConsignee.getUltConsigneeCountryCode());
		entity.setState(ultConsignee.getUltConsigneeState());
		entity.setCity(ultConsignee.getUltConsigneeCity());
		entity.setZip(ultConsignee.getUltConsigneeZip());
		entity.setAddressLine1(ultConsignee.getUltConsigneeAddrLine1());
		
		return entity;
	}
	
	public WlsScreeningResponse doWatchListScreening(ScreeningEntity entity, WatchListScreeningService watchListScreeningService) throws JAXBException, IOException, ApplicationException{
		String response = watchListScreeningService.doWatchListScreening(entity);
		WlsScreeningResponse responseObj = watchListScreeningService.translateXmlResponse(response);
		return responseObj;
	}
	

	@SuppressWarnings("unchecked")
	private String deserialzeLSStrJDK7(String filename) {

		String address = null;

		try (ObjectInputStream ois
			= new ObjectInputStream(new FileInputStream(filename))) {

			address = (String) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return address;
	}
	
	
	private Map<String, String> doLiceseScreening(Shipment shipment, ShipmentItem item, LicenceScreeningService licenseScreening, ExportOperationService service) throws IOException, ApplicationException{
		
		LicenseScreeningInput input = prepareLsInput(shipment, item, service);
		Map<String, String> result = new HashMap<String, String>();
				
		ProductLSResponse response = null;
		
		try{
			response = licenseScreening.doLicenceScreeningNew(input);
			//strResponse = deserialzeLSStrJDK7("C:\\Users\\lenovo\\Desktop\\GTN\\lsResponse.txt");
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Unable to screen item, server error", e);
		}
		
		/*LicenseScreeningResponse response = null;
		
		if(!isEmpty(strResponse)){
			ObjectMapper mapper = new ObjectMapper();			
			response = mapper.readValue(strResponse, LicenseScreeningResponse.class);
		}*/
		
		if(response.getStatus()!=null && response.getStatus().equals("SUCCESS") && !"LSNA".equals(response.getOverallStatusData())){
			
			if(response.getProductLSDetails()!=null && response.getProductLSDetails().size() > 0){
				ProductLSDetail details = response.getProductLSDetails().get(0);
				
				result.put("licReq", "LR".equals(details.getScreeningResult()) ? "Y" : "N");
				result.put("repReq", details.getReportReq());
				result.put("type", input.getType());
			}
			
			return result;
		}else{
			result.put("licReq", "NA");
			result.put("repReq", "NA");
			result.put("type", input.getType());			
			return result;
		}
				
		//return null;
	}
	/*private Map<String, String> doLiceseScreening(Shipment shipment, ShipmentItem item, LicenceScreeningService licenseScreening) throws IOException, ApplicationException{
		
		LicenseScreeningInput input = prepareLsInput(shipment, item);
		Map<String, String> result = new HashMap<String, String>();
		
		if(input.getType() == null){
			return null;
		}
		
		String strResponse = null;
		
		try{
			strResponse = licenseScreening.doLicenceScreening(input);
			//strResponse = deserialzeLSStrJDK7("C:\\Users\\lenovo\\Desktop\\GTN\\lsResponse.txt");
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Unable to screen item, server error", e);
		}
		
		LicenseScreeningResponse response = null;
		
		if(!isEmpty(strResponse)){
			ObjectMapper mapper = new ObjectMapper();			
			response = mapper.readValue(strResponse, LicenseScreeningResponse.class);
		}
		
		if(response.getStatus()!=null && !response.getStatus().equals("FAIL") && response.getEldsHostedServiceView().get(0)!=null){
			result.put("licReq", response.getEldsHostedServiceView().get(0).getLicReq());
			result.put("repReq", response.getEldsHostedServiceView().get(0).getReportReq());
			result.put("type", input.getType());			
			return result;
		}else if(response.getStatus()!=null && response.getStatus().equals("FAIL")){
			result.put("licReq", "NA");
			result.put("repReq", "NA");
			result.put("type", input.getType());			
			return result;
		}
				
		return null;
	}*/
	
	private LicenseScreeningInput prepareLsInput(Shipment shipment, ShipmentItem item, ExportOperationService service){
		LicenseScreeningInput input = new LicenseScreeningInput();
		
		input.setExpCountry(shipment.getCountryOfExportCode());
		input.setImpCountry(shipment.getCountryOfUltConsigneeCode());
		
		if(!isEmpty(item.getEccn())){
			input.setInput(item.getEccn());
			input.setType("ECCN");
		}else if(!isEmpty(item.getPartNo())){
			try{
				String eccnNo = service.getEccnForProduct(item.getPartNo());
				if(eccnNo != null){
					input.setInput(eccnNo);
				}else{
					input.setInput(item.getPartNo());
				}
			}catch(Exception e){
				input.setInput(item.getPartNo());
			}
			input.setType("Product No");
		}else if(!isEmpty(item.getUsmlCategory())){
			input.setInput(item.getUsmlCategory());
			input.setType("USML");
		}
	
		return input;
	}
	/*private LicenseScreeningInput prepareLsInput(Shipment shipment, ShipmentItem item){
		LicenseScreeningInput input = new LicenseScreeningInput();
		
		input.setExpCountry(shipment.getCountryOfExportCode());
		input.setImpCountry(shipment.getCountryOfUltConsigneeCode());
		
		if(!isEmpty(item.getPartNo())){
			input.setType("product_no");
			input.setInput(item.getPartNo());
		}else if(!isEmpty(item.getEccn())){
			input.setType("eccn");
			input.setInput(item.getEccn());
		}else if(!isEmpty(item.getUsmlCategory())){
			input.setType("usml");
			input.setInput(item.getUsmlCategory());
		}
	
		return input;
	}*/
	
	public Shipment updateShipmentLsStatus(Shipment shipment, ShipmentItem item, LicenceScreeningService licenseScreening, ExportOperationService service) throws ApplicationException, IOException{
		
		Map<String, String> licenseReq = doLiceseScreening(shipment, item, licenseScreening, service);
		
		if(licenseReq!=null){
			item.setLicReq(licenseReq.get("licReq"));
			item.setRepReq(licenseReq.get("repReq"));
			item.setScreenType(licenseReq.get("type"));
			item.setScreenedOn(new Date());
			
			/*if(shipment.getLsStatus()==null){
				shipment.setLsStatus(item.getLicReq());
			}
			else if(shipment.getLsStatus()!=null && shipment.getLsStatus().equals("N")){
				if(!isEmpty(item.getLicReq()) && item.getLicReq().equals("Y")){
					shipment.setLsStatus("Y");
				}else if(!isEmpty(item.getLicReq()) && item.getLicReq().equals("NA")){
					Collection<ShipmentItem> items = service.getShipmentItem(item.getShipmentId());
					String lastStatus = getShipmentItemStatus(items, item);
					
					//check this
				}
			}
			else if(shipment.getLsStatus()!=null && shipment.getLsStatus().equals("Y")){
				if(!isEmpty(item.getLicReq()) && item.getLicReq().equals("N")){
					
					Collection<ShipmentItem> items = service.getShipmentItem(item.getShipmentId());
					
					String lastStatus = getShipmentItemStatus(items, item);
					
					if(lastStatus!=null && lastStatus.equals("Y")){
						shipment.setLsStatus("Y");
					}else if(lastStatus!=null && lastStatus.equals("N")){
						shipment.setLsStatus("N");
					}else if(lastStatus == null){
						shipment.setLsStatus("N");
					}
				}
			}*/
			
			//service.saveShipment(shipment);
		}else{
			
			/*Collection<ShipmentItem> items = service.getShipmentItem(item.getShipmentId());
			
			boolean lastStatus = false;
			if(items!=null){
				Iterator<ShipmentItem> it = items.iterator();
				
				while(it.hasNext()){
					ShipmentItem itemDb = it.next();
					if(itemDb.getId()!=item.getId()){
						if(itemDb.getLicReq()!=null){
							lastStatus = true;
						}
					}
				}//while loop
			}
			
			if(!lastStatus){
				shipment.setLsStatus(null);
			}*/
			
			item.setLicReq(null);
			item.setRepReq(null);
			item.setScreenedOn(null);
			item.setScreenType(null);
			
			throw new ApplicationException("License Screening fail", new Exception());
		}
				
		return shipment;
	}
	
	private String getShipmentItemStatus(Collection<ShipmentItem> items, ShipmentItem item){
		String lastStatus = null;
		if(items!=null){
			Iterator<ShipmentItem> it = items.iterator();
			
			while(it.hasNext()){
				ShipmentItem itemDb = it.next();
				if(itemDb.getId()!=item.getId()){
					//at least one item requires a license
					if(itemDb.getLicReq()!=null && itemDb.getLicReq().equals("Yes")){
						lastStatus = "Y";
						break;
					}else if(itemDb.getLicReq()!=null && itemDb.getLicReq().equals("No")){
						lastStatus = "N";
					}
				}
			}//while loop
		}
		return lastStatus;
	}
		
	public Shipment prepareBookingCustom(Shipment db, Shipment view){
		
		db.setBookingNo(view.getBookingNo());
		db.setShipmentRefNo(view.getShipmentRefNo());
		db.setTrackingNo(view.getTrackingNo());
		db.setTransportRefNo(view.getTransportRefNo());
		db.setCountryOfOriginName(view.getCountryOfOriginName());
		db.setCountryOfOriginCode(view.getCountryOfOriginCode());
		db.setPortOfExportName(view.getPortOfExportName());
		db.setPortOfExportCode(view.getPortOfExportCode());
		db.setPortOfArrivalName(view.getPortOfArrivalName());
		db.setPortOfArrivalCode(view.getPortOfArrivalCode());
		db.setPortOfUnloadName(view.getPortOfUnloadName());
		db.setPortOfUnloadCode(view.getPortOfUnloadCode());
		db.setShipmentCarrierName(view.getShipmentCarrierName());
		db.setShipmentCarrierCode(view.getShipmentCarrierCode());
		db.setFinalDestAirport(view.getFinalDestAirport());
		db.setVesseFlightNo(view.getVesseFlightNo());
		db.setTransportDocNo(view.getTransportDocNo());
		db.setHazdarous(view.getHazdarous());
		db.setConveyanceName(view.getConveyanceName());
		
		db.setEeiNo(view.getEeiNo());
		db.setAesStatus(view.getAesStatus());
		db.setAesOption(view.getAesOption());
		db.setItn(view.getItn());
		db.setRoutedExpTxn(view.getRoutedExpTxn());
		db.setRelatedPrtyTxn(view.getRelatedPrtyTxn());
		db.setStateOfOrigin(view.getStateOfOrigin());
		db.setFtzNo(view.getFtzNo());
		db.setCorrectionCode(view.getCorrectionCode());
		db.setXtn(view.getXtn());
		
		return db;
	}
	
	
	/**
	 * Prepare shipment document object
	 * @param db
	 * @param view
	 * @return
	 */
	public Shipment prepareShipDoc(Shipment db, Shipment view){
		
		db.setShipmentInstruction(view.getShipmentInstruction());
		db.setSpecialInstruction(view.getSpecialInstruction());
		
		return db;
	}
	
	public String getNextShipNo(String lastShipNo, String sbuCode){
		if(lastShipNo == null){
			sbuCode = sbuCode + "-000001";
			return sbuCode;
		}else{
			String lastNo = lastShipNo.substring(lastShipNo.lastIndexOf("-") + 1);
			Long pId = new Long(lastNo);
			Number no = pId;
			pId = new Long(no.longValue() + 1L);
			return sbuCode+"-" + StringUtils.leftPad(pId.toString(), 5, "0");
		}
	}
	
	public List<String> validateShipment(Shipment shipment){
		List<String> op = new ArrayList<String>();
		
		List<String> exporterValidation = validateExporter(shipment);
		List<String> ultConsValidation = validateUltConsignee(shipment);
		
		
		if(exporterValidation.size() > 0){
			op.addAll(exporterValidation);
		}
		if(ultConsValidation.size() > 0){
			op.addAll(ultConsValidation);
		}
		
		if(shipment.getScreeningStatus()==null || "H".equals(shipment.getScreeningStatus())){
			op.add("Watch list screening fail.");
		}
		if(shipment.getLsStatus()==null || "Yes".equals(shipment.getLsStatus())){
			op.add("License screening fail.");
		}
		
		return op;		
	}
	
		
	/**
	 * check if a string is empty
	 * @params
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}else if("null".equals(s)){
			return true;
		}
		else{
			return true;
		}
	}
	
	public static boolean isEmpty(Double d){
		if(d!=null && !d.toString().isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean validateEmail(final String hex) {
				
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	
	public static String getStatus(String status){
		String statusDesc = "";
		if("P".equals(status)){
			statusDesc = "In progress"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("A".equals(status)){
			statusDesc = "Approved"; 
		}else if("B".equals(status)){
			statusDesc = "Booked"; 
		}else if("N".equals(status)){
			statusDesc = "New"; 
		}else if("S".equals(status)){
			statusDesc = "Shipped"; 
		}else if("D".equals(status)){
			statusDesc = "Validated"; 
		}else if("C".equals(status)){
			statusDesc = "Cancelled"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}else if("H".equals(status)){
			statusDesc = "On Hold"; 
		}
		return statusDesc;
	}

	/**
	 * This method will be removed later after adding ship to and bill to fields on UI
	 * @param shipment
	 */
	public void populateShiBillTo(Shipment shipment){
		shipment.setShipToCode(shipment.getUltConsigneeCode());
		shipment.setShipToName(shipment.getUltConsigneeName());
		shipment.setShipToAddr1(shipment.getUltConsigneeAddrLine1());
		shipment.setShipToAddr2(shipment.getUltConsigneeAddrLine2());
		shipment.setShipToCity(shipment.getUltConsigneeCity());
		shipment.setShipToCountry(shipment.getUltConsigneeCountryCode());
		shipment.setShipToCountryName(shipment.getUltConsigneeCountryName());
		shipment.setShipToState(shipment.getUltConsigneeState());
		shipment.setShipToStateName(shipment.getUltConsigneeStateName());
		shipment.setShipToZip(shipment.getUltConsigneeZip());

		shipment.setBillToCode(shipment.getUltConsigneeCode());
		shipment.setBillToName(shipment.getUltConsigneeName());
		shipment.setBillToAddr1(shipment.getUltConsigneeAddrLine1());
		shipment.setBillToAddr2(shipment.getUltConsigneeAddrLine2());
		shipment.setBillToCity(shipment.getUltConsigneeCity());
		shipment.setBillToCountry(shipment.getUltConsigneeCountryCode());
		shipment.setBillToCountryName(shipment.getUltConsigneeCountryName());
		shipment.setBillToState(shipment.getUltConsigneeState());
		shipment.setBillToStateName(shipment.getUltConsigneeStateName());
		shipment.setBillToZip(shipment.getUltConsigneeZip());		
	}

	public static JasperPrint getReportOp(String shipId, String type, HttpServletRequest req, ConnectionUtil conUtil) throws JRException, ClassNotFoundException, SQLException{
		
		//Compile jrxml file.
		JasperReport jasperReport = JasperCompileManager
	               .compileReport(getFilePath(req, type));
		
		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("shipId", Integer.parseInt(shipId));
		String folderPath = req.getServletContext().getRealPath("/jasper reports/");
		
		parameters.put(JRParameter.REPORT_FILE_RESOLVER, new SimpleFileResolver(new File(folderPath)));
		
		//ConnectionUtil conUtil = new ConnectionUtil();
		
		Connection conn = conUtil.getSQLServerConnection_SQLJDBC();
		
		JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
		
		return print;
	}
	
	public static String getFileName(String type, String shipmentNo){
		if("packingList".equals(type)){
			return "Packing List - "+shipmentNo+".pdf";
		}else if("commercialInvoice".equals(type)){
			return "Commercial Invoice - "+shipmentNo+".pdf";
		}
			
		return null;
	}
	
	private static String getFilePath(HttpServletRequest req, String type){
		if("packingList".equals(type)){
			return req.getServletContext().getRealPath("/jasper reports/packingListReport.jrxml");
		}else if("commercialInvoice".equals(type)){
			return req.getServletContext().getRealPath("/jasper reports/commercialInvoiceNew.jrxml");
		}
		
		return null;
	}
	
	/**
	 * This method is used to validate shipment document email input.
	 */
	public static List<String> validateDocEmail(ShipmentDocEmailDto emailDto){
		List<String> op = new ArrayList<String>();
		
		if(isEmpty(emailDto.getTo())){
			op.add("Received email id required.");
		}
		if(isEmpty(emailDto.getSubject())){
			op.add("Email subject is required.");
		}
		if(isEmpty(emailDto.getMessage())){
			op.add("Email message is required.");
		}
		if(!isEmpty(emailDto.getTo())){
			String[] emails = emailDto.getTo().split(",");
			for(String email: emails){
				if(email!=null){
					email = email.trim();
					boolean validEmail = validateEmail(email);
					if(!validEmail){
						op.add("Invalid email ID : "+email);
					}
				}else{
					op.add("Invalid email ID : "+email);
				}
				
			}
		}
		
		
		return op;
	}
	
	
	public ScreeningEntity prepareScreeningEntity(Shipment shipment, String type){
		ScreeningEntity entity = null;
		
		if("exporter".equals(type)){
			entity = prepareExporterScreenEntity(shipment);
		}else if("ultConsignee".equals(type)){
			entity = prepareUltConsigneeScreenEntity(shipment);
		}
		
		return entity;
	}
	
	private ScreeningEntity prepareExporterScreenEntity(Shipment shipment){
		ScreeningEntity entity = new ScreeningEntity();
		
		entity.setAddressLine1(shipment.getExporterAddressLine1());
		entity.setAddressLine2(shipment.getExporterAddressLine2());
		entity.setCity(shipment.getExporterCity());
		entity.setCountry(shipment.getExporterCountry());
		entity.setCountryName(shipment.getExporterCountryName());
		entity.setEntityCode(shipment.getExporterCode());
		entity.setEntityType("Exporter");
		entity.setName(shipment.getExporterName());
		entity.setState(shipment.getExporterState());
		entity.setStateName(shipment.getExporterStateName());
		entity.setZip(shipment.getExporterZip());
		
		return entity;
	}
	private ScreeningEntity prepareUltConsigneeScreenEntity(Shipment shipment){
		ScreeningEntity entity = new ScreeningEntity();
		
		entity.setAddressLine1(shipment.getUltConsigneeAddrLine1());
		entity.setAddressLine2(shipment.getUltConsigneeAddrLine2());
		entity.setCity(shipment.getUltConsigneeCity());
		entity.setCountry(shipment.getUltConsigneeCountryCode());
		entity.setCountryName(shipment.getUltConsigneeCountryName());
		entity.setEntityCode(shipment.getUltConsigneeCode());
		entity.setEntityType("Consignee");
		entity.setName(shipment.getUltConsigneeName());
		entity.setState(shipment.getUltConsigneeState());
		entity.setStateName(shipment.getUltConsigneeStateName());
		entity.setZip(shipment.getUltConsigneeZip());
		
		return entity;
	}
	
	public Collection<ShipmentDto> parseShipmentOp(Collection<Shipment> shipments) throws IllegalAccessException, InvocationTargetException{
		
		Collection<ShipmentDto> views = new ArrayList<ShipmentDto>(shipments.size());
		
		for(Shipment shipment: shipments){
			ShipmentDto dto = new ShipmentDto();
			
			//BeanUtils.copyProperties(dto, shipment);
			
			dto.setShipmentNo(shipment.getShipmentNo());
			dto.setId(shipment.getId());
			dto.setStatus(shipment.getStatus());
			dto.setExportDate(shipment.getExportDate());
			dto.setCountryOfUltConsigneeName(shipment.getCountryOfUltConsigneeName());
			dto.setCreatedOn(shipment.getCreatedOn());
			dto.setAesStatus(shipment.getAesStatus());
			dto.setShipmentNo(shipment.getShipmentNo());
			
			dto.setDelete(shipment.getId()+"");
			dto.setRates(shipment.getShipmentNo());
			
			views.add(dto);
		}
		
		return views;
	}
	
	public void updateItemStatus(Collection<ShipmentItem> items){
		if(items == null)
			return;
		
		for(ShipmentItem item: items){
			if("Y".equals(item.getLicReq())){
				if(!isEmpty(item.getLicenseNo())){
					item.setLicReq("P");
				} 
			}
		}
	}
	
}
