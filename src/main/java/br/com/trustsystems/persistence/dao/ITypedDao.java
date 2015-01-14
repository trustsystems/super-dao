package br.com.trustsystems.persistence.dao;

import br.com.trustsystems.persistence.Persistent;
import br.com.trustsystems.persistence.dao.query.Query;
import br.com.trustsystems.persistence.dao.query.Query.TypedQuery;
import br.com.trustsystems.persistence.dao.query.Options.AccessPlan;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * A typed version of the {@link IPersistenceProvider} interface. It provides type-safe method for domain object persistence.
 * 
 * @param <KEY>
 *            The type of primary key used for the {@link br.com.trustsystems.persistence.Persistent}
 * @param <E>
 *            The type of domain object the instance of {@link ITypedDao} works on
 */

public interface ITypedDao<KEY extends Serializable, E extends Persistent<KEY>> 
{

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	boolean delete(E entity);

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 * @return
	 */
	boolean deleteAll(Collection<E> entities);

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	List<E> findAll();

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	E findById(KEY id);
	
	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	E findById(KEY id, AccessPlan options);


	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	E persist(E entity);

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	List<E> persistAll(List<E> entities);

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	void runTransactional(IUnitOfWork t);

	/**
	 * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
	 * 
	 */
	long countAll();

    /**
     * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
     *
     */
	E find(TypedQuery query);

    /**
     * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
     *
     */
	<R> List<R> query(Class<R> queryResultType, Query queryToRun);

    /**
     * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
     *
     */
    List<E> findAll(TypedQuery query);


    <KEY extends Serializable, E extends Persistent<KEY>> ITypedDao forEntity(Class<KEY> keyType, Class<E> entityType);

    boolean isSameVersion(E one, E other);

    /**
     * See corresponding method in {@link br.com.trustsystems.persistence.dao.IPersistenceProvider}.
     *
     */
    void flush();
}
