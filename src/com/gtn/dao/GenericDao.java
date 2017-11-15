package com.gtn.dao;

import java.io.Serializable;
import java.util.Collection;

import com.gtn.model.Model;

public interface GenericDao {

	/**
	 * Create a new record for the enity.
	 *
	 * @param entity the entity
	 */
	void create(Model entity);

	/**
	 * Get the entity.
	 *
	 * @param pk The Serializable primary key or the Primary Key class (for composite primary keys)
	 * @param type the type
	 *
	 * @return the Entity object
	 */
	Object read(Class type, Serializable pk);

	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	void update(Model entity);

	/**
	 * Deletes the record.
	 *
	 * @param entity the entity
	 */
	void delete(Model entity);

	/**
	 * Find dynamic query.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the collection
	 */
	public Collection findDynamicQuery(String queryString, Object [] parameters);

	/**
	 * Find dynamic scalar.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the collection
	 */
	public long findDynamicScalar(String queryString, Object [] parameters);

	/**
	 * Find dynamic with table fields.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param classType the Class
	 *
	 * @return the collection
	 */
	public Collection findDynamicQueryWithFields(String queryString, Object [] parameters, Class classType);

	// Added following method by Renjith Sharma for License Seq: No: ENHT on 07-09-2011
	/**
	 * find dynamic query using a native SQL Statement with UPDATE or DELETE
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the int value
	 */
	public Collection findDynamicNativeQuery(String queryString, Object[] parameters);
	
	/**
	 * Execute Native Query and return  the result
	 * @param queryString
	 * @param parameters
	 * @return List
	 */
	public int findDynamicNativeQueryScalar(String queryString, Object[] parameters);

	/**
	 * Execute Native Query with Pagination and return  the result
	 * @param queryString
	 * @param parameters
	 * @return int

	 */
	public Collection findNativeQueryPagination(String queryString, Object [] parameters, int start, int chunkSize);

	/**
	 * Find dynamic with table fields with limited resultset.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param classType the Class
	 * @param start		the index
	 * @param chunkSize  the resultsize
	 * @return the collection
	 * @author Naveen John, 24th November 2010
	 */
	public Collection findDynamicQueryPagination(String queryString, Object [] parameters, int start, int chunkSize);
	public Collection findDynamicQueryWithFieldsPagination(String queryString, Object [] parameters, Class classType, int start, int chunkSize);
	int deleteDynamic(String queryString, Object[] params);
}
