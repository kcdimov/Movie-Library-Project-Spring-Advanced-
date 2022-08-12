package bg.softuni.movies.repository;

import bg.softuni.movies.models.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Modifying
    @Query("DELETE FROM Statistic")
    void dropTable();
}
