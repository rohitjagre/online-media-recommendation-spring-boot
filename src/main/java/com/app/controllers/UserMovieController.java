package com.app.controllers;

import java.util.Set;

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
@RequestMapping("/user/movie")
@CrossOrigin(origins = "*")
public class UserMovieController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMovieService movieService;

    @GetMapping("/get/favourites")
    public ResponseEntity<?> getFavouriteMovies(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                return new ResponseEntity<Set<Movie>>(userService.getLikedMovies(token.getId()), HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add/favourites/{movieId}")
    public ResponseEntity<?> addToFavouriteMovies(@RequestHeader String Authorization, @PathVariable String movieId,
                                                  RestTemplate restTemplate) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                Movie movie = movieService.getMovieById(movieId, restTemplate);
                userService.addLikedMovie(token.getId(), movie);
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clear/watchlist")
    public ResponseEntity<?> deleteMovieWatchList(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                userService.deleteMovieWatchList(token.getId());
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/watchlist")
    public ResponseEntity<?> getMovieWatchList(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                Set<Movie> movieWatchlist = userService.getMovieWatchList(token.getId());
                System.out.println(movieWatchlist);
                return new ResponseEntity<Set<Movie>>(movieWatchlist, HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add/watchlist/{movieId}")
    public ResponseEntity<?> addMovieToWatchList(@RequestHeader String Authorization, @PathVariable String movieId,
                                                 RestTemplate restTemplate) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                Movie movie = movieService.getMovieById(movieId, restTemplate);
                userService.addMovieToWatchList(token.getId(), movie);
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
