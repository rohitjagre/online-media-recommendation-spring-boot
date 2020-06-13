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

import com.app.services.interfaces.ITvShowService;
import com.app.services.interfaces.IUserService;
import com.app.tvshow.pojos.TVShow;
import com.app.utils.Token;
import com.app.utils.UserUtils;

@RestController
@RequestMapping("/user/tvshow")
@CrossOrigin(origins = "*")
public class UserTVShowController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITvShowService tvShowService;

    @GetMapping("/get/favourites")
    public ResponseEntity<?> getFavouriteTvShows(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                return new ResponseEntity<Set<TVShow>>(userService.getLikedTvShows(token.getId()), HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add/favourites/{tvShowId}")
    public ResponseEntity<?> addToFavouriteTvShows(@RequestHeader String Authorization, @PathVariable String tvShowId,
                                                   RestTemplate restTemplate) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                TVShow tvShow = tvShowService.getTvShowById(tvShowId, restTemplate);
                userService.addLikedTvShow(token.getId(), tvShow);
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clear/watchlist")
    public ResponseEntity<?> deleteTvShowWatchList(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                userService.deleteTvShowWatchList(token.getId());
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/watchlist")
    public ResponseEntity<?> getTvShowWatchList(@RequestHeader String Authorization) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                Set<TVShow> tvShowWatchlist = userService.getTvShowWatchList(token.getId());
                System.out.println(tvShowWatchlist);
                return new ResponseEntity<Set<TVShow>>(tvShowWatchlist, HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add/watchlist/{tvShowId}")
    public ResponseEntity<?> addTvShowToWatchList(@RequestHeader String Authorization, @PathVariable String tvShowId,
                                                  RestTemplate restTemplate) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                TVShow show = tvShowService.getTvShowById(tvShowId, restTemplate);
                userService.addTvShowToWatchList(token.getId(), show);
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
