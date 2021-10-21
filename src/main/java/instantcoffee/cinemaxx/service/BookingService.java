package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.authentication.User;
import instantcoffee.cinemaxx.dto.BookingDTO;
import instantcoffee.cinemaxx.dto.BookingDTOClient;
import instantcoffee.cinemaxx.dto.SeatListDTO;
import instantcoffee.cinemaxx.entities.TheaterHall;

import java.time.LocalDateTime;

public interface BookingService {

    public BookingDTOClient createBooking(User user, String theaterHallId, String movieId, String timeSlotId, String seatId);

    public SeatListDTO getSeatListDTO(TheaterHall theaterHall, LocalDateTime startTime);

    public void cancel(User user, int id) throws Exception;

    public void edit(User user, BookingDTO bookingDTO) throws Exception;

}
