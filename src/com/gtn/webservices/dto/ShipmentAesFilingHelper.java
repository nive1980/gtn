package com.gtn.webservices.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Shipment;
import com.gtn.model.ShipmentCarton;
import com.gtn.model.ShipmentItem;
import com.gtn.service.ExportOperationService;
import com.gtn.util.DateUtil;

public class ShipmentAesFilingHelper {

	private static final Logger logger = LoggerFactory.getLogger(ShipmentAesFilingHelper.class);
	
	public ShipmentFilingRequest prepareShipmentFilingReq(Shipment shipment, ExportOperationService expoprtOpService) throws ApplicationException{
		ShipmentFilingRequest req = new ShipmentFilingRequest();
		
		req.setMsgHeader(prepareMsgHeader());
		req.setShipmentHeader(getHeader(shipment));
		req.setInvoiceBackItem(new InvoiceBackOrderedItem());
		req.setInvoiceHeader(getInvoiceHeader(shipment, expoprtOpService));
		req.setInvoiceItem(getShipmentItems(shipment.getId(), expoprtOpService));
		
		return req;
	}
	
	private MessageHeader prepareMsgHeader(){
		MessageHeader msgHeader = new MessageHeader();
		
		msgHeader.setUserId("WZFKArU0b9Xsv4L1ET7G1g==");
		msgHeader.setPassword("tIUtf/vlEoJmKAp964wg6Q==");
		msgHeader.setMessageType("ERPR");
		
		return msgHeader;
	}
	
	private ShipmentHeader getHeader(Shipment shipment){
		
		ShipmentHeader details = new ShipmentHeader();
		
		shipment.setSbuCode("GTN");
		//approve shipment for AES filing in EASE
		shipment.setStatus("A");
		shipment.setScreeningStatus("A");
		
		BeanUtils.copyProperties(shipment, details);
		
		if(shipment.getExportDate()!=null){
			details.setExportDate(DateUtil.format(shipment.getExportDate(), "dd/MM/yyyy"));
		}
		
		details.setUltConsigneeStatus("A");
		details.setInterConsStatus("A");
		details.setFfStatus("A");
		details.setSkipWlsCheck("Y");
		//details.setAesStatus("N");
		
		String ultConsContactName = "";
		
		if(shipment.getUltConsigneeFirstName()!=null){
			ultConsContactName = shipment.getUltConsigneeFirstName()+" ";
		}
		if(shipment.getUltConsigneeLastName()!=null){
			ultConsContactName = shipment.getUltConsigneeLastName();
		}
		
		details.setUltConsigneeContactName(ultConsContactName);
		
		/*details.setCountryOfUltDest(shipment.getCountryOfUltConsigneeCode());
		details.setCountryOfUltDestName(shipment.getCountryOfUltConsigneeName());
		
		details.setDateOfExport(DateUtil.format(shipment.getExportDate(), "dd/MM/yyyy"));
		details.setExporterAddrLine1(shipment.getExporterAddressLine1());
		details.setExporterAddrLine2(shipment.getExporterAddressLine2());
		details.setExporterCity(shipment.getExporterCity());
		details.setExporterCode(shipment.getExporterCode());
		details.setExporterFirstName(shipment.getExporterFirstName());
		details.setExporterIdNo(shipment.getUsppiExporterId());
		details.setExporterIdType(shipment.getUsppiExporterIdType());
		details.setExporterLastName(shipment.getExporterLastName());
		details.setExporterMiddleName("");
		details.setExporterName(shipment.getExporterName());
		details.setExporterPhone(shipment.getExporterPhoneNo());
		details.setExporterState(shipment.getExporterState());
		details.setExporterZip(shipment.getExporterZip());
		details.setShipmentNo(shipment.getShipmentNo());
		details.setUltConsAddrLine1(shipment.getUltConsigneeAddrLine1());*/
		
		return details;
	}
	
	private List<InvoiceItem> getShipmentItems(int id, ExportOperationService expoprtOpService) throws ApplicationException{
		Collection<ShipmentItem> items = expoprtOpService.getShipmentItem(id);
		List<InvoiceItem> xmlItems = new ArrayList<InvoiceItem>(items.size());
		
		for(ShipmentItem item: items){
			InvoiceItem invoiceItem = new InvoiceItem();
			BeanUtils.copyProperties(item, invoiceItem);
			xmlItems.add(invoiceItem);
		}
		return xmlItems;
	}
	
	private InvoiceHeader getInvoiceHeader(Shipment shipment, ExportOperationService expoprtOpService) throws ApplicationException{
		InvoiceHeader invoiceHeader = new InvoiceHeader();
		
		BeanUtils.copyProperties(shipment, invoiceHeader);

		if(shipment.getExportDate()!=null){
			invoiceHeader.setExportDate(DateUtil.format(shipment.getExportDate(), "MM/dd/yyyy"));
			invoiceHeader.setOrderDate(DateUtil.format(shipment.getExportDate(), "MM/dd/yyyy"));
		}
		
		invoiceHeader.setInvoiceNo(shipment.getShipmentNo());
		invoiceHeader.setCartons(getCartonHeaders(shipment.getId(), expoprtOpService));
		
		return invoiceHeader;
	}
	
