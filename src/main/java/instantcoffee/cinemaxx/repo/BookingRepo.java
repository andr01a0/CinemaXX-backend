package instantcoffee.cinemaxx.repo;

import instantcoffee.cinemaxx.entities.Booking;
import instantcoffee.cinemaxx.entities.Seat;
import instantcoffee.cinemaxx.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByTimeSlotAndAndSeat(TimeSlot timeSlot, Seat seat);

}
