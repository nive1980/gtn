package com.gtn.service;

import java.io.IOException;

import com.gtn.dto.LicenseScreeningInput;
import com.gtn.dto.ProductLSResponse;

public interface LicenceScreeningService {

	public String doLicenceScreening(LicenseScreeningInput input) throws IOException;
	public String authenticateUser() throws IOException;
	public ProductLSResponse doLicenceScreeningNew(LicenseScreeningInput input) throws IOException;
	
}
