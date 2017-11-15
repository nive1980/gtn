package com.gtn.service;

import java.io.File;
import java.util.Collection;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.dto.ShipmentDto;
import com.gtn.enums.ShipmentEaseStatus;
import com.gtn.exception.ApplicationException;
import com.gtn.exception.ApplicationRuntimeException;
import com.gtn.helper.ExportOperationServiceHelper;
import com.gtn.model.AesStatusHistory;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.CurrencyValue;
import com.gtn.model.DplAuditValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ModeOfTransportValue;
import com.gtn.model.Model;
import com.gtn.model.PortsValue;
import com.gtn.model.ProductValue;
import com.gtn.model.Shipment;
import com.gtn.model.ShipmentCarton;
import com.gtn.model.ShipmentDocs;
import com.gtn.model.ShipmentItem;
import com.gtn.util.DateUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExportOperationServiceImpl implements ExportOperationService{

	@Autowired
	private GenericDao dao;
	
	@Autowired
	private ExportOperationServiceHelper exportOperationServiceHelper;
	
	@Override
	public Shipment findShipment(int id) throws ApplicationException{
		try{
			Shipment shipment = (Shipment) dao.read(Shipment.class, id);
			return shipment;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in find shipment ", e);
		}
	}
	
	@Override
	public Shipment saveShipment(Shipment shipment) throws ApplicationException, ApplicationRuntimeException{
		
		try{
			if(shipment.getId() > 0){
				dao.update(shipment);
			}else{
				dao.create(shipment);
			}
			return shipment;
		}catch(ConstraintViolationException e){
			e.printStackTrace();
			throw new ApplicationException(e.getMessage(), e);
		}catch(Exception e){
			e.printStackTrace();
			if(e.getCause()!=null && e.getCause().getCause()!=null){
				throw new ApplicationRuntimeException(e.getCause().getCause().getMessage());
			}
			throw new ApplicationRuntimeException(e.getMessage());
		}
	}
	
	@Override
	public Collection<Shipment> getAllShipment(String sbu) throws ApplicationException{
		try {
				String queryString = "select Object(c) from Shipment c where c.sbuCode = ?1";
				Object[] params = { sbu };
				Collection<Shipment> result = null;
				result = dao.findDynamicQuery(queryString, params);
				return result;
			} catch (Exception e) {
				System.out.println("in error@@@@@@@@@@");
				e.printStackTrace();
				throw new ApplicationException("Get all shipment exception ", e);
		}	
	}
	
	
	@Override
	public void removeShipment(int id) throws ApplicationException{
		
		try{
			
			//remove cartons
			String cartonQuery = "Select Object(c) from ShipmentCarton c where shipmentId = ?1";
			Object[] params = {id};
			Collection<ShipmentCarton> cartons = dao.findDynamicQuery(cartonQuery, params);
			
			for(ShipmentCarton carton : cartons){
				dao.delete(carton);
			}
			
			//remove items
			String itemQuery = "Select Object(c) from ShipmentItem c where shipmentId = ?1";			
			Collection<ShipmentItem> items = dao.findDynamicQuery(itemQuery, params);
			
			for(ShipmentItem item : items){
				dao.delete(item);
			}
			
			//remove docs
			String docsQuery = "Select Object(c) from ShipmentDocs c where shipmentId = ?1";
			Collection<ShipmentDocs> docs = dao.findDynamicQuery(docsQuery, params);
			
			for(ShipmentDocs doc : docs){
				removeDocument(doc.getId());
			}
			
			Shipment shipment = (Shipment) dao.read(Shipment.class, id);
			dao.delete(shipment);
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in remove shipment ", e);
		}
		
	}
	
	@Override
	public Collection<Shipment> searchShipment(ShipmentDto view) throws ApplicationException{		
		try{
			StringBuilder queryString = new StringBuilder("select Object(c) from Shipment c where ");
			
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
			
			queryString.append(" c.shipmentNo is not null");
			
			System.out.println("queryString --------------- " + queryString.toString());
			
			Object[] params = {};
			Collection<Shipment> result = null;
			result = dao.findDynamicQuery(queryString.toString(), params);
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in search shipment ", e);
		}

	}
	
	@Override
	public Long searchShipmentCount(ShipmentDto view) throws ApplicationException{
		Object[] params = new Object[15];
		try {
			String queryString = exportOperationServiceHelper.prepareSearchShipmentQuery(view, "COUNT",
					params);
			System.out.println("getQualificationsCount query : " + queryString);
			Long result = dao.findDynamicScalar(queryString, params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("System error", e);
		}
	}
	
	@Override
	public Collection<Shipment> searchShipmentPagination(ShipmentDto view) throws ApplicationException {
		try{
			//StringBuilder queryString = new StringBuilder("Select Object(p) from ExporterValue p where ");
			
			String queryString = exportOperationServiceHelper.prepareSearchShipmentQuery(view, "DATA", new Object[5]);
			
			Object[] params = {};
			Collection<Shipment> result = null;
			result = dao.findDynamicQueryPagination(queryString.toString(), params, view.getOffset(), view.getLimit());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Master Service : Exception in search shipments ", e);
		}
		
	}
	
	@Override
	public Collection<ShipmentItem> getShipmentItem(int shipmentId) throws ApplicationException{
		
		try{
			String queryString = "Select Object(c) from ShipmentItem c where shipmentId = ?1";
			Object[] params = {shipmentId};
			
			Collection<ShipmentItem> results = dao.findDynamicQuery(queryString, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in get items ", e);
		}

	}
	
	@Override
	public Collection<ShipmentCarton> getShipmentCarton(int shipmentId) throws ApplicationException{
		
		try{
			String queryString = "Select Object(c) from ShipmentCarton c where shipmentId = ?1";
			Object[] params = {shipmentId};
			
			Collection<ShipmentCarton> results = dao.findDynamicQuery(queryString, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in get cartons ", e);
		}		
	}
	
	/**
	 * Get next item number from db table
	 */
	@Override
	public int getNextItemNo(Integer shipmentId){
		String queryString = "select MAX(itemNo) from ShipmentItem where shipmentId = ?1";
		Object[] params = {shipmentId};
		
		Collection<Integer> results = dao.findDynamicQuery(queryString, params);
		
		if(results!=null && results.size() > 0){
			if(results.iterator().next() == null){
				return 0;
			}
			return results.iterator().next();
		}else{
			return 0;
		}
	}
	
	@Override
	public ShipmentItem saveShipmentItem(ShipmentItem item) throws ApplicationException{		
		try{
			if(item.getId() > 0){
				dao.update(item);
			}else{
				dao.create(item);
			}
			return item;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in saveItem ", e);
		}
	}
	
	@Override
	public void deleteItem(int itemId) throws ApplicationException{
		if(itemId > 0){
			ShipmentItem item = (ShipmentItem) dao.read(ShipmentItem.class, itemId);
			dao.delete(item);
		}else{
			throw new ApplicationException("Export operation : Item not found", new Exception());
		}
	}
	
	@Override
	public <E> E findEntity(E e, int id) throws ApplicationException{
		try{
			E entity = (E) dao.read(e.getClass(), id);
			return entity;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException("Export operation : Exception in find entity ", ex);
		}		
	}
	
	@Override
	public Model saveEntity(Model entity) throws ApplicationException{
		try{
			dao.create(entity);
			return entity;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException("Export operation : Exception in create entity ", ex);
		}
	}
	
	@Override
	public Model updateEntity(Model entity) throws ApplicationException{
		try{
			dao.update(entity);
			return entity;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException("Export operation : Exception in create entity ", ex);
		}
	}
	
	@Override
	public int getNextCartonNo(Integer shipmentId){
		String queryString = "select MAX(cartonNo) from ShipmentCarton where shipmentId = ?1";
		Object[] params = {shipmentId};
		
		Collection<Integer> results = dao.findDynamicQuery(queryString, params);
		
		if(results!=null && results.size() > 0){
			if(results.iterator().next() == null){
				return 0;
			}
			return results.iterator().next();
		}else{
			return 0;
		}
	}
	
	@Override
	public void deleteCarton(int cartonId) throws ApplicationException{
		if(cartonId > 0){
			ShipmentCarton carton = (ShipmentCarton) dao.read(ShipmentCarton.class, cartonId);
			dao.delete(carton);
		}else{
			throw new ApplicationException("Export operation : Carton not found", new Exception());
		}
	}
	
	@Override
	public ShipmentCarton saveShipmentCarton(ShipmentCarton carton) throws ApplicationException{
		
		try{
			if(carton.getId() > 0){
				dao.update(carton);
			}else{
				dao.create(carton);
			}
			return carton;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in saveItem ", e);
		}
	}
	
	@Override
	public Collection<ShipmentDocs> getShipmentDocs(int shipmentId) throws ApplicationException{	
		
		try{
			String queryString = "select Object(c) from ShipmentDocs c where shipmentId = ?1";
			Object[] params = {shipmentId};
			
			Collection<ShipmentDocs> results = dao.findDynamicQuery(queryString, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in get cartons ", e);
		}
		
	}
	
	@Override
	public void removeDocument(int id) throws ApplicationException{
		try{
			ShipmentDocs doc = (ShipmentDocs) dao.read(ShipmentDocs.class, id);
			
			File file = new File(doc.getFilePath());
			
			if(file.exists()){
				boolean ans = file.delete();
			}
			System.out.println("File deleted ------------------- > "+doc.getFilePath());
			
			dao.delete(doc);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Export operation : Exception in remove shipment ", e);
		}
	}
	
	@Override
	public String getNextShipmentNo(String sbuCode){
		sbuCode = sbuCode+"-";
		String queryString = "select MAX(shipmentNo) from Shipment where shipmentNo LIKE CONCAT('"+sbuCode+"', '%')";
		Object[] params = { };
		
		Collection<String> maxShipNo = dao.findDynamicQuery(queryString, params);
		
		if(maxShipNo!=null && maxShipNo.size() > 0){
			return maxShipNo.iterator().next();
		}
		
		return null;
	}
	
	
	@Override
	public Collection<CurrencyValue> getCurrency(String param) throws ApplicationException{
		try{
			String queryString  = "Select Object(c) from CurrencyValue c where c.description like '%"+param+"%' OR c.currencyCode like '%"+param+"%'";
			Object[] params = { };
			
			Collection<CurrencyValue> currency = dao.findDynamicQuery(queryString, params);
			
			return currency;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting currency .", e);
		}
	}
	
	
	
	@Override
	public Collection<ModeOfTransportValue> getModeOfTransport(String param) throws ApplicationException{
		try{
			String queryString  = "Select Object(c) from ModeOfTransportValue c where c.motDes like '%"+param+"%' OR c.motId like '%"+param+"%' OR  c.typeOfTransport like '%"+param+"%'";
			Object[] params = { };
			
			Collection<ModeOfTransportValue> mot = dao.findDynamicQuery(queryString, params);
			
			return mot;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting currency .", e);
		}
	}
	
	
	
	@Override
	public Collection<Object[]> getShipmentStatusCount() throws ApplicationException{
		
		try{
			String cntQry = "Select count(*), status from Shipment group by status";
			Object[] params = {};
			
			Collection<Object[]> results = dao.findDynamicQuery(cntQry, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment statuses .", e);
		}
	}
	
	@Override
	public Collection<Object[]> getShipmentAesStatusCount() throws ApplicationException{
		
		try{
			String cntQry = "Select count(*), easeStatus from Shipment group by easeStatus";
			Object[] params = {};
			
			Collection<Object[]> results = dao.findDynamicQuery(cntQry, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment AES statuses .", e);
		}
	}

	@Override
	public Collection<Object[]> getShipmentByCountryCount() throws ApplicationException{
		
		try{
			String cntQry = "select count(*), countryOfUltConsigneeName, countryOfUltConsigneeCode from Shipment where countryOfUltConsigneeCode is not null group by countryOfUltConsigneeCode, countryOfUltConsigneeName";
			Object[] params = {};
			
			Collection<Object[]> results = dao.findDynamicQuery(cntQry, params);
			
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment status by country .", e);
		}
	}
	
	@Override
	public Collection<ExporterValue> getExporter(String query) throws ApplicationException{
		String queryString = "select Object(p) from ExporterValue p where upper(p.expCode) like upper(?1) or upper(p.expName) like upper(?1) and p.expName is not null and rtrim(ltrim(p.expName)) !='' order by p.expName ASC";
		Object[] params = { "%"+query+"%" };
		
		try{
			Collection<ExporterValue> exporters = dao.findDynamicQuery(queryString, params);
			return exporters;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment status by country .", e);
		}
	}
	
	@Override
	public Collection<ConsigneeValue> getConsignee(String query) throws ApplicationException{
		String queryString = "select Object(p) from ConsigneeValue p where upper(p.consigneeId) like upper(?1) or upper(p.consigneeName) like upper(?1) and p.consigneeName is not null order by p.consigneeName ASC";
		Object[] params = { "%"+query+"%" };
		
		try{
			Collection<ConsigneeValue> exporters = dao.findDynamicQuery(queryString, params);
			return exporters;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment status by country .", e);
		}
	}
	
	
	@Override
	public Collection<PortsValue> getPorts(String query, String country) throws ApplicationException{
		//queryString = "select Object(p) from ConsigneeValue p where upper(p.consigneeId) like upper(?1) or upper(p.consigneeName) like upper(?1) and p.consigneeName is not null order by p.consigneeName ASC";
		String queryString ="Select c.portCode, c.description from PortsValue c where c.status <> ?1 AND c.country =?2 AND c.portCountry =?3 AND ( c.description like ?4 OR c.portCode like ?4 ) ORDER BY c.portCode ASC";
		Object[] params = { "N", country, country, "%"+query+"%" };
		
		try{
			Collection<PortsValue> ports = dao.findDynamicQueryWithFields(queryString, params, PortsValue.class);
			return ports;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting shipment ports by country .", e);
		}
	}
	
	@Override
	public Shipment getShipment(String shipmentNo){
		String queryString = "select Object(p) from Shipment p where shipmentNo = ?1";
		Collection<Shipment> shipments = dao.findDynamicQuery(queryString, new Object[]{shipmentNo});
		
		if(shipments!=null && shipments.size() > 0){
			return shipments.iterator().next();
		}
		return null;
	}
	
	@Override
	public Collection<AesStatusHistory> getShipmentAesStatusHistory(String shipmentNo) throws ApplicationException{
		String queryString = "select Object(p) from AesStatusHistory p where p.shipmentNo = ?1 order by id desc";
		Object[] params = { shipmentNo };
		try{
			Collection<AesStatusHistory> history = dao.findDynamicQuery(queryString, params);
			
			Shipment shipment = getShipment(shipmentNo);
			
			if(ShipmentEaseStatus.RECEIVED.getType().equals(shipment.getEaseStatus())){
				shipment.setEaseStatus(ShipmentEaseStatus.VIEWED.getType());
				dao.update(shipment);
			}
			
			return history;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting AES shipment status history.", e);
		}
	}

	@Override
	public Collection<Object[]> getShipmentsByValue() throws ApplicationException{
		try{
			String queryString = "select sum(shipmentValue), count(SHIPMENT_NO) as cnt, CAST(createdOn as DATE) from Shipment group by CAST(createdOn as DATE)";
			queryString = "select sum(shipment_value) as total, count(SHIPMENT_NO) as cnt, CAST(created_on as DATE) as dte from shipments group by CAST(created_on as DATE)";
			Object[] params = {};
			Collection<Object[]> results = dao.findDynamicNativeQuery(queryString, params);
			return results;
		}catch(Exception e){
			throw new ApplicationException("Error getting shipment by value", e);
		}
	}

	@Override
	public Collection<DplAuditValue> getDplAudit(String tableKey, String tableName, String entityName) throws ApplicationException{
		
		if(isEmpty(tableKey)){
			throw new ApplicationException("Invalid Input", null);
		}
		
		try{
			Object[] params = {tableName, tableKey, entityName};
			String queryString = "Select Object(p) from DplAuditValue p where p.tablename = ?1 and p.tablekey = ?2 and p.entityName = ?3 order by p.createdOn desc";			
			
			Collection<DplAuditValue> results = dao.findDynamicQuery(queryString, params);
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting audit history in MasterService : ", e);
		}
	}
	
	@Override
	public Collection<ProductValue> getProducts(String query, String sbu) throws ApplicationException{
		String queryString = "select Object(p) from ProductValue p where (upper(p.partNo) like upper(?1) or upper(p.description) like upper(?1) or upper(p.exportClass) like upper(?1) or upper(p.importClass) like upper(?1)) and p.sbuCode = ?2 and p.partNo is not null order by p.partNo ASC";
		Object[] params = { "%"+query+"%", sbu };
		
		try{
			Collection<ProductValue> exporters = dao.findDynamicQuery(queryString, params);
			return exporters;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting products from master table .", e);
		}
	}
	
	@Override
	public String getEccnForProduct(String partNo) throws ApplicationException{
		
		String queryString = "select p.exportClass from ProductValue p where p.partNo = ?1";
		
		Object[] params = { partNo };
			try{
			Collection eccnList = dao.findDynamicQuery(queryString, params);
			
			if(eccnList.size() > 0){
				String objArr = (String) eccnList.iterator().next();
				
				if(objArr == null){
					return null;
				}
				
				return objArr;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting eccn for part .", e);
		}
		
		return null;
	}
	
	
	private boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
}
