package bg.softuni.movies.models.bindings;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class ActorAddBM {

    private String name;
    private String biography;

    public ActorAddBM() {
    }

    @NotBlank(message = "Actor name cannot be empty")
    @Length(min = 3, message = "Actor name have to be minimum 3 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Please enter information about the actor or actress!")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
