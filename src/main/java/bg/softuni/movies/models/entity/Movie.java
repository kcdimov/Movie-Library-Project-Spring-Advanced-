package bg.softuni.movies.models.entity;

import bg.softuni.movies.models.enums.Genre;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    private String title;
    private String plot; //description
    private List<Actor> actors;
    private List<Picture> pictures;
    private List<UserEntity> userEntities;
    private List<Review> reviews;
    private List<Genre> genres;

    public Movie() {
    }
    @Column(unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT")
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @ManyToMany
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @OneToMany
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public List<UserEntity> getUsers() {
        return userEntities;
    }

    public void setUsers(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    @ManyToMany
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
