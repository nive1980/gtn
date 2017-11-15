package com.gtn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENU")
public class MenuValue implements Model{
	
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
	private Integer productId;
	
	@Id
	@Column(name = "MENU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	@Column(name = "DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "LEVEL")
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	@Column(name = "PARENT_MENU_ID")
	public long getParentmenuId() {
		return parentmenuId;
	}
	public void setParentmenuId(long parentmenuId) {
		this.parentmenuId = parentmenuId;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "ITEM_POSITION")
	public int getItemPosition() {
		return itemPosition;
	}
	public void setItemPosition(int itemPosition) {
		this.itemPosition = itemPosition;
	}
	
	@Column(name="ITEM_URL")
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	
	@Column(name="FA_ICON")
	public String getFaIcon() {
		return faIcon;
	}
	public void setFaIcon(String faIcon) {
		this.faIcon = faIcon;
	}

	@Column(name="LINK_TYPE")
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	@Column(name="PRODUCT_ID")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public boolean equals(Object o){
		
		if (o == this) {
            return true;
        }
		
		if (!(o instanceof MenuValue)) {
            return false;
        }
		
		MenuValue p = (MenuValue) o;
		
		if(this.getMenuId() == p.getMenuId())
			return true;
		
		return false;
	}
}
