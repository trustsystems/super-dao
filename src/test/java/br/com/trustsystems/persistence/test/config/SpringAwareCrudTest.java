package br.com.trustsystems.persistence.test.config;



import br.com.trustsystems.persistence.Persistent;
import br.com.trustsystems.persistence.dao.test.CrudTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath*:META-INF/common-applicationContext.xml")
public abstract class SpringAwareCrudTest<K extends Serializable, A extends Persistent<K>> extends CrudTest<K,A> {

}
