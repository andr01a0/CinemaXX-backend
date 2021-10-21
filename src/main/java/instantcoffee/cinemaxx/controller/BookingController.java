package instantcoffee.cinemaxx.controller;

import instantcoffee.cinemaxx.authentication.User;
import instantcoffee.cinemaxx.dto.BookingDTO;
import instantcoffee.cinemaxx.dto.BookingDTOClient;
import instantcoffee.cinemaxx.dto.CreateBookingDTO;
import instantcoffee.cinemaxx.dto.SeatListDTO;
import instantcoffee.cinemaxx.entities.TheaterHall;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

public interface BookingController {

    @GetMapping
    public SeatListDTO getSeatList(@RequestParam TheaterHall theaterHall,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime);

    @PostMapping
    public ResponseEntity<BookingDTOClient> createBooking(CreateBookingDTO createBookingDto, User user);


    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@AuthenticationPrincipal User user, @PathVariable int id);

    @PutMapping()
    public ResponseEntity<String> editBooking(@AuthenticationPrincipal User user, @RequestBody BookingDTO bookingDTO);

}
