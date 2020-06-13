package com.app.dao.interfaces;

import com.app.movie.pojos.Movie;

public interface IMovieDAO {
    public boolean storeMovie(Movie movie);
}
