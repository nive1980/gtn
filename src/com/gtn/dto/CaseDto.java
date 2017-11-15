package com.gtn.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtn.model.CaseLog;

public class CaseDto {

	private String description;
	private String userId;
	private long category;
	private String priority;
	private long ticketId;
	private String createdOn;
	private String status;
	private String statusDesc;
	private String newMessage;
	private List<CaseLogDto> messages;
	private String createdByName;
	private Map<String, Object> selectedProduct;
	private ProductDto selectedSubProduct;
	private String assignedToName ;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String title) {
		this.description = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public String getCreatedOn() {
		
		return createdOn;
	}
	public void setCreatedOn(Date createdOnDate) {
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		this.createdOn = sdfr.format(createdOnDate);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		if("O".equalsIgnoreCase(status))
			statusDesc = "Open";
		else if("C".equalsIgnoreCase(status))
			statusDesc = "Closed";
		else 
			statusDesc = "";
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		if("O".equalsIgnoreCase(status))
			statusDesc = "Open";
		else if("C".equalsIgnoreCase(status))
			statusDesc = "Closed";
		else 
			statusDesc = "";
		this.statusDesc = statusDesc;
	}
	public String getNewMessage() {
		return newMessage;
	}
	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}
	public List<CaseLogDto> getMessages() {
		return messages;
	}
	public void setMessages(List<CaseLogDto> messages) {
		this.messages = messages;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Map<String, Object> getSelectedProduct() {
		return selectedProduct;
	}
	public void setSelectedProduct(Map<String, Object> selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	public ProductDto getSelectedSubProduct() {
		return selectedSubProduct;
	}
	public void setSelectedSubProduct(ProductDto selectedSubProduct) {
		this.selectedSubProduct = selectedSubProduct;
	}
	public String getAssignedToName() {
		return assignedToName;
	}
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	
}
