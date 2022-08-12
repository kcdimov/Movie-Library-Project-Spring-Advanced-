package bg.softuni.movies.models.service;


import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.UserEntity;

public class ReviewServiceModel {
    private String description;
    private String title;
    private double rating;
    private Movie movie;
    private UserEntity userEntity;

    public ReviewServiceModel() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
