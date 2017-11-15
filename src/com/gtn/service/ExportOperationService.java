package com.gtn.service;

import java.util.Collection;

import com.gtn.dto.ShipmentDto;
import com.gtn.exception.ApplicationException;
import com.gtn.exception.ApplicationRuntimeException;
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

public interface ExportOperationService {
	
	public Shipment saveShipment(Shipment shipment) throws ApplicationException, ApplicationRuntimeException;
	public Collection<Shipment> getAllShipment(String sbu) throws ApplicationException;
	public void removeShipment(int id) throws ApplicationException;
	public Collection<Shipment> searchShipment(ShipmentDto view) throws ApplicationException;
	public Shipment findShipment(int id) throws ApplicationException;
	
	public Collection<ShipmentItem> getShipmentItem(int shipmentId) throws ApplicationException;
	public Collection<ShipmentCarton> getShipmentCarton(int shipmentId) throws ApplicationException;
	public int getNextItemNo(Integer shipmentId);
	public ShipmentItem saveShipmentItem(ShipmentItem item) throws ApplicationException;
	public void deleteItem(int itemId) throws ApplicationException;
	public <E> E findEntity(E e, int id) throws ApplicationException;

	public int getNextCartonNo(Integer shipmentId);
	public void deleteCarton(int cartonId) throws ApplicationException;
	public ShipmentCarton saveShipmentCarton(ShipmentCarton carton) throws ApplicationException;

	public Collection<ShipmentDocs> getShipmentDocs(int shipmentId) throws ApplicationException;
	public Model saveEntity(Model entity) throws ApplicationException;
	public void removeDocument(int id) throws ApplicationException;
	
	public Model updateEntity(Model entity) throws ApplicationException;
	public String getNextShipmentNo(String sbuCode);
	
	public Collection<CurrencyValue> getCurrency(String param) throws ApplicationException;
	
	public Collection<Object[]> getShipmentStatusCount() throws ApplicationException;
	public Collection<Object[]> getShipmentByCountryCount() throws ApplicationException;
	public Collection<ExporterValue> getExporter(String query) throws ApplicationException;
	public Collection<ConsigneeValue> getConsignee(String query) throws ApplicationException;
	public Collection<ModeOfTransportValue> getModeOfTransport(String param) throws ApplicationException;
	public Collection<PortsValue> getPorts(String query, String country) throws ApplicationException;
	public Shipment getShipment(String shipmentNo);
	public Collection<AesStatusHistory> getShipmentAesStatusHistory(String shipmentNo) throws ApplicationException;
	public Collection<Object[]> getShipmentAesStatusCount() throws ApplicationException;
	public Collection<Object[]> getShipmentsByValue() throws ApplicationException;
	public Collection<DplAuditValue> getDplAudit(String tableKey, String tableName, String entityName) throws ApplicationException;
	public Long searchShipmentCount(ShipmentDto view) throws ApplicationException;
	public Collection<Shipment> searchShipmentPagination(ShipmentDto view) throws ApplicationException;
	public Collection<ProductValue> getProducts(String query, String sbu) throws ApplicationException;
	public String getEccnForProduct(String partNo) throws ApplicationException;
	
}
	