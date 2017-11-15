package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.dto.ConsigneeDto;
import com.gtn.dto.ExporterDto;
import com.gtn.dto.ProductsDto;
import com.gtn.exception.ApplicationException;
import com.gtn.helper.MasterServiceHelper;
import com.gtn.model.ConsigneeValue;
import com.gtn.model.Country;
import com.gtn.model.DplAuditReasonValue;
import com.gtn.model.DplAuditValue;
import com.gtn.model.DplReasonValue;
import com.gtn.model.ExporterValue;
import com.gtn.model.ProductManufacturerValue;
import com.gtn.model.ProductValue;
import com.gtn.model.State;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class MasterServiceimpl implements MasterService{

	@Autowired
	private GenericDao dao;
	
	@Autowired
	private MasterServiceHelper masterServiceHelper;
	
	@Override
	public Long searchExportersCount(ExporterDto view) throws ApplicationException{
		Object[] params = new Object[15];
		try {
			String queryString = masterServiceHelper.prepareSearchExporterQuery(view, "COUNT",
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
	public Collection<ExporterValue> searchExporters(ExporterDto view) throws ApplicationException {
		try{
			//StringBuilder queryString = new StringBuilder("Select Object(p) from ExporterValue p where ");
			
			String queryString = masterServiceHelper.prepareSearchExporterQuery(view, "DATA", new Object[5]);
			
			/*int start = -1;
			
			if(!isEmpty(view.getLimit()) && !isEmpty(view.getPage())){
				//int start = view.getPage() * view.getLimit();
				//int nextLimit = view.getLimit() + start;
				
				if(view.getPage() <= 1){
					start = 0;
				}else if(view.getPage() > 1){
					start = (view.getPage() - 1) * view.getLimit();
				}
			}
			
			if(start == -1){
				start = 0;
			}*/
			
			/*int start = masterServiceHelper.getStartPage(view.getPage(), view.getLimit());
			
			Object[] params = {};
			Collection<ExporterValue> result = null;
			result = dao.findDynamicQueryPagination(queryString.toString(), params, start, view.getLimit());
			
			return result;*/
			
			//String queryString = masterServiceHelper.prepareSearchExporterQuery(view, "DATA", new Object[5]);
			
			//int start = masterServiceHelper.getStartPage(view.getOffset(), view.getLimit());
			
			Object[] params = {};
			Collection<ExporterValue> result = null;
			result = dao.findDynamicQueryPagination(queryString.toString(), params, view.getOffset(), view.getLimit());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Master Service : Exception in search exporter ", e);
		}
		
	}
	
	@Override
	public Long getDplAudit() throws ApplicationException {
		Long itemNo = new Long(1);

		try {
			String sql = "Select MAX(c.dplAuditNo) from DplAuditValue c ";
			Class[] types = {};
			Object[] params = {};

			Collection result = dao.findDynamicQuery(sql, params);
			Long sNo = (Long) result.iterator().next();
			if (sNo == null) {
				itemNo = new Long(1);
			} else {
				itemNo = new Long(sNo.longValue() + 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Error getting audit number.", e);
		}
		return itemNo;

	}

	@Override
	public Collection<DplAuditValue> getDplAudit(String tableKey, String type) throws ApplicationException{
		
		if(isEmpty(tableKey)){
			throw new ApplicationException("Invalid Input", null);
		}
		
		try{
			String queryString = "Select Object(p) from DplAuditValue p where p.tablekey = ?1 ";
			Object[] params = new Object[2];
			
			params[0] = tableKey;
			
			if(!isEmpty(type)){
				queryString = queryString + "and p.entityName = ?2";
				params[1] = type;
			}
			
			queryString += " order by p.createdOn desc";
			
			Collection<DplAuditValue> results = dao.findDynamicQuery(queryString, params);
			return results;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting audit history in MasterService : ", e);
		}
	}
	
	@Override
	public Collection<DplReasonValue> getDplReason(String statusCode) throws ApplicationException{
		String queryString = null;
		Object[] params = new Object[1];
		
		if(isEmpty(statusCode)){
			queryString = "select Object(p) from DplReasonValue p where p.denyOverride is not null";
		}else{
			queryString = "select Object(p) from DplReasonValue p where p.denyOverride = ?1";
			params[0] = statusCode;
		}
		
		try{
			Collection<DplReasonValue> results = dao.findDynamicQuery(queryString, params);
			return results;
		}catch(Exception e){
			throw new ApplicationException("Error getting DPL Reason masterservice", e);
		}
	}
	
	@Override
	public Collection<Object[]> getAuditReasons(long auditNo) throws ApplicationException{
		try{
			String queryString = "select p.REASON_CODE, p.REASON from DPL_REASON p inner join DPL_AUDIT_REASON r on r.REASON_CODE = p.REASON_CODE where r.DPL_AUDIT_NO = ?1";
			Object[] params = {auditNo};
			Collection<Object[]> reasons = dao.findDynamicNativeQuery(queryString, params);
			return reasons;
		}catch(Exception e){
			throw new ApplicationException("Error getting audit reason in master service : ", e);
		}
	}
	
	@Override
	public Long searchProductCount(ProductsDto view) throws ApplicationException{
		//StringBuilder queryString = new StringBuilder("select distinct(p.part_no), p.export_class, p.import_class, p.MODEL_NO, p.NET_WEIGHT, p.HAZMAT, p.SBU_CODE, p.SKU_NO, p.CREATED_ON from PRODUCTS p left join PRODUCTS_MANUFACTURER m on m.product_id = p.part_no where ");
		
		Object[] params = {};
		Long result = 0L;
		int intResult = 0;
		
		String queryString = masterServiceHelper.prepareSearchProductQuery(view, "COUNT", params);
		
		try{
			Collection c = dao.findDynamicNativeQuery(queryString, params);
			if(c.size() > 0){
				intResult = (Integer) c.iterator().next();
			}
			return new Long(intResult);
		}catch(Exception e){
			throw new ApplicationException("Error in searching parts : MasterServiceImpl ", e);
		}
	}
	
	
	@Override
	public Collection searchProductPagination(ProductsDto view) throws ApplicationException {
		try{
			//StringBuilder queryString = new StringBuilder("Select Object(p) from ExporterValue p where ");
			
			String queryString = masterServiceHelper.prepareSearchProductQuery(view, "DATA", new Object[5]);
						
			int start = masterServiceHelper.getStartPage(view.getOffset(), view.getLimit());
			
			Object[] params = {};
			Collection result = null;
			result = dao.findNativeQueryPagination(queryString.toString(), params, view.getOffset(), view.getLimit());
			
			return result;
		
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Master Service : Exception in search consignee ", e);
		}
		
	}
	
	
	@Override
	public Collection<ProductManufacturerValue> getProductManufacturers(String partNo) throws ApplicationException{
		String queryString = "Select Object(p) from ProductManufacturerValue p where p.partNo = ?1";
		Object[] params = { partNo };
	
		Collection<ProductManufacturerValue> result = null;
		
		try{
			result = dao.findDynamicQuery(queryString, params);
		}catch(Exception e){
			throw new ApplicationException("Error getting product manufacturers in MasterServiceImpl", e);
		}
		return result;
	}

	@Override
	public Long getProductManufactureItemNo(String partNo) throws ApplicationException{
		String queryString = "select max(itemNo) from ProductManufacturerValue where partNo = ?1";
		Object[] params = {partNo};
		
		Long itemNo = new Long(1);
		
		try{
			Class[] types = {};
			Collection result = dao.findDynamicQuery(queryString, params);
			Long sNo = (Long) result.iterator().next();
			if (sNo == null) {
				itemNo = new Long(1);
			} else {
				itemNo = new Long(sNo.longValue() + 1);
			}
		}catch(Exception e){
			throw new ApplicationException("Error getting product manufacture item no - MasterServiceImpl", e);
		}
		
		return itemNo;
	}

	
	@Override
	public Collection<ConsigneeValue> searchConsignee(ConsigneeDto view) throws ApplicationException {
		try{
			StringBuilder queryString = new StringBuilder("Select Object(p) from ConsigneeValue p where ");
			
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
			
			queryString.append(" p.consigneeId is not null ");
			
			if(!isEmpty(view.getSortBy())){
				queryString.append("order by ").append(view.getSortBy()).append(" ").append(view.getDirection());
			}
			
			Object[] params = {};
			Collection<ConsigneeValue> result = null;
			result = dao.findDynamicQuery(queryString.toString(), params);
			
			return result;
		
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Master Service : Exception in search consignee ", e);
		}
		
	}
	
	/**
	 * pagination consignee search
	 */
	
	@Override
	public Long searchConsigneeCount(ConsigneeDto view) throws ApplicationException{
		Object[] params = new Object[15];
		try {
			String queryString = masterServiceHelper.prepareSearchConsigneeQuery(view, "COUNT",
					params);
			System.out.println("getConsigneeCount query : " + queryString);
			Long result = dao.findDynamicScalar(queryString, params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("System error", e);
		}
	}
	
	@Override
	public Collection<ConsigneeValue> searchConsgineePagination(ConsigneeDto view) throws ApplicationException {
		try{
			//StringBuilder queryString = new StringBuilder("Select Object(p) from ExporterValue p where ");
			
			String queryString = masterServiceHelper.prepareSearchConsigneeQuery(view, "DATA", new Object[5]);
						
			int start = masterServiceHelper.getStartPage(view.getOffset(), view.getLimit());
			
			Object[] params = {};
			Collection<ConsigneeValue> result = null;
			result = dao.findDynamicQueryPagination(queryString.toString(), params, view.getOffset(), view.getLimit());
			
			return result;
		
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Master Service : Exception in search consignee ", e);
		}
		
	}
	
	
	@Override
	public void removeExpCons(String tableKey, String type) throws ApplicationException{
		String queryString = "Select Object(p) from DplAuditValue p where p.tablename = ?1 and p.tablekey = ?2";
		
		Object[] params = {type, tableKey}; 
		
		try{
		
			Collection<DplAuditValue> audits = dao.findDynamicQuery(queryString, params) ;
			
			for(DplAuditValue dplVal : audits){
			
				String reasonQuery = "select Object(p) from DplAuditReasonValue p where dplAuditNo = ?1";
				
				Object[] reasonParams = {dplVal.getDplAuditNo()};
				
				Collection<DplAuditReasonValue> reasons = dao.findDynamicQuery(reasonQuery, reasonParams);
				
				for(DplAuditReasonValue reason: reasons)
					dao.delete(reason);
				
				dao.delete(dplVal);
			
			}
			
			if("exporter".equals(type)){
				ExporterValue exporter = (ExporterValue) dao.read(ExporterValue.class, tableKey);
			   	dao.delete(exporter);
			}else if("consignee".equals(type)){
				ConsigneeValue consignee = (ConsigneeValue) dao.read(ConsigneeValue.class, tableKey);
			   	dao.delete(consignee);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error in master service impl - ", e);
		}
	}
	
	@Override
	public Collection<Country> getCountry(String param) throws ApplicationException{
		try{
			String queryString  = "Select Object(c) from Country c where c.countryCode like '%"+param+"%' OR c.countryName like '%"+param+"%'";
			Object[] params = { };
			
			Collection<Country> country = dao.findDynamicQuery(queryString, params);
			
			return country;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting country .", e);
		}
	}
	

	@Override	
	public Collection<State> getStates(String countryCode, String qry) throws ApplicationException{
		
		try{
			StringBuilder queryString  = new StringBuilder("Select c.stateCode, c.stateName, c.countryCode from State c where ");
			Object[] params = new Object[2];
			int paramCount = 0;
			/*params = 
			params[0] = countryCode;
			params[1] = qry;*/
			
			if(!isEmpty(qry)){
				queryString.append("(upper(c.stateCode) like upper(?").append(paramCount+1).append(") or upper(c.stateName) like upper(?").append(paramCount+1).append(")) and ");
				params[paramCount++] = '%'+qry+'%';
			}
			if(!isEmpty(countryCode)){			
				queryString.append("upper(c.countryCode) = upper(?").append(paramCount+1).append(") and ");
				params[paramCount++] = countryCode;
			}
			
			queryString.append(" c.stateCode is not null order by c.stateName");
			
			/*else{
				params = new Object[1];
				params[0] = countryCode;
			}*/
			
			
			Collection<State> states = dao.findDynamicQueryWithFields(queryString.toString(), params, State.class);
			
			return states;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting states for country.", e);
		}
	}
	
	/*public Collection<State> getStates(String qry, String countryCode){
		StringBuilder queryString = new StringBuilder("select p.stateCode, p.stateName from State p where ");
		Object[] params = new Object[5];
		
		if(!isEmpty(countryCode)){
			queryString.append("p.countryCode = ?1 ").append(" and "); 
		}
	}*/
	
	
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
