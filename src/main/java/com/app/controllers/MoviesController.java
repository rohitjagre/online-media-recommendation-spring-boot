package com.app.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.movie.pojos.Movie;
import com.app.services.interfaces.IMovieService;
import com.app.services.interfaces.IUserService;
import com.app.utils.Token;
import com.app.utils.UserUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movie")
public class MoviesController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovie(@RequestHeader String Authorization, @PathVariable String movieId,
                                      RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                Movie movie = movieService.getMovieById(movieId, template);
                return new ResponseEntity<Movie>(movie, HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recommendation/{movieId}/{pageNumber}")
    public ResponseEntity<?> getMovies(@RequestHeader String Authorization, @PathVariable String movieId,
                                       @PathVariable int pageNumber, RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                List<Movie> moviesList = movieService.getRecommendedMovies(movieId, pageNumber, template);
                return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{searchTerm}/{pageNumber}")
    public ResponseEntity<?> getSearchMovies(@RequestHeader String Authorization, @PathVariable String searchTerm,
                                             @PathVariable int pageNumber, RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                userService.addSearch(token.getId(), searchTerm);
                List<Movie> moviesList = movieService.searchMovieWithPageNumber(searchTerm, pageNumber, template);
                return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
