package com.app.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.interfaces.ITvShowDAO;
import com.app.tvshow.pojos.TVShow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class TvShowDAO implements ITvShowDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void addTVShow(TVShow show) {
        getCurrentSession().saveOrUpdate(show);
    }
}
