package com.gtn.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Model;

@Service("genericService")
@Transactional
public class GenericServiceImpl implements GenericService{

	@Autowired
	private GenericDao dao;
	
	@Override
	public Model saveEntity(Model entity) throws ApplicationException {
		dao.create(entity);
		return entity;
	}

	@Override
	public Model updateEntity(Model entity) throws ApplicationException {
		dao.update(entity);
		return entity;
	}

	@Override
	public void removeEntity(Model entity) throws ApplicationException {
		dao.delete(entity);
	}

	@Override
	public Model findEntity(Model entity, Serializable pk) throws ApplicationException {
		try{
			Model entityDb = (Model) dao.read(entity.getClass(), pk);
			return entityDb;
		}catch(Exception e){
			throw new ApplicationException("Error finding entity", e);
		}
		
	}
	
	@Override
	public Model read(Class type, Serializable pk) throws ApplicationException{
		return (Model) dao.read(type, pk);
	}
}
