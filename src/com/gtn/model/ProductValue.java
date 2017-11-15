package com.gtn.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTS")
public class ProductValue implements Serializable, Model{

	private String partNo;
	private String description;
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
	private String hazmatCode;
	private String hazmatDesc;
	private String flashPointTemp;
	private String hazmatContactName;
	private String hazmatContactPhone;
	
	private String sbuCode;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	@Id
	@Column(name="PART_NO")
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	@Column(name="PART_DESC")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="PART_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="MATERIAL_TYPE")
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	
	@Column(name="UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name="SKU_NO")
	public String getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	
	@Column(name="MODEL_NO")
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	@Column(name="NET_WEIGHT")
	public BigDecimal getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}
	
	@Column(name="UOM_PRICE")
	public String getUomForPrice() {
		return uomForPrice;
	}
	public void setUomForPrice(String uomForPrice) {
		this.uomForPrice = uomForPrice;
	}
	
	@Column(name="EXPORT_CLASS")
	public String getExportClass() {
		return exportClass;
	}
	public void setExportClass(String exportClass) {
		this.exportClass = exportClass;
	}
	
	@Column(name="IMPORT_CLASS")
	public String getImportClass() {
		return importClass;
	}
	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}
	
	@Column(name="HAZMAT")
	public boolean isHazmat() {
		return hazmat;
	}
	public void setHazmat(boolean hazmat) {
		this.hazmat = hazmat;
	}
	
	@Column(name="HAZMAT_CODE")
	public String getHazmatCode() {
		return hazmatCode;
	}
	public void setHazmatCode(String hazmatCode) {
		this.hazmatCode = hazmatCode;
	}
	
	@Column(name="HAZMAT_DESC")
	public String getHazmatDesc() {
		return hazmatDesc;
	}
	public void setHazmatDesc(String hazmatDesc) {
		this.hazmatDesc = hazmatDesc;
	}
	
	@Column(name="FLASH_POINT_TEMP")
	public String getFlashPointTemp() {
		return flashPointTemp;
	}
	public void setFlashPointTemp(String flashPointTemp) {
		this.flashPointTemp = flashPointTemp;
	}
	
	@Column(name="HAZMAT_CONTACT_NAME")
	public String getHazmatContactName() {
		return hazmatContactName;
	}
	public void setHazmatContactName(String hazmatContactName) {
		this.hazmatContactName = hazmatContactName;
	}
	
	@Column(name="HAZMAT_CONTACT_PHONE")
	public String getHazmatContactPhone() {
		return hazmatContactPhone;
	}
	public void setHazmatContactPhone(String hazmatContactPhone) {
		this.hazmatContactPhone = hazmatContactPhone;
	}
	
	@Column(name="CREATED_BY", updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_ON", updatable = false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="UPDATED_ON")
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@Column(name="SBU_CODE")
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

}
