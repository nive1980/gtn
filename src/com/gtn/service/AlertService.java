package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Alert;

public interface AlertService {
	
	public Collection<Alert> getAlerts(long userId) throws ApplicationException;

	public void saveAlert(Alert alert) throws ApplicationException;
}
