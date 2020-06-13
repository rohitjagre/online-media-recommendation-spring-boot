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

import com.app.services.interfaces.ITvShowService;
import com.app.services.interfaces.IUserService;
import com.app.tvshow.pojos.TVShow;
import com.app.utils.Token;
import com.app.utils.UserUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tvshow")
public class TvShowController {

    @Autowired
    private ITvShowService tvShowService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{tvShowId}")
    public ResponseEntity<?> getTVShow(@RequestHeader String Authorization, @PathVariable String tvShowId,
                                       RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                TVShow show = tvShowService.getTvShowById(tvShowId, template);
                return new ResponseEntity<TVShow>(show, HttpStatus.OK);
            }
            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recommendation/{tvShowId}/{pageNumber}")
    public ResponseEntity<?> getTVShowRecommendation(@RequestHeader String Authorization, @PathVariable String tvShowId,
                                                     @PathVariable int pageNumber, RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                List<TVShow> tvShows = tvShowService.getTvShowRecommendationById(tvShowId, pageNumber, template);
                return new ResponseEntity<List<TVShow>>(tvShows, HttpStatus.OK);
            }
            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{searchTerm}/{pageNumber}")
    public ResponseEntity<?> searchTVShow(@RequestHeader String Authorization, @PathVariable String searchTerm,
                                          @PathVariable int pageNumber, RestTemplate template) {
        try {
            Token token = UserUtils.parseToken(Authorization);
            if (UserUtils.validateToken(token)) {
                System.out.println("TV Show search request");
                userService.addSearch(token.getId(), searchTerm);
                List<TVShow> tvShows = tvShowService.searchTvShowByPageNumber(searchTerm, pageNumber, template);
                return new ResponseEntity<List<TVShow>>(tvShows, HttpStatus.OK);
            }
            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
