package com.app.user.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.movie.pojos.Movie;
import com.app.tvshow.pojos.TVShow;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String password;
    private boolean isAccountActive;
    private String securityQuestion;
    private String securityAnswer;

    Set<Movie> likedMovies = new LinkedHashSet<>();
    Set<TVShow> likedShows = new LinkedHashSet<>();
    Set<Movie> movieWatchList = new LinkedHashSet<>();
    Set<TVShow> tvShowWatchList = new LinkedHashSet<>();

    Set<String> searches = new LinkedHashSet<>();

    // profile based
    private String displayName;
    private String profilePicUrl;
    private Date dateOfBirth;
    private String gender;

    public User() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @JsonIgnore
    @Column(length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountActive() {
        return isAccountActive;
    }

    public void setAccountActive(boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void addSearchItem(String searchTerm) {
        searches.add(searchTerm);
    }

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "user_searches", joinColumns = {@JoinColumn(name = "user_id")})
    public Set<String> getSearches() {
        return searches;
    }

    public void setSearches(Set<String> searches) {
        this.searches = searches;
    }

    // convenience method for movies
    public void addLikedMovie(Movie movie) {
        likedMovies.add(movie);
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_liked_movies", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "movie_id")})
    public Set<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(Set<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    // convenience method for tvshows
    public void addLikedTVShows(TVShow show) {
        likedShows.add(show);
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_liked_tvshows", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "tvshow_id")})
    public Set<TVShow> getLikedShows() {
        return likedShows;
    }

    public void setLikedShows(Set<TVShow> likedShows) {
        this.likedShows = likedShows;
    }

    // convenience method
    public void addMovieToWatchList(Movie movie) {
        movieWatchList.add(movie);
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_watchlist", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "movie_id")})
    public Set<Movie> getMovieWatchList() {
        return movieWatchList;
    }

    public void setMovieWatchList(Set<Movie> movieWatchList) {
        this.movieWatchList = movieWatchList;
    }

    // convenience
    public void addTvShowToWatchList(TVShow show) {
        tvShowWatchList.add(show);
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tvshow_watchlist", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "tvshow_id")})
    public Set<TVShow> getTvShowWatchList() {
        return tvShowWatchList;
    }

    public void setTvShowWatchList(Set<TVShow> tvShowWatchList) {
        this.tvShowWatchList = tvShowWatchList;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(columnDefinition = "text")
    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Temporal(TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password
                + ", isAccountActive=" + isAccountActive + ", securityQuestion=" + securityQuestion
                + ", securityAnswer=" + securityAnswer + ", displayName=" + displayName + ", profilePicUrl="
                + profilePicUrl + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

}
