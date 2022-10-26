package bg.softuni.movies.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class PictureAddBM {

    private String url;
    private String actorName;
    private String movie;

    public PictureAddBM() {
    }

    @NotBlank(message = "The field cannot be empty. Please put valid picture address!")
    @Length(min = 1, message = "Please enter picture's url!")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

}
