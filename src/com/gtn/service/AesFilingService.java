package com.gtn.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.gtn.exception.ApplicationException;
import com.gtn.webservices.dto.ShipmentDetail;

public interface AesFilingService {
	public Collection<String> getAesSubmmitedShipments() throws ApplicationException;
	public List<String> updatedAesResponse(Set<ShipmentDetail> response);
}
