package org.aptech.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Stateless(name = "EmployeeBeanEJB")
@LocalBean

public class EntityBean<T extends Serializable> implements EntityService<T> {

    private Class<T> type;
    private final EntityManager entityManager;

    public EntityBean() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("manytomanyPersistenceUnit");
        entityManager = managerFactory.createEntityManager();

    }

    @Override
    public List<T> getAllEntity() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();

    }

    @Override
    public T getEntityById(long id) {

        return this.entityManager.find(type, id);

    }

    @Override
    public T getEntityByName(String name) {
        return this.entityManager.find(type, name);
    }

    @Override
    public boolean addEntity(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }


    @Override
    public boolean updateEntity(T entity) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean deleteEntity(long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(getEntityById(id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }


    @Override
    public void setType(Class<T> t) {
        type = t;
    }






}
