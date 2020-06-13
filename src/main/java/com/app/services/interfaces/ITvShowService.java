package com.app.services.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.app.tvshow.pojos.TVShow;
import com.app.tvshow.pojos.catchers.TVShowCatcher;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ITvShowService {

    TVShow getTvShowById(String tvShowId, RestTemplate template) throws ParseException;

    List<TVShow> getTvShowRecommendationById(String tvShowId, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException;

    // utility method for conversion
    TVShowCatcher[] convertJsonToTvShowCatcherArray(String json)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException;

    List<TVShow> searchTvShowByPageNumber(String searchTerm, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException;

    TVShow convertTVShow(TVShowCatcher tVShowCatcher) throws ParseException;

    List<TVShow> convertToTVShowList(List<TVShowCatcher> tvShowCatchList) throws ParseException;

}