package com.app.services.implementations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.movie.pojos.Movie;
import com.app.movie.pojos.catchers.MovieGenres;
import com.app.movie.pojos.catchers.MovieCatcher;
import com.app.services.interfaces.IMovieService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieService implements IMovieService {
    private static final String tokenId = "abc";
    private static final String baseUrl = "https://api.themoviedb.org/3/movie/";
    private static final String searchMovieUrl = "https://api.themoviedb.org/3/search/movie";

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Movie> searchMovieWithPageNumber(String searchTerm, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException {
        System.out.println("Movie search request");
        String url = searchMovieUrl
                + "?api_key={tokenId}&language=en-US&query={searchTerm}&page={pageNumber}&include_adult=false";
        String json = template.getForObject(url, String.class, tokenId, searchTerm, pageNumber);
        MovieCatcher[] moviesCatcher = convertJsonToMovieCatcherArray(json);
        List<Movie> moviesList = convertToMovieList(Arrays.asList(moviesCatcher));
        return moviesList;
    }

    @Override
    public List<Movie> getRecommendedMovies(String movieId, int pageNumber, RestTemplate restTemplate)
            throws ParseException, JsonParseException, JsonMappingException, IOException {
        String url = baseUrl + "{movieId}/recommendations?api_key={tokenId}&language=en-US&page={pageNumber}";
        String json = restTemplate.getForObject(url, String.class, movieId, tokenId, pageNumber);
        System.out.println(json);
        MovieCatcher[] moviesCatcher = convertJsonToMovieCatcherArray(json);
        return convertToMovieList(Arrays.asList(moviesCatcher));
    }

    @Override
    public MovieCatcher[] convertJsonToMovieCatcherArray(String json)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode resultNode = mapper.readTree(json).get("results");
        MovieCatcher[] moviesCatcher = mapper.readValue(resultNode.toString(), MovieCatcher[].class);
        return moviesCatcher;
    }

    @Override
    public Movie getMovieById(String movieId, RestTemplate restTemplate) throws ParseException {
        String url = baseUrl + "{movieId}?api_key={tokenId}&language=en-US";
        MovieCatcher movieCatcher = restTemplate.getForObject(url, MovieCatcher.class, movieId, tokenId);
        Movie movie = convertToMovie(movieCatcher);
        return movie;
    }

    // parse the api pojos to our required pojo format
    @Override
    public Movie convertToMovie(MovieCatcher movieCatcher) throws ParseException {

        Movie movie = new Movie();
        for (MovieGenres g : movieCatcher.getGenres()) {
            movie.addGenreToList(g);
        }
        // movie.setGenres(Arrays.asList(movieCatcher.getGenres()));
        movie.setHomepage(movieCatcher.getHomepage());
        movie.setId(Integer.parseInt(movieCatcher.getId()));
        movie.setImdbId(movieCatcher.getImdb_id());
        movie.setOverview(movieCatcher.getOverview());
        movie.setPopularity(Double.parseDouble(movieCatcher.getPopularity()));
        movie.setPosterPath(movieCatcher.getPoster_path());
        movie.setReleaseDate(sdf.parse(movieCatcher.getRelease_date()));
        movie.setRuntime(Integer.parseInt(movieCatcher.getRuntime()));
        movie.setTitle(movieCatcher.getTitle());
        movie.setVoteCount(Integer.parseInt(movieCatcher.getVote_count()));

        return movie;
    }

    // parse the api pojos to our required pojo format
    @Override
    public List<Movie> convertToMovieList(List<MovieCatcher> movieCatcherList) throws ParseException {
        List<Movie> movies = new ArrayList<>();
        for (MovieCatcher movieCatcher : movieCatcherList) {
            System.out.println(movieCatcher);
            Movie movie = new Movie();
            // movie.setGenreIds(Arrays.asList(movieCatcher.getGenre_ids()));
            movie.setId(Integer.parseInt(movieCatcher.getId()));
            // movie.setOverview(movieCatcher.getOverview());
            movie.setPosterPath(movieCatcher.getPoster_path());
            // movie.setReleaseDate(sdf.parse(movieCatcher.getRelease_date()));
            movie.setTitle(movieCatcher.getTitle());
            movie.setVoteCount(Integer.parseInt(movieCatcher.getVote_count()));
            movies.add(movie);
        }
        return movies;
    }
}
