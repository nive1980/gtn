package com.gtn.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBException;

import com.gtn.dto.ScreeningEntity;
import com.gtn.dto.WlsScreeningResponse;
import com.gtn.exception.ApplicationException;

public interface WatchListScreeningService {
	
	public String doWatchListScreening(ScreeningEntity entity) throws JAXBException, IOException, ApplicationException;
	public WlsScreeningResponse translateXmlResponse(String xmlStr) throws JAXBException, UnsupportedEncodingException;

}
