package com.app.dao.interfaces;

import java.util.Set;

import com.app.movie.pojos.Movie;
import com.app.tvshow.pojos.TVShow;
import com.app.user.pojos.User;

public interface IUserDAO {
    Integer addUser(User user);

    User getUser(int userId);

    User getUser(String email);

    void updateUser(User user);

    void deleteUser(User user);

    void addLikedMovie(int userId, Movie movie);

    void addLikedTvShow(int userId, TVShow tvShow);

    Set<Movie> getLikedMovies(int userId);

    Set<TVShow> getLikedTvShows(int userId);

    void addMovieToWatchList(int userId, Movie movie);

    void addTvShowToWatchList(int userId, TVShow movie);

    Set<Movie> getMovieWatchList(int userId);

    Set<TVShow> getTvShowWatchList(int userId);

    void deleteMovieWatchList(int userId);

    void deleteTvShowWatchList(int userId);

    void addSearch(int userId, String searchTerm);

    Set<String> getSearchHistory(int userId);

    void deleteSearchHistory(int userId);
}
