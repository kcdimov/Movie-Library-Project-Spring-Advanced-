package bg.softuni.movies.models.service;

import bg.softuni.movies.models.entity.Movie;
import java.util.List;

public class ActorServiceModel extends BaseEntityServiceModel {

    private String actorName;
    private String biography;
    private List<Movie> movies;
    private List<PictureServiceModel> pictures;


    public ActorServiceModel() {
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<PictureServiceModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureServiceModel> pictures) {
        this.pictures = pictures;
    }
}
