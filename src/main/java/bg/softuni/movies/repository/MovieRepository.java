package bg.softuni.movies.repository;

import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>,
        JpaSpecificationExecutor<Movie> {

    Movie findByTitle(String title);

    //List<Movie> findAllByTitle();

    @Query("SELECT m.title FROM Movie as m")
    List<String> findAllTitles();

    //List<Movie> findMovieByUsers();

    @Query("SELECT m FROM Movie as m WHERE ?1 MEMBER OF m.users")
    List<Movie> findMoviesByUserEntities(UserEntity userEntity);

   // List<Movie> findAllByGenres();

    @Query("SELECT m FROM Movie as m WHERE ?1 MEMBER OF m.genres")
    List<Movie> findAllByGenre(Genre genre);

}
