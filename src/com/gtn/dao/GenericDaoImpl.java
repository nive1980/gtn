package com.gtn.dao;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gtn.model.Model;

@Repository
public class GenericDaoImpl implements GenericDao {
	/** The manager. */
	@PersistenceContext
	protected EntityManager manager;

	/**
	 * Create a new record for the enity.
	 *
	 * @param entity The enity which has to be created
	 */
	public void create(Model entity) {
		manager.persist(entity);
	}

	/**
	 * Get the entity.
	 *
	 * @param pk The Serializable primary key or the Primary Key class (for composite primary keys)
	 * @param type the type
	 *
	 * @return the Entity object
	 */
	@SuppressWarnings("unchecked")
	public Object read(Class type, Serializable pk) {

		return manager.find(type, pk);
	}

	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	public void update(Model entity) {
		manager.merge(entity);
	}

	/**
	 * Deletes the record.
	 *
	 * @param entity the entity
	 */
	public void delete(Model entity) {
		manager.remove(manager.merge(entity));
	}

	
	public Collection findDynamicQuery(String queryString, Object [] parameters) {

		Query query = manager.createQuery(queryString);
		query = setQueryParameters(query, parameters);
		return query.getResultList();
	}


	public long findDynamicScalar(String queryString, Object [] parameters) {
		Query query = manager.createQuery(queryString);
		query = setQueryParameters(query, parameters);
		return (long)query.getResultList().iterator().next();
	}

	/**
	 * Sets the query parameters. Ignores null and empty String
	 *
	 * @param query the query
	 * @param parameters the parameters
	 *
	 * @return the query
	 */
	private Query setQueryParameters(Query query, Object [] parameters) {
		for (int i=0; i< parameters.length; i++) {
			if (parameters [i] != null) {
				// Check for empty String
				if (parameters [i] instanceof String) {
					String newName = (String) parameters [i];
					// If empty String, then ignore
					if (!newName.equals(""))
						query.setParameter(i+1, parameters [i]);
				} else
					query.setParameter(i+1, parameters [i]);
			}
		}
		return query;
	}

	
	public Collection findDynamicQueryWithFields(String queryString, Object [] parameters, Class classType) {
		Query query = manager.createQuery(queryString);
		query = setQueryParameters(query, parameters);
		Collection coll = query.getResultList();
		return populateEntity(coll,queryString,classType);
	}
	
	/**
	 * find dynamic query using a native SQL Statement with UPDATE or DELETE
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the int value
	 */
	public Collection findDynamicNativeQuery(String queryString, Object[] parameters){
		Query query = manager.createNativeQuery(queryString);
		query = setQueryParameters(query, parameters);
		return  query.getResultList();
	}
	
	
	/**
	 * Executes Native Query
	 * @param queryString
	 * @param parameters
	 * @return List
	 */
	public int findDynamicNativeQueryScalar(String queryString, Object[] parameters){
		
		Query query = manager.createNativeQuery(queryString);
		query = setQueryParameters(query, parameters);
		return query.executeUpdate();
		
	}
	/**
	 * Executes Native Query with Pagination
	 * @param queryString
	 * @param parameters
	 * @return List
	 */
	public Collection findNativeQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) {

		Query query;
		query = manager.createNativeQuery(queryString);
		query = setQueryParameters(query, parameters);
		query.setFirstResult(start);
		query.setMaxResults(chunkSize);
		return query.getResultList();
	}

	public Collection findDynamicQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) {
		Query query = manager.createQuery(queryString);
		query = setQueryParameters(query, parameters);
		query.setFirstResult(start);
		query.setMaxResults(chunkSize);
		return query.getResultList();
	}


	public Collection findDynamicQueryWithFieldsPagination(String queryString, Object [] parameters, Class classType, int start, int chunkSize) {
		Query query = manager.createQuery(queryString);
		query = setQueryParameters(query, parameters);
		query.setFirstResult(start);
		query.setMaxResults(chunkSize);
		Collection coll = query.getResultList();
		return populateEntity(coll,queryString,classType);
	}
	
@SuppressWarnings("unchecked")
	private Collection populateEntity(Collection coll, String queryString, Class classType ){
		String strForTokenizing = queryString.substring(0,queryString.toUpperCase().indexOf(" FROM "));
		strForTokenizing = strForTokenizing.substring(strForTokenizing.toUpperCase().indexOf("SELECT")+7);
		StringTokenizer fields = new StringTokenizer(strForTokenizing,",");
		ArrayList fieldArray = new ArrayList();
		while(fields.hasMoreTokens()){
			String fieldName = fields.nextToken().toString();
			fieldName = fieldName.substring(fieldName.indexOf(".") +1).trim();
			fieldArray.add(fieldName);
		}
		Iterator it  = coll.iterator();
		Object obj;
		ArrayList list = new ArrayList();
		try {
			while(it.hasNext()){
				obj = classType.newInstance();
				Object[] objectArray = (Object[])it.next();

				for(int i=0;i<objectArray.length;i++){
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldArray.get(i).toString(),classType);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(obj, objectArray[i]);
				}
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
@Override
public int deleteDynamic(String queryString, Object[] params) {
	Query query = manager.createQuery(queryString);
	query = setQueryParameters(query, params);
	return query.executeUpdate();

}




}
