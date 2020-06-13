package com.app.dao.implementations;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.interfaces.IUserDAO;
import com.app.movie.pojos.Movie;
import com.app.tvshow.pojos.TVShow;
import com.app.user.pojos.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	private Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Integer addUser(User user) {
		return (Integer) getCurrentSession().save(user);
	}

	@Override
	public User getUser(int id) {
		return getCurrentSession().get(User.class, id);
	}

	/*
	 * String updateJpql =
	 * "UPDATE User u SET u.name=:name, u.securityQuestion=:securityQuestion, u.securityAnswer=:securityAnswer, "
	 * +
	 * "u.displayName=:displayName, u.dateOfBirth=:dateOfBirth, u.gender=:gender WHERE u.email=:email"
	 * ; getCurrentSession().createQuery(updateJpql,
	 * User.class).setParameter("name", user.getName())
	 * .setParameter("securityQuestion", user.getSecurityQuestion())
	 * .setParameter("securityAnswer", user.getSecurityAnswer())
	 * .setParameter("displayName",
	 * user.getDisplayName()).setParameter("dateOfBirth", user.getDateOfBirth())
	 * .setParameter("gender", user.getGender()).setParameter("email",
	 * user.getEmail()).executeUpdate();
	 */

	@Override
	public void updateUser(User user) {
		Session session = getCurrentSession();
		User u1 = session.get(User.class, user.getId());
		u1.setDateOfBirth(user.getDateOfBirth());
		u1.setDisplayName(user.getDisplayName());
		u1.setGender(user.getGender());
		u1.setName(user.getName());
		u1.setSecurityQuestion(user.getSecurityQuestion());
		u1.setSecurityAnswer(user.getSecurityAnswer());
		session.save(u1);
	}

	@Override
	public void deleteUser(User user) {
		getCurrentSession().delete(user);
	}

	@Override
	public void addLikedMovie(int userId, Movie movie) {
		getCurrentSession().get(User.class, userId).addLikedMovie(movie);
	}

	@Override
	public void addLikedTvShow(int userId, TVShow tvShow) {
		getCurrentSession().get(User.class, userId).addLikedTVShows(tvShow);
	}

	@Override
	public Set<Movie> getLikedMovies(int userId) {
		Set<Movie> movies = getCurrentSession().get(User.class, userId).getLikedMovies();
		System.out.println(movies.size());
		return movies;
	}

	@Override
	public Set<TVShow> getLikedTvShows(int userId) {
		Set<TVShow> shows = getCurrentSession().get(User.class, userId).getLikedShows();
		System.out.println(shows.size());
		return shows;
	}

	@Override
	public User getUser(String email) {
		String jpql = "SELECT a FROM User a where a.email=:email";
		return getCurrentSession().createQuery(jpql, User.class).setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public void addMovieToWatchList(int userId, Movie movie) {
		getCurrentSession().get(User.class, userId).addMovieToWatchList(movie);
	}

	@Override
	public void addTvShowToWatchList(int userId, TVShow show) {
		getCurrentSession().get(User.class, userId).addTvShowToWatchList(show);
	}

	@Override
	public Set<Movie> getMovieWatchList(int userId) {
		Set<Movie> movies = getCurrentSession().get(User.class, userId).getMovieWatchList();
		System.out.println(movies.size());
		return movies;
	}

	@Override
	public Set<TVShow> getTvShowWatchList(int userId) {
		Set<TVShow> shows = getCurrentSession().get(User.class, userId).getTvShowWatchList();
		System.out.println(shows.size());
		return shows;
	}

	@Override
	public void addSearch(int userId, String searchTerm) {
		getCurrentSession().get(User.class, userId).addSearchItem(searchTerm);
	}

	@Override
	public Set<String> getSearchHistory(int userId) {
		Set<String> searchHistory = getCurrentSession().get(User.class, userId).getSearches();
		System.out.println(searchHistory.size());
		return searchHistory;
	}

	@Override
	public void deleteSearchHistory(int userId) {
		getCurrentSession().get(User.class, userId).getSearches().clear();
	}

	@Override
	public void deleteMovieWatchList(int userId) {
		getCurrentSession().get(User.class, userId).getMovieWatchList().clear();
	}

	@Override
	public void deleteTvShowWatchList(int userId) {
		getCurrentSession().get(User.class, userId).getTvShowWatchList().clear();
	}

}
