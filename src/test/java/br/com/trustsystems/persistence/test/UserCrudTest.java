package br.com.trustsystems.persistence.test;


import br.com.trustsystems.persistence.dao.ITypedDao;
import br.com.trustsystems.persistence.test.config.SpringAwareCrudTest;
import br.com.trustsystems.persistence.test.dao.UserDao;
import br.com.trustsystems.persistence.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserCrudTest extends SpringAwareCrudTest<Long, User>
{
    @Autowired
    private UserDao dao;

    @Override
    protected User createValidEntity()
    {
        User user = new User();
        user.setName("John Rambo");
        return user;
    }

    @Override
    protected void modifyEntity(User which)
    {
        which.setEmail("m16@rambo.com");
    }

    @Override
    protected ITypedDao<Long, User> getDao()
    {
        return dao;
    }

    @Override
    protected void addEntities(List<User> entities)
    {
        entities.add(createValidEntity());
        entities.add(createValidEntity());
        entities.add(createValidEntity());
    }
}
