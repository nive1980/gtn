package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CARTON_HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class CartonHeader {
	
	@XmlElement(name="PACKAGE_NUMBER")
	private int packageNo;
    		
    @XmlElement(name="CARTON_NUMBER") 		
	private int cartonNo;
    		
    @XmlElement(name="HAZARDOUS")
	private String haszmat;
	
    @XmlElement(name="DIMENSION_UOM")
    private String dimUom;
    
    @XmlElement(name="WEIGHT_UOM")
	private String weightUom;
    
    @XmlElement(name="LENGTH")
	private Double length;
    
    @XmlElement(name="WIDTH")
	private Double width;
    
    @XmlElement(name="HEIGHT")
	private Double height;
    
    @XmlElement(name="CARTON_GROSS_WEIGHT")
	private Double grossWeight;
    
    @XmlElement(name="CARTON_NET_WEIGHT")
	private Double netWeight;

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
    
    
	
}
