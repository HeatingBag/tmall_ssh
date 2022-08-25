/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午4:18:21
*/
package com.how2java.tmall.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.how2java.tmall.dao.impl.DAOImpl;



/**
 * 设计一个新的类，叫做ServiceDelegateDAO，在其中注入dao，然后让对dao的每一个方法进行委派。
 * 什么是委派:
 * 当调用ServiceDelegateDAO对象的delete(Object entity) 的时候，其实就是委派给的dao的delete(Object entity) 方法。
 * 但是从调用者的角度来看，调用者只知道ServiceDelegateDAO这个类的delete(Object entity)方法，而意识不到dao的存在。
 * 委派的好处:
 * 通过委派模式把dao隐藏起来，调用的时候意识不到dao的存在，代码看起来更加简洁
 * 过继承委派模式的ServiceDelegateDAO，继承了update和delete，直接使用即可，BaseServiceImpl 类无须再额外提供这两个方法
 * */

public class ServiceDelegateDAO {
	
	@Autowired
	DAOImpl dao;

	/**
	 * 
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#afterPropertiesSet()
	 */
	public void afterPropertiesSet() {
		dao.afterPropertiesSet();
	}

	/**
	 * @param queryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#bulkUpdate(java.lang.String, java.lang.Object[])
	 */
	public int bulkUpdate(String queryString, Object... values) throws DataAccessException {
		return dao.bulkUpdate(queryString, values);
	}

	/**
	 * @param queryString
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#bulkUpdate(java.lang.String, java.lang.Object)
	 */
	public int bulkUpdate(String queryString, Object value) throws DataAccessException {
		return dao.bulkUpdate(queryString, value);
	}

	/**
	 * @param queryString
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#bulkUpdate(java.lang.String)
	 */
	public int bulkUpdate(String queryString) throws DataAccessException {
		return dao.bulkUpdate(queryString);
	}

	/**
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#clear()
	 */
	public void clear() throws DataAccessException {
		dao.clear();
	}

	/**
	 * @param arg0
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#closeIterator(java.util.Iterator)
	 */
	public void closeIterator(Iterator arg0) throws DataAccessException {
		dao.closeIterator(arg0);
	}

	/**
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#contains(java.lang.Object)
	 */
	public boolean contains(Object entity) throws DataAccessException {
		return dao.contains(entity);
	}

