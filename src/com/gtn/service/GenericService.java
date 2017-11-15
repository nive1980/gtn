package com.gtn.service;

import java.io.Serializable;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Model;

public interface GenericService {

	public Model saveEntity(Model entity) throws ApplicationException;
	public Model updateEntity(Model entity) throws ApplicationException;
	public void removeEntity(Model entity) throws ApplicationException;
	public Model findEntity(Model entity, Serializable pk) throws ApplicationException;
	public Model read(Class type, Serializable pk) throws ApplicationException;

}
	