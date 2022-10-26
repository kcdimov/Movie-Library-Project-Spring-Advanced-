package bg.softuni.movies.models.service;

import bg.softuni.movies.models.entity.Picture;
import bg.softuni.movies.models.enums.Genre;
import java.util.List;

public class MovieServiceModel extends BaseEntityServiceModel {

    private String title;
    private String plot;//description
    private List<ActorServiceModel> actors;
    private List<Genre> genres;
    private Picture picture;
    private List<UserServiceModel> users;
    private List<ReviewServiceModel> reviews;

    public MovieServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }


    public List<ActorServiceModel> getActors() {
        return actors;
    }

    public void setActors(List<ActorServiceModel> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Picture getPictures() {
        return picture;
    }

    public void setPictures(Picture picture) {
        this.picture = picture;
    }

    public List<UserServiceModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    public List<ReviewServiceModel> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewServiceModel> reviews) {
        this.reviews = reviews;
    }
}
