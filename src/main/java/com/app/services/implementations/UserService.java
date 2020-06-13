package com.app.services.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.interfaces.IUserDAO;
import com.app.movie.pojos.Movie;
import com.app.services.interfaces.IUserService;
import com.app.tvshow.pojos.TVShow;
import com.app.user.pojos.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDao;

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public Integer addUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userDao.addUser(user);
	}

	@Override
	@Transactional
	public void addLikedMovie(int userId, Movie movie) {
		userDao.addLikedMovie(userId, movie);
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		User user = userDao.getUser(userId);
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User validateUser(User user) {
		User u = userDao.getUser(user.getEmail());
		if (u != null && passwordEncoder.matches(user.getPassword(), u.getPassword())) {
			return u;
		}
		return null;
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public void addLikedTvShow(int userId, TVShow tvShow) {
		userDao.addLikedTvShow(userId, tvShow);
	}

	@Override
	@Transactional
	public Set<Movie> getLikedMovies(int userId) {
		return userDao.getLikedMovies(userId);
	}

	@Override
	@Transactional
	public Set<TVShow> getLikedTvShows(int userId) {
		return userDao.getLikedTvShows(userId);
	}

	@Override
	@Transactional
	public void addMovieToWatchList(int userId, Movie movie) {
		if (userDao.getUser(userId) != null) {
			userDao.addMovieToWatchList(userId, movie);
		}
	}

	@Override
	@Transactional
	public void addTvShowToWatchList(int userId, TVShow show) {
		if (userDao.getUser(userId) != null) {
			userDao.addTvShowToWatchList(userId, show);
		}
	}

	@Override
	@Transactional
	public Set<Movie> getMovieWatchList(int userId) {
		if (userDao.getUser(userId) != null) {
			return userDao.getMovieWatchList(userId);
		}
		return null; // throw exception here
	}

	@Override
	@Transactional
	public Set<TVShow> getTvShowWatchList(int userId) {
		if (userDao.getUser(userId) != null) {
			return userDao.getTvShowWatchList(userId);
		}
		return null; // throw exception here
	}

	@Override
	@Transactional
	public void deleteMovieWatchList(int userId) {
		if (userDao.getUser(userId) != null) {
			userDao.deleteMovieWatchList(userId);
		}
	}

	@Override
	@Transactional
	public void deleteTvShowWatchList(int userId) {
		if (userDao.getUser(userId) != null) {
			userDao.deleteTvShowWatchList(userId);
		}
	}

	@Override
	@Transactional
	public void addSearch(int userId, String searchTerm) {
		if (userDao.getUser(userId) != null) {
			userDao.addSearch(userId, searchTerm);
		}
	}

	@Override
	@Transactional
	public Set<String> getSearchHistory(int userId) {
		if (userDao.getUser(userId) != null) {
			return userDao.getSearchHistory(userId);
		}
		return null; // throw exception here
	}

	@Override
	@Transactional
	public void deleteSearchHistory(int userId) {
		if (userDao.getUser(userId) != null) {
			userDao.deleteSearchHistory(userId);
		}
	}

}
