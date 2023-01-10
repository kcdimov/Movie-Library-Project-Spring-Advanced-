package bg.softuni.movies.repository;

import bg.softuni.movies.models.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    Actor findByName(String actorName);

    @Query("SELECT a.name FROM Actor as a")
    List<String> findAllActorsNames();
}
