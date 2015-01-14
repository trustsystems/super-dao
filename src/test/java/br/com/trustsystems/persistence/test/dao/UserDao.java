package br.com.trustsystems.persistence.test.dao;


import br.com.trustsystems.persistence.dao.IPersistenceProvider;
import br.com.trustsystems.persistence.dao.TypedDao;
import br.com.trustsystems.persistence.test.domain.User;
import br.com.trustsystems.persistence.test.provider.DatabasePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserDao extends TypedDao<Long, User>
{

    @Autowired
    private DatabasePersistenceProvider dbPersistenceProvider;

    public UserDao()
    {
        super(Long.class, User.class);
    }

    @Override
    protected IPersistenceProvider getPersistenceProvider()
    {
        return dbPersistenceProvider;
    }
}
