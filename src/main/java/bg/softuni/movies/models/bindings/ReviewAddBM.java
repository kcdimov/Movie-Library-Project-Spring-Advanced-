package bg.softuni.movies.models.bindings;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ReviewAddBM {

    private String description;
    private String title;
    private double rating;
    private String movie;

    public ReviewAddBM() {
    }

    @NotBlank(message = "Please fill description")
    @Length(min = 10, message = "Description have to minimum 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank(message = "Please enter title")
    @Length(min = 3, message = "Title have to be minimum 3 characters")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Min(value = 1, message = "Rating have to be positive number")
    @Max(value = 10, message = "Rating cannot be more than 10")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @NotBlank(message = "Please enter movie")
    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
