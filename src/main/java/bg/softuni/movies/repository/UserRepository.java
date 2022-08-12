package bg.softuni.movies.repository;


import bg.softuni.movies.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

   Optional<UserEntity> findByUsername(String username);
}
