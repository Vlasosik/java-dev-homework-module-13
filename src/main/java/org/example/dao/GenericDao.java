package org.example.dao;

import org.example.hibernate_util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDao<T> {
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    public T read(Object id, Class<T> entityClass) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }

    }

    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }
    }

    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
    }
}
