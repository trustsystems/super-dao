package br.com.trustsystems.persistence.test.domain;

import javax.persistence.*;

@Entity
public class User extends AbstractEntity<Long>
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Version
    private long version= -1;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public long getVersion()
    {
        return version;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public void setId(Long aLong)
    {
        this.id = id;
    }
}
