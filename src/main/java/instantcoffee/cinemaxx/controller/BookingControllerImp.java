package instantcoffee.cinemaxx.controller;

import instantcoffee.cinemaxx.authentication.User;
import instantcoffee.cinemaxx.dto.BookingDTO;
import instantcoffee.cinemaxx.dto.BookingDTOClient;
import instantcoffee.cinemaxx.dto.CreateBookingDTO;
import instantcoffee.cinemaxx.dto.SeatListDTO;
import instantcoffee.cinemaxx.entities.TheaterHall;
import instantcoffee.cinemaxx.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingControllerImp implements BookingController {

    @Autowired
    BookingService bookingService;


    @Override
    public ResponseEntity<BookingDTOClient> createBooking(@RequestBody CreateBookingDTO createBookingDto,
                                                          @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                bookingService.createBooking(
                        user,
                        createBookingDto.getTheaterHallId(),
                        createBookingDto.getMovieId(),
                        createBookingDto.getTimeSlotId(),
                        createBookingDto.getSeatId())
        );
    }

    public SeatListDTO getSeatList(TheaterHall theaterHall, LocalDateTime startTime) {
        return bookingService.getSeatListDTO(theaterHall, startTime);
    }


    @Override
    @Transactional
    public ResponseEntity<String> editBooking(User user, BookingDTO bookingDTO) {
        try {
            bookingService.edit(user, bookingDTO);
            return ResponseEntity.ok("{message: \"Booking successfully updated\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{message: \"Failed to update booking\"}");
        }
    }

    @Override
    public ResponseEntity<String> cancelBooking(User user, int id) {
        try {
            bookingService.cancel(user, id);
            return ResponseEntity.ok("{message : \"Booking canceled\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{message : \"Failed to cancel booking\"}");
        }
    }

}
