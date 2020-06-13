package com.app.services.implementations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.services.interfaces.ITvShowService;
import com.app.tvshow.pojos.TVShow;
import com.app.tvshow.pojos.catchers.TVShowGenres;
import com.app.tvshow.pojos.catchers.TVShowCatcher;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TvShowService implements ITvShowService {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final String tokenId = "abc";
    private static final String baseUrl = "https://api.themoviedb.org/3/tv/";

    @Override
    public TVShow getTvShowById(String tvShowId, RestTemplate template) throws ParseException {
        String url = baseUrl + "{tvShowId}?api_key={tokenId}&language=en-US";
        TVShowCatcher tvShowCatch = template.getForObject(url, TVShowCatcher.class, tvShowId, tokenId);
        TVShow show = convertTVShow(tvShowCatch);
        return show;
    }

    @Override
    public List<TVShow> getTvShowRecommendationById(String tvShowId, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException {
        String url = baseUrl + "{tvShowId}/recommendations?api_key={tokenId}&language=en-US&page={pageNumber}";
        String json = template.getForObject(url, String.class, tvShowId, tokenId, pageNumber);
        TVShowCatcher[] tvShowCatchList = convertJsonToTvShowCatcherArray(json);
        List<TVShow> tvShows = convertToTVShowList(Arrays.asList(tvShowCatchList));
        return tvShows;
    }

    // utility method for conversion
    @Override
    public TVShowCatcher[] convertJsonToTvShowCatcherArray(String json)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode results = mapper.readTree(json).get("results");
        TVShowCatcher[] tvShowCatchList = mapper.readValue(results.toString(), TVShowCatcher[].class);
        return tvShowCatchList;
    }

    @Override
    public List<TVShow> searchTvShowByPageNumber(String searchTerm, int pageNumber, RestTemplate template)
            throws IOException, JsonProcessingException, JsonParseException, JsonMappingException, ParseException {
        String url = "https://api.themoviedb.org/3/search/tv?api_key={tokenId}&language=en-US&query={searchTerm}&page={pageNumber}";
        String json = template.getForObject(url, String.class, tokenId, searchTerm, pageNumber);
        TVShowCatcher[] tvShowCatchList = convertJsonToTvShowCatcherArray(json);
        List<TVShow> tvShows = convertToTVShowList(Arrays.asList(tvShowCatchList));
        return tvShows;
    }

    @Override
    public TVShow convertTVShow(TVShowCatcher tVShowCatcher) throws ParseException {
        TVShow show = new TVShow();
        show.setFirstAirDate(sdf.parse(tVShowCatcher.getFirst_air_date()));

        for (TVShowGenres g : tVShowCatcher.getGenres()) {
            show.addGenreToList(g);
        }
        show.setHomepage(tVShowCatcher.getHomepage());
        show.setId(Integer.parseInt(tVShowCatcher.getId()));
        show.setName(tVShowCatcher.getName());
        show.setNumberOfEpisodes(Integer.parseInt(tVShowCatcher.getNumber_of_episodes()));
        show.setNumberOfSeasons(Integer.parseInt(tVShowCatcher.getNumber_of_seasons()));
        show.setOverview(tVShowCatcher.getOverview());
        show.setPopularity(Double.parseDouble(tVShowCatcher.getPopularity()));
        show.setPosterPath(tVShowCatcher.getPoster_path());
        show.setVoteAverage(Double.parseDouble(tVShowCatcher.getVote_average()));
        show.setVoteCount(Integer.parseInt(tVShowCatcher.getVote_count()));
        return show;
    }

    @Override
    public List<TVShow> convertToTVShowList(List<TVShowCatcher> tvShowCatchList) throws ParseException {
        List<TVShow> shows = new ArrayList<>();
        for (TVShowCatcher show : tvShowCatchList) {
            TVShow tvShow = new TVShow();
            // tvShow.setFirstAirDate(sdf.parse(show.getFirst_air_date()));
            tvShow.setId(Integer.parseInt(show.getId()));
            tvShow.setName(show.getName());
            tvShow.setVoteCount(Integer.parseInt(show.getVote_count()));
            tvShow.setVoteAverage(Double.parseDouble(show.getVote_average()));
            tvShow.setPosterPath(show.getPoster_path());
            // tvShow.setOverview(show.getOverview());
            // tvShow.setOriginCountry(Arrays.asList(show.getOrigin_country()));
            shows.add(tvShow);
        }
        return shows;
    }
}
