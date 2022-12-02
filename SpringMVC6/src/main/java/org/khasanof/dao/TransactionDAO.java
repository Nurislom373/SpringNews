package org.khasanof.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.khasanof.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDAO {

    private final SessionFactory sessionFactory;

    public TransactionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(TransactionEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public List<TransactionEntity> list() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TransactionEntity> query = builder.createQuery(TransactionEntity.class);
        Root<TransactionEntity> from = query.from(TransactionEntity.class);
        query.select(from);
        Query<TransactionEntity> sessionQuery = session.createQuery(query);
        return sessionQuery.list();
    }
}
