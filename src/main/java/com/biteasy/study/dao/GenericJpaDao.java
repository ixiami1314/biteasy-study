package com.biteasy.study.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 通用泛型DAO抽象类
 * 实现IDAO
 *
 * JPA 实体管理器标签从这里标上
 * @param <T>
 */
public abstract class GenericJpaDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericJpaDao.class);

    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private Class<T> persistentClass;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericJpaDao() {
        persistentClass = getGenericType();
    }

    public GenericJpaDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public T save(T entity) throws DaoException {
        if(entity == null) {
            LOG.error ("HUHOO CORE DB " + entity.getClass().getName() + " is null");
            throw new DaoException ("entity is null");
        }

        try {
            getEntityManager().persist(entity);
        } catch(Exception e) {
            LOG.error ("HUHOO CORE DB persist error");
            throw new DaoException("persist error");
        }

        LOG.info ("HUHOO CORE DB persist " + entity.getClass().getName() + " success!");
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) throws DaoException {

        if(entity == null) {
            LOG.error ("HUHOO CORE DB entity is null");
            throw new DaoException("entity is null");
        }

        try {
            T mergedEntity = getEntityManager().merge(entity);
            LOG.info ("HUHOO CORE DB update " + entity.getClass().getName() + " success!");
            return mergedEntity;
        } catch(Exception e) {
            LOG.error ("HUHOO CORE DB merge is error");
            throw new DaoException("merge is error");
        }
    }

    @Override
    @Transactional
    public void delete(T entity) throws DaoException {
        throw new DaoException ("remove error");
    }

    @Override
    public T findById(Long id) throws DaoException {
        if(id == null) {
            LOG.error("HUHOO CORE DB find ID is null");
            throw new DaoException("find id is null");
        }

        try {
            T entity = getEntityManager().find(persistentClass, id);
            return entity;
        } catch(Exception e) {
            LOG.error ("HUHOO CORE DB find persistent is error");
            throw new DaoException("find persistent is error");
        }
    }

    @Override
    public List<T> findByIds(Collection<Long> ids) throws DaoException {
        if (ids == null || ids.isEmpty()) {
            return Collections.<T>emptyList();
        }

        String sql = "FROM " + persistentClass.getSimpleName() +" where id in (:ids)";
        Query query = entityManager.createQuery (sql);
        query.setParameter ("ids", ids);
        try {
//		List<T> entities = new ArrayList<T>();
//		try {
//			for (Long id : ids) {
//				T entity = getEntityManager().find(persistentClass, id);
//				if(entity == null) {
//					LOG.debug ("HUHOO CORE DB Entity cannot be found", id);
//				}
//				entities.add(entity);
//			}
//			return entities;
            return query.getResultList();
        } catch(Exception e) {
            LOG.error("HUHOO CORE DB find ids is error");
            throw new DaoException("find ids is error");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() throws DaoException {
        try {
            return getEntityManager().createQuery("SELECT x from " + getPersistentClass().getSimpleName() + " x").getResultList();
        } catch(Exception e) {
            LOG.error("HUHOO CORE DB find all is error");
            throw new DaoException("find all is error");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Map<String, Boolean> sortOrders) throws DaoException {
        try {
            StringBuffer jpql = new StringBuffer("SELECT x from " + getPersistentClass().getSimpleName() + " x ");

            if (sortOrders.isEmpty ()) {
                return getEntityManager().createQuery(jpql.toString()).getResultList();
            }

            jpql.append("ORDER BY");
            int index = 0;
            for(Map.Entry<String,Boolean> entry : sortOrders.entrySet()) {
                String sortProp = entry.getKey();
                String sortOrder = entry.getValue() ? ASC : DESC;
                jpql.append(" x.").append(sortProp).append(" ").append(sortOrder);
                if(index < sortOrders.size() - 1) {
                    jpql.append(",");
                }
                index ++;
            }
            LOG.debug("HUHOO CORE DB findAll (sortOrders) ... hql >>> {}", jpql.toString());
            return getEntityManager().createQuery(jpql.toString()).getResultList();
        } catch(Exception e) {
            LOG.error("HUHOO CORE DB find all is error");
            throw new DaoException("find all is error");
        }
    }

    @Override
    public void flush() throws DaoException {
        try {
            getEntityManager().flush();
        } catch(Exception e) {
            LOG.error("HUHOO CORE DB flush entitymanage is error");
            throw new DaoException("flush error");
        }
    }

    @Override
    public T getReference(Class<T> clazz, Long id) throws DaoException {
        try {
            return getEntityManager().getReference(clazz, id);
        } catch(Exception e) {
            LOG.error("HUHOO CORE DB get reference is error");
            throw new DaoException("get reference is error");
        }
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Class<T> getGenericType() {
        Class clazz = getClass();
        ParameterizedType type = null;

        do {
            try {
                type = (ParameterizedType) clazz.getGenericSuperclass();
            } catch (ClassCastException e) {
            }
            clazz = clazz.getSuperclass();
        } while(type == null && clazz != null);

        if(type == null) {
            throw new RuntimeException();
        }

        return (Class<T>) type.getActualTypeArguments()[0];
    }

}