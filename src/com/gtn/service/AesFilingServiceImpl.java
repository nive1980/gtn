package com.gtn.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.enums.ShipmentEaseStatus;
import com.gtn.exception.ApplicationException;
import com.gtn.model.AesStatusHistory;
import com.gtn.model.Shipment;
import com.gtn.webservices.dto.ShipmentDetail;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AesFilingServiceImpl implements AesFilingService{

	@Autowired
	private GenericDao dao;
	
	@Autowired
	private ExportOperationService expService;
	
	private static final Logger logger = LoggerFactory.getLogger(AesFilingServiceImpl.class); 
	
	@Override
	public Collection<String> getAesSubmmitedShipments() throws ApplicationException {
		String queryString = "select s.shipmentNo from Shipment s where s.easeStatus = ?1";
		Collection<String> shipmentNos = dao.findDynamicQuery(queryString, new Object[]{ ShipmentEaseStatus.SUBMITTED.getType() });
		
		return shipmentNos;
	}

	@Override
	public List<String> updatedAesResponse(Set<ShipmentDetail> response){
		
		for(ShipmentDetail s: response){
			
			if(s==null){
				logger.error(" ********** Empty shipment received ********** ");
			}else{
				Shipment shipment = expService.getShipment(s.getShipmentNo());

				//persist audit record
				persistAesAudit(s, shipment);
				
				shipment.setAesOption(s.getAesOption());
				shipment.setAesStatus(s.getShipmentCustomFilingStatus());
				shipment.setXtn(s.getXtn());
				shipment.setItn(s.getItnNo());
				shipment.setCorrectionCode(s.getCorrectionCode());
		
				shipment.setEaseStatus(ShipmentEaseStatus.RECEIVED.getType());
				
				/**
				 *	persist AES changes to GTN
				 */
				dao.update(shipment);
			}			
		}
		
		return null;
	}
	
	private void persistAesAudit(ShipmentDetail detail, Shipment shipment){
		AesStatusHistory audit = new AesStatusHistory();
		
		audit.setNewAes(detail.getShipmentCustomFilingStatus());
		audit.setNewCorrectionCode(detail.getCorrectionCode());
		audit.setNewItn(detail.getItnNo());
		audit.setNewXtn(detail.getXtn());
		
		audit.setOldAes(shipment.getAesStatus());
		audit.setOldCorrectionCode(shipment.getCorrectionCode());
		audit.setOldItn(shipment.getItn());
		audit.setOldXtn(shipment.getXtn());
		audit.setRespondedOn(new Date());
		audit.setShipmentNo(shipment.getShipmentNo());
		audit.setSubmittedOn(shipment.getEaseSubmitDate());
		
		dao.create(audit);
	}
	
	public static boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
}
