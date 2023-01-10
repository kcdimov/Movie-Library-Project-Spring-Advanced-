package bg.softuni.movies.models.bindings;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MovieAddBM {

    private String title;
    private String plot;
    private String picture;
    private String genre;
    private String genres;
    private String actor;
    private List<String> actors;

    //TODO add music composer and validation of actor and directors

    public MovieAddBM() {
    }

    @NotBlank(message = "Movie title cannot be empty")
    @Length(min = 2, message = "Movie title must be minimum two characters")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank(message = "Movie plot cannot be empty")
    @Length(min = 10, message = "Movie plot have to be minimum 10 characters")
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @NotBlank(message = "Genre cannot be empty")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @NotBlank(message = "Please enter actor name")
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @NotNull(message = "Please enter actor name")
    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


//    @NotBlank(message = "Please enter Director first name")
//    public String getDirectorFirstName() {
//        return directorFirstName;
//    }
//
//    public void setDirectorFirstName(String directorFirstName) {
//        this.directorFirstName = directorFirstName;
//    }
//
//
//    @NotBlank(message = "Please enter Director last name")
//    public String getDirectorLastName() {
//        return directorLastName;
//    }
//
//    public void setDirectorLastName(String directorLastName) {
//        this.directorLastName = directorLastName;
//    }
}
