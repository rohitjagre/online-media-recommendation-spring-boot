package com.app.services.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.app.movie.pojos.Movie;
import com.app.movie.pojos.catchers.MovieCatcher;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IMovieService {

    List<Movie> searchMovieWithPageNumber(String searchTerm, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException;

    List<Movie> getRecommendedMovies(String movieId, int pageNumber, RestTemplate restTemplate)
            throws ParseException, JsonParseException, JsonMappingException, IOException;

    MovieCatcher[] convertJsonToMovieCatcherArray(String json)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException;

    Movie getMovieById(String movieId, RestTemplate restTemplate) throws ParseException;

    // parse the api pojos to our required pojo format
    Movie convertToMovie(MovieCatcher movieCatcher) throws ParseException;

    // parse the api pojos to our required pojo format
    List<Movie> convertToMovieList(List<MovieCatcher> movieCatcherList) throws ParseException;

}