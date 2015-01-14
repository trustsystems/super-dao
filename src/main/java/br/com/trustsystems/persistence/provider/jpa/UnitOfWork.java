package br.com.trustsystems.persistence.provider.jpa;

import br.com.trustsystems.persistence.dao.IUnitOfWork;

import javax.persistence.EntityManager;

/**
 * An implementation of {@link br.com.trustsystems.persistence.dao.IUnitOfWork} interface for JPA 2.0. It offers access to an instance of entity
 * manager using the instance variable em.
 * 
 */
public abstract class UnitOfWork implements IUnitOfWork 
{
	protected EntityManager em;

	@Override
	public abstract void execute() throws Exception;

	public void setEm(EntityManager em)
	{
		this.em = em;
	}
}
