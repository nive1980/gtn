package com.gtn.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PublicationCartDto {
	private BigDecimal TotalCost;
	private int totalCount;
	private ArrayList <PublicationDto> publicationDtos;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(BigDecimal TotalCost) {
		this.TotalCost = TotalCost;
	}
	public void setPublications(ArrayList<PublicationDto> publicationDtos) {
		this.publicationDtos=publicationDtos;
	}
	public ArrayList<PublicationDto> getPublications(){
		return publicationDtos;
	}
	
		
}
