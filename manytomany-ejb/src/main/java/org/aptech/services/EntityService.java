package org.aptech.services;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface EntityService<T extends Serializable>  {
    public List<T> getAllEntity();

    public T getEntityById(long id);

    public T getEntityByName(String name);

    public boolean addEntity(T entity);

    public boolean updateEntity(T entity);

    public boolean deleteEntity(long id);
    public void setType(Class<T> t);





}
