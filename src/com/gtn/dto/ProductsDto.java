package com.gtn.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductsDto implements Serializable{

	private String partNo;
	private String partDesc;
	private String type;
	private String materialType;
	private BigDecimal unitPrice;
	private String currency;
	private String skuNo;
	private String modelNo;
	private BigDecimal netWeight;
	private String uomForPrice;
	private String exportClass;
	private String importClass;
	
	private boolean hazmat;
	private String hazmatStr;
	private String hazmatCode;
	private String hazmatDesc;
	private String flashPointTemp;
	private String hazmatContactName;
	private String hazmatContactPhone;
	
	private String sbu;
	private String reqType;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	private List<String> msgs;
	
	private String sortBy;
	private String direction;
	private String reqNavType;
	
	private Integer offset;
	private Integer limit;
	private String delete;
	
	private ProductManufacturerDto manufacture;

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPartDesc() {
		return partDesc;
	}

	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getUomForPrice() {
		return uomForPrice;
	}

	public void setUomForPrice(String uomForPrice) {
		this.uomForPrice = uomForPrice;
	}

	public String getExportClass() {
		return exportClass;
	}

	public void setExportClass(String exportClass) {
		this.exportClass = exportClass;
	}

	public String getImportClass() {
		return importClass;
	}

	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}

	public boolean isHazmat() {
		return hazmat;
	}

	public void setHazmat(boolean hazmat) {
		this.hazmat = hazmat;
	}

	public String getHazmatCode() {
		return hazmatCode;
	}

	public void setHazmatCode(String hazmatCode) {
		this.hazmatCode = hazmatCode;
	}

	public String getHazmatDesc() {
		return hazmatDesc;
	}

	public void setHazmatDesc(String hazmatDesc) {
		this.hazmatDesc = hazmatDesc;
	}

	public String getFlashPointTemp() {
		return flashPointTemp;
	}

	public void setFlashPointTemp(String flashPointTemp) {
		this.flashPointTemp = flashPointTemp;
	}

	public String getHazmatContactName() {
		return hazmatContactName;
	}

	public void setHazmatContactName(String hazmatContactName) {
		this.hazmatContactName = hazmatContactName;
	}

	public String getHazmatContactPhone() {
		return hazmatContactPhone;
	}

	public void setHazmatContactPhone(String hazmatContactPhone) {
		this.hazmatContactPhone = hazmatContactPhone;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	

	public ProductManufacturerDto getManufacture() {
		return manufacture;
	}

	public void setManufacture(ProductManufacturerDto manufacture) {
		this.manufacture = manufacture;
	}

	public String getHazmatStr() {
		return hazmatStr;
	}

	public void setHazmatStr(String hazmatStr) {
		this.hazmatStr = hazmatStr;
	}

	public String getReqNavType() {
		return reqNavType;
	}

	public void setReqNavType(String reqNavType) {
		this.reqNavType = reqNavType;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	
	
}
