package instantcoffee.cinemaxx.repo;

import instantcoffee.cinemaxx.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {


    @RestResource(
            path = "getAllByRange",
            rel = "getAllByRange")
    @Query("SELECT m FROM Movie m WHERE m.startDate <= :endRange and m.endDate >= :startRange")
    List<Movie> getAllByRange(
            @Param("startRange") LocalDate startRange,
            @Param("endRange") LocalDate endRange);
}