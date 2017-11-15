package com.gtn.controller;

import java.util.HashMap;
import java.util.Map;

public class GenericController {

	private Map<String , Object> sessionMap = new HashMap<String, Object>();

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