	/**
	 * @param ex
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#convertHibernateAccessException(org.hibernate.HibernateException)
	 */
	public DataAccessException convertHibernateAccessException(HibernateException ex) {
		return dao.convertHibernateAccessException(ex);
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#delete(java.lang.Object, org.hibernate.LockMode)
	 */
	public void delete(Object entity, LockMode lockMode) throws DataAccessException {
		dao.delete(entity, lockMode);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#delete(java.lang.Object)
	 */
	public void delete(Object entity) throws DataAccessException {
		dao.delete(entity);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#delete(java.lang.String, java.lang.Object, org.hibernate.LockMode)
	 */
	public void delete(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		dao.delete(entityName, entity, lockMode);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#delete(java.lang.String, java.lang.Object)
	 */
	public void delete(String entityName, Object entity) throws DataAccessException {
		dao.delete(entityName, entity);
	}

	/**
	 * @param entities
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#deleteAll(java.util.Collection)
	 */
	public void deleteAll(Collection entities) throws DataAccessException {
		dao.deleteAll(entities);
	}

	/**
	 * @param filterName
	 * @return
	 * @throws IllegalStateException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#enableFilter(java.lang.String)
	 */
	public Filter enableFilter(String filterName) throws IllegalStateException {
		return dao.enableFilter(filterName);
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#evict(java.lang.Object)
	 */
	public void evict(Object entity) throws DataAccessException {
		dao.evict(entity);
	}

	/**
	 * @param <T>
	 * @param action
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#execute(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public <T> T execute(HibernateCallback<T> action) throws DataAccessException {
		return dao.execute(action);
	}

	/**
	 * @param action
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#executeFind(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public List executeFind(HibernateCallback<?> action) throws DataAccessException {
		return dao.executeFind(action);
	}

	/**
	 * @param <T>
	 * @param action
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#executeWithNativeSession(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public <T> T executeWithNativeSession(HibernateCallback<T> action) {
		return dao.executeWithNativeSession(action);
	}

	/**
	 * @param <T>
	 * @param action
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#executeWithNewSession(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public <T> T executeWithNewSession(HibernateCallback<T> action) {
		return dao.executeWithNewSession(action);
	}

	/**
	 * @param queryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#find(java.lang.String, java.lang.Object[])
	 */
	public List find(String queryString, Object... values) throws DataAccessException {
		return dao.find(queryString, values);
	}

	/**
	 * @param queryString
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#find(java.lang.String, java.lang.Object)
	 */
	public List find(String queryString, Object value) throws DataAccessException {
		return dao.find(queryString, value);
	}

	/**
	 * @param queryString
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#find(java.lang.String)
	 */
	public List find(String queryString) throws DataAccessException {
		return dao.find(queryString);
	}

	/**
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) throws DataAccessException {
		return dao.findByCriteria(criteria, firstResult, maxResults);
	}

	/**
	 * @param criteria
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public List findByCriteria(DetachedCriteria criteria) throws DataAccessException {
		return dao.findByCriteria(criteria);
	}

	/**
	 * @param exampleEntity
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByExample(java.lang.Object, int, int)
	 */
	public List findByExample(Object exampleEntity, int firstResult, int maxResults) throws DataAccessException {
		return dao.findByExample(exampleEntity, firstResult, maxResults);
	}

	/**
	 * @param exampleEntity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByExample(java.lang.Object)
	 */
	public List findByExample(Object exampleEntity) throws DataAccessException {
		return dao.findByExample(exampleEntity);
	}

	/**
	 * @param entityName
	 * @param exampleEntity
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByExample(java.lang.String, java.lang.Object, int, int)
	 */
	public List findByExample(String entityName, Object exampleEntity, int firstResult, int maxResults)
			throws DataAccessException {
		return dao.findByExample(entityName, exampleEntity, firstResult, maxResults);
	}

	/**
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByExample(java.lang.String, java.lang.Object)
	 */
	public List findByExample(String entityName, Object exampleEntity) throws DataAccessException {
		return dao.findByExample(entityName, exampleEntity);
	}

	/**
	 * @param queryString
	 * @param paramName
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedParam(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public List findByNamedParam(String queryString, String paramName, Object value) throws DataAccessException {
		return dao.findByNamedParam(queryString, paramName, value);
	}

	/**
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedParam(String queryString, String[] paramNames, Object[] values) throws DataAccessException {
		return dao.findByNamedParam(queryString, paramNames, values);
	}

	/**
	 * @param queryName
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	public List findByNamedQuery(String queryName, Object... values) throws DataAccessException {
		return dao.findByNamedQuery(queryName, values);
	}

	/**
	 * @param queryName
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQuery(java.lang.String, java.lang.Object)
	 */
	public List findByNamedQuery(String queryName, Object value) throws DataAccessException {
		return dao.findByNamedQuery(queryName, value);
	}

	/**
	 * @param queryName
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQuery(java.lang.String)
	 */
	public List findByNamedQuery(String queryName) throws DataAccessException {
		return dao.findByNamedQuery(queryName);
	}

	/**
	 * @param queryName
	 * @param paramName
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQueryAndNamedParam(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException {
		return dao.findByNamedQueryAndNamedParam(queryName, paramName, value);
	}

	/**
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQueryAndNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
			throws DataAccessException {
		return dao.findByNamedQueryAndNamedParam(queryName, paramNames, values);
	}

	/**
	 * @param queryName
	 * @param valueBean
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedQueryAndValueBean(java.lang.String, java.lang.Object)
	 */
	public List findByNamedQueryAndValueBean(String queryName, Object valueBean) throws DataAccessException {
		return dao.findByNamedQueryAndValueBean(queryName, valueBean);
	}

	/**
	 * @param queryString
	 * @param valueBean
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#findByValueBean(java.lang.String, java.lang.Object)
	 */
	public List findByValueBean(String queryString, Object valueBean) throws DataAccessException {
		return dao.findByValueBean(queryString, valueBean);
	}

	/**
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#flush()
	 */
	public void flush() throws DataAccessException {
		dao.flush();
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#get(java.lang.Class, java.io.Serializable, org.hibernate.LockMode)
	 */
	public <T> T get(Class<T> entityClass, Serializable id, LockMode lockMode) throws DataAccessException {
		return dao.get(entityClass, id, lockMode);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#get(java.lang.Class, java.io.Serializable)
	 */
	public <T> T get(Class<T> entityClass, Serializable id) throws DataAccessException {
		return dao.get(entityClass, id);
	}

	/**
	 * @param entityName
	 * @param id
	 * @param lockMode
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#get(java.lang.String, java.io.Serializable, org.hibernate.LockMode)
	 */
	public Object get(String entityName, Serializable id, LockMode lockMode) throws DataAccessException {
		return dao.get(entityName, id, lockMode);
	}

	/**
	 * @param entityName
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#get(java.lang.String, java.io.Serializable)
	 */
	public Object get(String entityName, Serializable id) throws DataAccessException {
		return dao.get(entityName, id);
	}

	/**
	 * @return
	 * @throws IllegalStateException
	 * @throws BeansException
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#getEntityInterceptor()
	 */
	public Interceptor getEntityInterceptor() throws IllegalStateException, BeansException {
		return dao.getEntityInterceptor();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#getFetchSize()
	 */
	public int getFetchSize() {
		return dao.getFetchSize();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#getFilterNames()
	 */
	public String[] getFilterNames() {
		return dao.getFilterNames();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#getFlushMode()
	 */
	public int getFlushMode() {
		return dao.getFlushMode();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#getJdbcExceptionTranslator()
	 */
	public SQLExceptionTranslator getJdbcExceptionTranslator() {
		return dao.getJdbcExceptionTranslator();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#getMaxResults()
	 */
	public int getMaxResults() {
		return dao.getMaxResults();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#getQueryCacheRegion()
	 */
	public String getQueryCacheRegion() {
		return dao.getQueryCacheRegion();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#getSessionFactory()
	 */
	public SessionFactory getSessionFactory() {
		return dao.getSessionFactory();
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return dao.hashCode();
	}

	/**
	 * @param arg0
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#initialize(java.lang.Object)
	 */
	public void initialize(Object arg0) throws DataAccessException {
		dao.initialize(arg0);
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#isAllowCreate()
	 */
	public boolean isAllowCreate() {
		return dao.isAllowCreate();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#isAlwaysUseNewSession()
	 */
	public boolean isAlwaysUseNewSession() {
		return dao.isAlwaysUseNewSession();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#isCacheQueries()
	 */
	public boolean isCacheQueries() {
		return dao.isCacheQueries();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#isCheckWriteOperations()
	 */
	public boolean isCheckWriteOperations() {
		return dao.isCheckWriteOperations();
	}

	/**
	 * @return
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#isExposeNativeSession()
	 */
	public boolean isExposeNativeSession() {
		return dao.isExposeNativeSession();
	}

	/**
	 * @param queryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#iterate(java.lang.String, java.lang.Object[])
	 */
	public Iterator iterate(String queryString, Object... values) throws DataAccessException {
		return dao.iterate(queryString, values);
	}

	/**
	 * @param queryString
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#iterate(java.lang.String, java.lang.Object)
	 */
	public Iterator iterate(String queryString, Object value) throws DataAccessException {
		return dao.iterate(queryString, value);
	}

	/**
	 * @param queryString
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#iterate(java.lang.String)
	 */
	public Iterator iterate(String queryString) throws DataAccessException {
		return dao.iterate(queryString);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#load(java.lang.Class, java.io.Serializable, org.hibernate.LockMode)
	 */
	public <T> T load(Class<T> entityClass, Serializable id, LockMode lockMode) throws DataAccessException {
		return dao.load(entityClass, id, lockMode);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#load(java.lang.Class, java.io.Serializable)
	 */
	public <T> T load(Class<T> entityClass, Serializable id) throws DataAccessException {
		return dao.load(entityClass, id);
	}

	/**
	 * @param entity
	 * @param id
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#load(java.lang.Object, java.io.Serializable)
	 */
	public void load(Object entity, Serializable id) throws DataAccessException {
		dao.load(entity, id);
	}

	/**
	 * @param entityName
	 * @param id
	 * @param lockMode
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#load(java.lang.String, java.io.Serializable, org.hibernate.LockMode)
	 */
	public Object load(String entityName, Serializable id, LockMode lockMode) throws DataAccessException {
		return dao.load(entityName, id, lockMode);
	}

	/**
	 * @param entityName
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#load(java.lang.String, java.io.Serializable)
	 */
	public Object load(String entityName, Serializable id) throws DataAccessException {
		return dao.load(entityName, id);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#loadAll(java.lang.Class)
	 */
	public <T> List<T> loadAll(Class<T> entityClass) throws DataAccessException {
		return dao.loadAll(entityClass);
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#lock(java.lang.Object, org.hibernate.LockMode)
	 */
	public void lock(Object entity, LockMode lockMode) throws DataAccessException {
		dao.lock(entity, lockMode);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#lock(java.lang.String, java.lang.Object, org.hibernate.LockMode)
	 */
	public void lock(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		dao.lock(entityName, entity, lockMode);
	}

	/**
	 * @param <T>
	 * @param entityName
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#merge(java.lang.String, java.lang.Object)
	 */
	public <T> T merge(String entityName, T entity) throws DataAccessException {
		return dao.merge(entityName, entity);
	}

	/**
	 * @param <T>
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#merge(java.lang.Object)
	 */
	public <T> T merge(T entity) throws DataAccessException {
		return dao.merge(entity);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#persist(java.lang.Object)
	 */
	public void persist(Object entity) throws DataAccessException {
		dao.persist(entity);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#persist(java.lang.String, java.lang.Object)
	 */
	public void persist(String entityName, Object entity) throws DataAccessException {
		dao.persist(entityName, entity);
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#refresh(java.lang.Object, org.hibernate.LockMode)
	 */
	public void refresh(Object entity, LockMode lockMode) throws DataAccessException {
		dao.refresh(entity, lockMode);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#refresh(java.lang.Object)
	 */
	public void refresh(Object entity) throws DataAccessException {
		dao.refresh(entity);
	}

	/**
	 * @param entity
	 * @param replicationMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#replicate(java.lang.Object, org.hibernate.ReplicationMode)
	 */
	public void replicate(Object entity, ReplicationMode replicationMode) throws DataAccessException {
		dao.replicate(entity, replicationMode);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @param replicationMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#replicate(java.lang.String, java.lang.Object, org.hibernate.ReplicationMode)
	 */
	public void replicate(String entityName, Object entity, ReplicationMode replicationMode)
			throws DataAccessException {
		dao.replicate(entityName, entity, replicationMode);
	}

	/**
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#save(java.lang.Object)
	 */
	public Serializable save(Object entity) throws DataAccessException {
		return dao.save(entity);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#save(java.lang.String, java.lang.Object)
	 */
	public Serializable save(String entityName, Object entity) throws DataAccessException {
		return dao.save(entityName, entity);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(Object entity) throws DataAccessException {
		dao.saveOrUpdate(entity);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#saveOrUpdate(java.lang.String, java.lang.Object)
	 */
	public void saveOrUpdate(String entityName, Object entity) throws DataAccessException {
		dao.saveOrUpdate(entityName, entity);
	}

	/**
	 * @param entities
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#saveOrUpdateAll(java.util.Collection)
	 */
	public void saveOrUpdateAll(Collection entities) throws DataAccessException {
		dao.saveOrUpdateAll(entities);
	}

	/**
	 * @param allowCreate
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setAllowCreate(boolean)
	 */
	public void setAllowCreate(boolean allowCreate) {
		dao.setAllowCreate(allowCreate);
	}

	/**
	 * @param alwaysUseNewSession
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setAlwaysUseNewSession(boolean)
	 */
	public void setAlwaysUseNewSession(boolean alwaysUseNewSession) {
		dao.setAlwaysUseNewSession(alwaysUseNewSession);
	}

	/**
	 * @param beanFactory
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	public void setBeanFactory(BeanFactory beanFactory) {
		dao.setBeanFactory(beanFactory);
	}

	/**
	 * @param cacheQueries
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setCacheQueries(boolean)
	 */
	public void setCacheQueries(boolean cacheQueries) {
		dao.setCacheQueries(cacheQueries);
	}

	/**
	 * @param checkWriteOperations
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setCheckWriteOperations(boolean)
	 */
	public void setCheckWriteOperations(boolean checkWriteOperations) {
		dao.setCheckWriteOperations(checkWriteOperations);
	}

	/**
	 * @param entityInterceptor
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setEntityInterceptor(org.hibernate.Interceptor)
	 */
	public void setEntityInterceptor(Interceptor entityInterceptor) {
		dao.setEntityInterceptor(entityInterceptor);
	}

	/**
	 * @param entityInterceptorBeanName
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setEntityInterceptorBeanName(java.lang.String)
	 */
	public void setEntityInterceptorBeanName(String entityInterceptorBeanName) {
		dao.setEntityInterceptorBeanName(entityInterceptorBeanName);
	}

	/**
	 * @param exposeNativeSession
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setExposeNativeSession(boolean)
	 */
	public void setExposeNativeSession(boolean exposeNativeSession) {
		dao.setExposeNativeSession(exposeNativeSession);
	}

	/**
	 * @param fetchSize
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setFetchSize(int)
	 */
	public void setFetchSize(int fetchSize) {
		dao.setFetchSize(fetchSize);
	}

	/**
	 * @param filter
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setFilterName(java.lang.String)
	 */
	public void setFilterName(String filter) {
		dao.setFilterName(filter);
	}

	/**
	 * @param filterNames
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setFilterNames(java.lang.String[])
	 */
	public void setFilterNames(String[] filterNames) {
		dao.setFilterNames(filterNames);
	}

	/**
	 * @param flushMode
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setFlushMode(int)
	 */
	public void setFlushMode(int flushMode) {
		dao.setFlushMode(flushMode);
	}

	/**
	 * @param constantName
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setFlushModeName(java.lang.String)
	 */
	public void setFlushModeName(String constantName) {
		dao.setFlushModeName(constantName);
	}

	/**
	 * @param jdbcExceptionTranslator
	 * @see org.springframework.orm.hibernate3.HibernateAccessor#setJdbcExceptionTranslator(org.springframework.jdbc.support.SQLExceptionTranslator)
	 */
	public void setJdbcExceptionTranslator(SQLExceptionTranslator jdbcExceptionTranslator) {
		dao.setJdbcExceptionTranslator(jdbcExceptionTranslator);
	}

	/**
	 * @param maxResults
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setMaxResults(int)
	 */
	public void setMaxResults(int maxResults) {
		dao.setMaxResults(maxResults);
	}

	/**
	 * @param queryCacheRegion
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#setQueryCacheRegion(java.lang.String)
	 */
	public void setQueryCacheRegion(String queryCacheRegion) {
		dao.setQueryCacheRegion(queryCacheRegion);
	}

	/**
	 * @param sessionFactory
	 * @see com.how2java.tmall.dao.impl.DAOImpl#setSessionFactory(org.hibernate.SessionFactory)
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return dao.toString();
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#update(java.lang.Object, org.hibernate.LockMode)
	 */
	public void update(Object entity, LockMode lockMode) throws DataAccessException {
		dao.update(entity, lockMode);
	}

	/**
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#update(java.lang.Object)
	 */
	public void update(Object entity) throws DataAccessException {
		dao.update(entity);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @param lockMode
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#update(java.lang.String, java.lang.Object, org.hibernate.LockMode)
	 */
	public void update(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		dao.update(entityName, entity, lockMode);
	}

	/**
	 * @param entityName
	 * @param entity
	 * @throws DataAccessException
	 * @see org.springframework.orm.hibernate3.HibernateTemplate#update(java.lang.String, java.lang.Object)
	 */
	public void update(String entityName, Object entity) throws DataAccessException {
		dao.update(entityName, entity);
	}

}
