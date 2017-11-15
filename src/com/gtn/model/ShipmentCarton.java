package com.gtn.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SHIPMENT_CARTON")
@Access(AccessType.PROPERTY)
public class ShipmentCarton implements Model{
	
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
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SHIPMENT_ID")
	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	@Column(name = "PACKAGE_NO")
	public int getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(int packageNo) {
		this.packageNo = packageNo;
	}

	@Column(name = "CARTON_NO")
	public int getCartonNo() {
		return cartonNo;
	}

	public void setCartonNo(int cartonNo) {
		this.cartonNo = cartonNo;
	}

	@Column(name = "HAZMAT")
	public String getHaszmat() {
		return haszmat;
	}

	public void setHaszmat(String haszmat) {
		this.haszmat = haszmat;
	}

	@Column(name = "DIMENSION_UOM")
	public String getDimUom() {
		return dimUom;
	}

	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}

	@Column(name = "WEIGHT_UOM")
	public String getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}

	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	
	@Transient
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
}
