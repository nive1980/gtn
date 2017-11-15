package com.gtn.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartDto {

	private List<ShoppingCartItemDto> items;
	
	private String userId;
	
	public List<ShoppingCartItemDto> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCartItemDto> items) {
		this.items = items;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
