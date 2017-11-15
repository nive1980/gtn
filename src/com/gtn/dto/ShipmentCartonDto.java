package com.gtn.dto;

import java.util.List;

public class ShipmentCartonDto {
	private int id;
	private int shipmentId;
	private int packageNo;
	private int cartonNo;
	private String haszmat;
	private String dimUom;
	private String weightUom;
	private Double length;
	private Double width;
	private Double height;
	private Double grossWeight;
	private Double netWeight;
	
	private List<String> msgs;
	
	private Integer limit;
	private Integer page;
	private String filter;
	
	private String delete;
	private Integer offset;
	private String copy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public int getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(int packageNo) {
		this.packageNo = packageNo;
	}
	public int getCartonNo() {
		return cartonNo;
	}
	public void setCartonNo(int cartonNo) {
		this.cartonNo = cartonNo;
	}
	public String getHaszmat() {
		return haszmat;
	}
	public void setHaszmat(String haszmat) {
		this.haszmat = haszmat;
	}
	public String getDimUom() {
		return dimUom;
	}
	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}
	public String getWeightUom() {
		return weightUom;
	}
	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public String getCopy() {
		return copy;
	}
	public void setCopy(String copy) {
		this.copy = copy;
	}
	
	
}
