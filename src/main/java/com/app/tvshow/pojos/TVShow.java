package com.app.tvshow.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.app.tvshow.pojos.catchers.TVShowGenres;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class TVShow implements Serializable {
    private Set<TVShowGenres> genres = new LinkedHashSet<>();
    private Integer id;
    private Integer numberOfSeasons;
    private String name;
    private Double popularity;
    private Integer numberOfEpisodes;
    private String homepage;
    private Date firstAirDate;
    private String overview;
    private Integer voteCount;
    private String posterPath;
    private Double voteAverage;

    public TVShow() {
        super();
    }

    @Override
    public String toString() {
        return "TVShow [id=" + id + ", numberOfSeasons=" + numberOfSeasons + ", name=" + name + ", popularity="
                + popularity + ", numberOfEpisodes=" + numberOfEpisodes + ", homepage=" + homepage + ", firstAirDate="
                + firstAirDate + ", overview=" + overview + ", voteCount=" + voteCount + ", posterPath=" + posterPath
                + ", voteAverage=" + voteAverage + "]";
    }

    @Id
    @Column(name = "tvshow_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tvshow_genre_mapping", joinColumns = {@JoinColumn(name = "tvshow_id")}, inverseJoinColumns = {
            @JoinColumn(name = "genre_id")})
    public Set<TVShowGenres> getGenres() {
        return genres;
    }

    // convenience method
    public void addGenreToList(TVShowGenres g) {
        genres.add(g);
    }

    public void setGenres(Set<TVShowGenres> genres) {
        this.genres = genres;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Column(columnDefinition = "text")
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstAirDate == null) ? 0 : firstAirDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        TVShow other = (TVShow) obj;
        if (firstAirDate == null) {
            if (other.firstAirDate != null)
                return false;
        } else if (!firstAirDate.equals(other.firstAirDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
