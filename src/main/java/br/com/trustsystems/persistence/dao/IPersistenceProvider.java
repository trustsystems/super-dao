package br.com.trustsystems.persistence.dao;

import br.com.trustsystems.persistence.Persistent;
import br.com.trustsystems.persistence.dao.query.Query;
import br.com.trustsystems.persistence.dao.query.Query.TypedQuery;
import br.com.trustsystems.persistence.dao.query.Options.AccessPlan;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * A generic interface to plug-in a specific persistence technology. The interface provides methods for the standard
 * persistence operations (Create,Read,Update,Delete) and some query methods based on a slightly fluent query object construction.
 * 
 * Generic type arguments are used in method signature to avoid binding a persistence provider instance to a concrete type of
 * {@link br.com.trustsystems.persistence.Persistent}. The same persistence provider instance can be shared among multiple
 * instances DAOs.
 * 
 * The intention of a persistence provider implementation is to encapsulate all technology aware code in one reusable and interchangeable artifact.
 * Thus, an implementation is always technology aware and coupled to the used persistence technology.
 * Other artifacts of the persistence layer such as {@link TypedDao}  rely on the {@link IPersistenceProvider} implementation
 * to carry out their persistence operations. A change of underlying persistence technology is possible by simply providing a different
 * implementation of {@link IPersistenceProvider}.
 *
 */
public interface IPersistenceProvider
{
	/**
	 * Deletes a domain object from persistent storage.
	 * How delete operation is cascaded to referenced objects depends on the persistence technology and on the 
	 * configuration of the persistence model (e.g. annotations on the domain object)
	 * 
	 * @param <E>
	 *            any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass
	 *            the class of domain object that will be deleted
	 * @param entity
	 *            the domain object to delete
	 * @return 
	 */
	<E extends Persistent<? extends Serializable>> boolean delete(Class<E> domainClass, E entity);

	
	/**
	 * Deletes a list of domain objects from persistence storage. Conforms to the semantics of the delete method.
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The class of domain object that will be deleted
	 * @param entities The domain object to delete
	 * @return 
	 */
	<E extends Persistent<? extends Serializable>> boolean deleteAll(Class<E> domainClass, Collection<E> entities);


	/**
	 * Finds all domain objects of type domainClass and possibly its subtypes
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The upper bound of the class hierarchy that will be considered in the query
	 * @return A list of all domain objects of type <D> or one of its subtypes
	 */
	<E extends Persistent<? extends Serializable>> List<E> findAll(Class<E> domainClass);

	/**
	 * Find a domain object by its primary key.
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The class of domain object that will looked up by id
	 * @param id The primary key of the domain object
	 * @return the domain object with the given id or null if such an object does not exist
	 */
	<E extends Persistent<? extends Serializable>> E findById(Class<E> domainClass, Serializable id);
	
	/**
	 * Find a domain object by its primary key.
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The class of domain object that will looked up by id
	 * @param id The primary key of the domain object
     * @param options Configuraiton object to specify additional options like lock mode
	 * @return the domain object with the given id or null if such an object does not exist
	 */
	<E extends Persistent<? extends Serializable>> E findById(Class<E> domainClass, Serializable id, AccessPlan options);
	

	/**
	 * The method is used to persist an instance of {@link br.com.trustsystems.persistence.Persistent} to the underlying storage
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The class of domain object that will be persisted
	 * @param entity The domain object to persist
	 * @return The persisted object (after a call to persists, the id is set on the returned persistent object)
	 */
	<E extends Persistent<? extends Serializable>> E persist(Class<E> domainClass, E entity);

	/**
	 * The method is used to persist a list of instances of {@link br.com.trustsystems.persistence.Persistent}. It applies the semantics
	 * of the persist operation of a single domain object
	 * 
	 * @param <E> Any subtype of {@link br.com.trustsystems.persistence.Persistent} welcome
	 * @param domainClass The class of domain object that will be persisted
	 * @param entities The domain object to persist
	 * @return A list containing all successfully persisted objects
	 */
	<E extends Persistent<? extends Serializable>> List<E> persistAll(Class<E> domainClass, List<E> entities);

	/**
	 * Executes the given unit of work within new transaction boundaries. If the unit of work executes without 
	 * exceptions, then changes to persistent object are committed else all changes are rolled back.
	 * 
	 * @param t The unit of work that will be run in a transaction
	 */
	void runInTransaction(IUnitOfWork t);

	
	/**
	 * Counts all existing entities of type Class<E>
	 * @param domainClass The class type of domain object
	 * @return The number of existing entities
	 */
	<E extends Persistent<? extends Serializable>> long count(Class<E> domainClass);

    /**
     * Execute a typed query to retrieve any number of domain objects.
     *
     * @param domainClass Any valid domain class
     * @param query  The query to execute
     * @return
     */
	<E extends Persistent<? extends Serializable>> List<E> findAll(Class<E> domainClass, TypedQuery query);


    /**
     * Find a single domain object using the given query. If multiple objects match, the first one is returned
     * (Note: If the query does not specify ordering criteria, executing the same query might give different results
     * on each execution)
     *
     * @param domainClass Any valid domain class
     * @param query  The query to execute
     * @return
     */
	<E extends Persistent<? extends Serializable>> E find(Class<E> domainClass, TypedQuery query);

    /**
     * Execute a query
     *
     * @param resultType Any type of object that may be returned be the underlying persistence storage, e.g. Long,
     *                   Tuple or any of the valid domain classes
     * @param source   The query definition in a form that is understood by the persistence provider
     * @return
     */
	<E> List<E> runQuery(Class<E> resultType, Query source);

    /**
     * Write all pending changes to the database. A flush does not commit any data. It will just write to
     * the underlying storage technology. In context of traditional RDBMS this may cause checks of referential
     * integrity and possibly the throwing of exceptions.
     */
    void flush();

}
