package bg.softuni.movies.models.service;

public class PictureServiceModel extends BaseEntityServiceModel {

    private String url;
    private ActorServiceModel actor;
    private MovieServiceModel movie;
    // try without user picture
    private UserServiceModel user;

    public PictureServiceModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ActorServiceModel getActor() {
        return actor;
    }

    public void setActor(ActorServiceModel actor) {
        this.actor = actor;
    }

    public MovieServiceModel getMovie() {
        return movie;
    }

    public void setMovie(MovieServiceModel movie) {
        this.movie = movie;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
