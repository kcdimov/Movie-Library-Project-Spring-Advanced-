package bg.softuni.movies.models.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor extends BaseEntity {

    private String name;
    private String biography;
    private List<Movie> movies;
    private List<Picture> pictures;

    public Actor() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

//    @ManyToMany (mappedBy = "actors")
    @ManyToMany
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @OneToMany
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