	private List<CartonHeader> getCartonHeaders(int shipmentId, ExportOperationService expoprtOpService) throws ApplicationException{
		Collection<ShipmentCarton> cartons = expoprtOpService.getShipmentCarton(shipmentId);
		
		if(cartons!=null){
			List<CartonHeader> cartonHeaders = new ArrayList<CartonHeader>(cartons.size());
			
			cartonHeaders = cartons.stream().map(x -> getCartonHeader(x)).collect(Collectors.toList());
			
			return cartonHeaders;
		}else{
			return null;
		}
		
	}
	
	private CartonHeader getCartonHeader(ShipmentCarton carton){
		CartonHeader ch = new CartonHeader();
		BeanUtils.copyProperties(carton, ch);
		return ch;
	}
	
	
	/***
	 * Read Shipment from EASE START
	 */
	public ShipmentDetailsReq getEaseShipmentRequest(List<String> shipNos){
		ShipmentDetailsReq request = new ShipmentDetailsReq();

		Shipments shipments = new Shipments();
		shipments.setShipment(shipNos);
		
		request.setMessageHeader(prepareMsgHeader());
		request.setShipments(shipments);
		
		return request;
	}
	
	/**
	 * Process EASE get shipment response for AES
	 * @param response
	 * @param service
	 * @return
	 * @throws ApplicationException 
	 */
	public Set<ShipmentDetail> processShipmentResponse(ShipmentDetailsRes response, ExportOperationService service) throws ApplicationException{
		
		String status = response.getStatus();
		
		if(status != null && "SUCCESS".equals(status)){
			if(response.getShipmentsDetails()!=null){
				List<ShipmentDetail> shipments = response.getShipmentsDetails().getShipmentDetail();
				
				Set<ShipmentDetail> result = processShipment(shipments, service);
				return result;
				
			}else{
				logger.error("********** Shipment details = null ********** \n"+response.getHostedError());
			}
			
		}else{
			logger.error("********** Error reading shipment from EASE ********** \n"+response.getHostedError());
		}
		
		return null;
	}
	
	/**
	 * Update status of each shipment
	 * @param shipments
	 * @param service
	 * @return
	 * @throws ApplicationException 
	 */
	private Set<ShipmentDetail> processShipment(List<ShipmentDetail> shipments, ExportOperationService service) throws ApplicationException{
		
		Set<ShipmentDetail> result = new HashSet<ShipmentDetail>();
		
		for(ShipmentDetail shipment : shipments){
			boolean isAesUpdated = isAesUpdated(shipment, service);
			if(isAesUpdated){
				result.add(shipment);
			}else{
				logger.info("********* AES Response not received for shipment :: "+shipment.getShipmentNo()+" ********* ");
			}
		}
		
		return result;
	}
	
	/**
	 * Compare EASE shipment with GTN to see if AES response was received
	 * @param shipment
	 * @param service
	 * @return
	 * @throws ApplicationException 
	 */
	private boolean isAesUpdated(ShipmentDetail shipment, ExportOperationService service) throws ApplicationException{
		
		Shipment shipmentValue = (Shipment) service.getShipment(shipment.getShipmentNo());
		
		boolean updated = false;
		
		if(shipmentValue!=null){
			updated = compareString(shipment.getAesOption(), shipmentValue.getAesOption());
			if(!updated){
				updated = compareString(shipment.getShipmentCustomFilingStatus(), shipmentValue.getAesStatus());
			}if(!updated){
				updated = compareString(shipment.getShipmentStatus(), shipmentValue.getStatus());
			}if(!updated){
				updated = compareString(shipment.getItnNo(), shipmentValue.getItn());
			}if(!updated){
				updated = compareString(shipment.getXtn(), shipmentValue.getXtn());
			}if(!updated){
				updated = compareString(shipment.getCorrectionCode(), shipmentValue.getCorrectionCode());
			}
		}else{
			logger.error("********** Shipment Not found in GTN ********** "+shipment.getShipmentNo());
		}
		
		return updated;
	}

	public static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Compare two strings
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean compareString(String s1, String s2){
		boolean changed = false;
		
		if(!isEmpty(s1) && isEmpty(s2)){
			changed = true;
		}else if(isEmpty(s1) && !isEmpty(s2)){
			changed = true;
		}else if(!isEmpty(s1) && !isEmpty(s2)){
			if(!s1.equals(s2)){
				changed = true;
			}
		}
		
		return changed;
	}
	
}
