package bg.softuni.movies.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    private String url;
    private Actor actor;
    private Movie movie;
    private UserEntity userEntity;

    //TODO maybe to remove user. I think dont need from picture;

    public Picture() {
    }

    @Column(columnDefinition = "TEXT")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @ManyToOne
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
