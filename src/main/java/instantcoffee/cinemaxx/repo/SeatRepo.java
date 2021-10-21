package instantcoffee.cinemaxx.repo;

import instantcoffee.cinemaxx.entities.Seat;
import instantcoffee.cinemaxx.entities.TheaterHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Integer> {
    public List<Seat> findAllByTheaterHall(TheaterHall theaterHall);
}
