package com.gtn.dto;

import java.util.Collection;

public class GridResponseDto {

	private Collection results;
	private Long totalResultCount;
	public Collection getResults() {
		return results;
	}
	public void setResults(Collection results) {
		this.results = results;
	}
	public Long getTotalResultCount() {
		return totalResultCount;
	}
	public void setTotalResultCount(Long totalResultCount) {
		this.totalResultCount = totalResultCount;
	}
	
	
	
	
	
}
