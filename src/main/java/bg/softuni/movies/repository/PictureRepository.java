package bg.softuni.movies.repository;

import bg.softuni.movies.models.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
        List<Picture> findAllByMovie_Id(Long id);
        List<Picture> findAllByActor_Id(Long id);

}
