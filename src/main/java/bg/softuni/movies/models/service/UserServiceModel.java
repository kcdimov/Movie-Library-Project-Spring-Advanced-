package bg.softuni.movies.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserServiceModel extends BaseEntityServiceModel {
    private String username;
    private String password;
    private String email;
    private List<ReviewServiceModel> reviews;
    //try without picture
    private List<PictureServiceModel> pictures;
    private List<MovieServiceModel> movies;


    public UserServiceModel() {
    }


    @NotBlank
    @Length(min = 5, max=20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Length(min = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReviewServiceModel> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewServiceModel> reviews) {
        this.reviews = reviews;
    }

    public List<PictureServiceModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureServiceModel> pictures) {
        this.pictures = pictures;
    }

    public List<MovieServiceModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieServiceModel> movies) {
        this.movies = movies;
    }


}
