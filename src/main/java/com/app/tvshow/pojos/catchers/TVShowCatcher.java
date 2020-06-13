package com.app.tvshow.pojos.catchers;

import java.util.Arrays;

public class TVShowCatcher {
    private String vote_average;

    private TVShowGenres[] genres;

    private String[] genre_ids;

    private String[] episode_run_time;

    private String type;

    private String id;

    private String[] languages;

    private String number_of_seasons;

    private String last_air_date;

    private String in_production;

    private String name;

    private String popularity;

    private Networks[] networks;

    private Created_by[] created_by;

    private String backdrop_path;

    private String status;

    private String number_of_episodes;

    private String original_name;

    private String homepage;

    private String first_air_date;

    private String[] origin_country;

    private String original_language;

    private String overview;

    private Production_companies[] production_companies;

    private Seasons[] seasons;

    private String vote_count;

    private String poster_path;

    @Override
    public String toString() {
        return "TVShowCatcher [vote_average=" + vote_average + ", genres=" + Arrays.toString(genres) + ", genre_ids="
                + Arrays.toString(genre_ids) + ", episode_run_time=" + Arrays.toString(episode_run_time) + ", type="
                + type + ", id=" + id + ", languages=" + Arrays.toString(languages) + ", number_of_seasons="
                + number_of_seasons + ", last_air_date=" + last_air_date + ", in_production=" + in_production
                + ", name=" + name + ", popularity=" + popularity + ", networks=" + Arrays.toString(networks)
                + ", created_by=" + Arrays.toString(created_by) + ", backdrop_path=" + backdrop_path + ", status="
                + status + ", number_of_episodes=" + number_of_episodes + ", original_name=" + original_name
                + ", homepage=" + homepage + ", first_air_date=" + first_air_date + ", origin_country="
                + Arrays.toString(origin_country) + ", original_language=" + original_language + ", overview="
                + overview + ", production_companies=" + Arrays.toString(production_companies) + ", seasons="
                + Arrays.toString(seasons) + ", vote_count=" + vote_count + ", poster_path=" + poster_path + "]";
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public TVShowGenres[] getGenres() {
        return genres;
    }

    public void setGenres(TVShowGenres[] genres) {
        this.genres = genres;
    }

    public String[] getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(String[] episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(String number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getIn_production() {
        return in_production;
    }

    public void setIn_production(String in_production) {
        this.in_production = in_production;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Networks[] getNetworks() {
        return networks;
    }

    public void setNetworks(Networks[] networks) {
        this.networks = networks;
    }

    public Created_by[] getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Created_by[] created_by) {
        this.created_by = created_by;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(String number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Production_companies[] getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(Production_companies[] production_companies) {
        this.production_companies = production_companies;
    }

    public Seasons[] getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons[] seasons) {
        this.seasons = seasons;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
