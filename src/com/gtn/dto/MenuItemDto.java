package com.gtn.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDto implements Comparable<MenuItemDto>{

	private long menuId;
	private String displayName;
	private String description;
	private long level;
	private long parentmenuId;
	private String status;
	private int itemPosition;
	private String itemUrl;
	private String faIcon;
	private String linkType;
	private Long productId;
	
	//user active
	private String active;
	
	private List<MenuItemDto> subMenu = new ArrayList<MenuItemDto>();
	
	
	
	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}





	public String getDisplayName() {
		return displayName;
	}





	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public long getLevel() {
		return level;
	}





	public void setLevel(long level) {
		this.level = level;
	}





	public long getParentmenuId() {
		return parentmenuId;
	}





	public void setParentmenuId(long parentmenuId) {
		this.parentmenuId = parentmenuId;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public int getItemPosition() {
		return itemPosition;
	}





	public void setItemPosition(int itemPosition) {
		this.itemPosition = itemPosition;
	}





	public String getItemUrl() {
		return itemUrl;
	}





	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}





	public String getFaIcon() {
		return faIcon;
	}





	public void setFaIcon(String faIcon) {
		this.faIcon = faIcon;
	}





	public String getLinkType() {
		return linkType;
	}





	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}





	





	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getActive() {
		return active;
	}





	public void setActive(String active) {
		this.active = active;
	}





	public List<MenuItemDto> getSubMenu() {
		return subMenu;
	}





	public void setSubMenu(List<MenuItemDto> subMenu) {
		this.subMenu = subMenu;
	}





	@Override
	public int compareTo(MenuItemDto o) {
		if(this.itemPosition > o.getItemPosition()){
			return 1;
		}else if(this.itemPosition < o.getItemPosition()){
			return -1;
		}
		return 0;
	}
}
