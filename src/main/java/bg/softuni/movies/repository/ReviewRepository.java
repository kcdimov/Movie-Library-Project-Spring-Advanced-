package bg.softuni.movies.repository;

import bg.softuni.movies.models.entity.Review;
import bg.softuni.movies.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //List<Review> findAllByMovie_Users();

    List<Review> findAllByMovie_Id(Long id);

    @Query("SELECT r FROM Review AS r WHERE r.user = ?1")
    List<Review> findAllMovieByUser(UserEntity userEntity);
}
