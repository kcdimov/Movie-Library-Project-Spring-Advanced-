package bg.softuni.movies.models.bindings;

public class SearchMovieBM {

    private String title;
    private String genre;

    public SearchMovieBM() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isEmpty() {

        return  (title == null || title.isEmpty()) &&
                (genre == null || genre.isEmpty());
    }
}

