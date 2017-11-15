package com.gtn.service;

import java.util.Collection;

import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.ExporterDto;
import com.gtn.dto.ProductsDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.Country;
import com.gtn.model.DplAuditValue;
import com.gtn.model.DplReasonValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ProductManufacturerValue;
import com.gtn.model.ProductValue;
import com.gtn.model.State;

public interface MasterService {

	public Long searchExportersCount(ExporterDto view) throws ApplicationException;
	public Collection<ExporterValue> searchExporters(ExporterDto view) throws ApplicationException;
	public Long getDplAudit() throws ApplicationException;
	public Collection<DplAuditValue> getDplAudit(String tableKey, String type) throws ApplicationException;
	public Collection<DplReasonValue> getDplReason(String statusCode) throws ApplicationException;
	public Collection<Object[]> getAuditReasons(long auditNo) throws ApplicationException;
	//public Collection searchProducts(ProductsDto view) throws ApplicationException;
	public Collection<ProductManufacturerValue> getProductManufacturers(String partNo) throws ApplicationException;
	public Long getProductManufactureItemNo(String partNo) throws ApplicationException;
	public Collection<ConsigneeValue> searchConsignee(ConsigneeDto view) throws ApplicationException;
	public void removeExpCons(String tableKey, String type) throws ApplicationException;
	public Collection<Country> getCountry(String param) throws ApplicationException;
	public Collection<State> getStates(String countryCode, String qry) throws ApplicationException;
	public Long searchConsigneeCount(ConsigneeDto view) throws ApplicationException;
	public Collection<ConsigneeValue> searchConsgineePagination(ConsigneeDto view) throws ApplicationException;

	public Long searchProductCount(ProductsDto view) throws ApplicationException;
	public Collection searchProductPagination(ProductsDto view) throws ApplicationException;
}
