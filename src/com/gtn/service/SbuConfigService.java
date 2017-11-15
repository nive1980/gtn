package com.gtn.service;

import java.util.Collection;

import com.gtn.dto.SbuConfigView;
import com.gtn.exception.ApplicationException;
import com.gtn.model.SbuConfigValue;

public interface SbuConfigService {

	public Collection<SbuConfigValue> searchSbuConfig(SbuConfigView view) throws ApplicationException;
	
}
