package br.com.trustsystems.persistence.dao.test.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class DataInitializationTask implements IDataInitializationTask 
{
    protected Logger logger = LoggerFactory.getLogger(getClass());
            
    @Override
    public abstract void setUp();
    
    @Override
    public abstract void tearDown();
    
    protected <T> T getRandomElement(List<T> candidates)
    {
        int idx = (int) System.currentTimeMillis() % candidates.size();
        return candidates.get(idx);
    }
}
