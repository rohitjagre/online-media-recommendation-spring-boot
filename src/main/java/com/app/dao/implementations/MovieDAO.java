package com.app.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.interfaces.IMovieDAO;
import com.app.movie.pojos.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class MovieDAO implements IMovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    @Transactional
    public boolean storeMovie(Movie movie) {
        getCurrentSession().saveOrUpdate(movie);
        return true;
    }

}
