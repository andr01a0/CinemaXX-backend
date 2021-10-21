package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.authentication.User;
import instantcoffee.cinemaxx.dto.BookingDTO;
import instantcoffee.cinemaxx.dto.BookingDTOClient;
import instantcoffee.cinemaxx.dto.SeatListDTO;
import instantcoffee.cinemaxx.entities.*;
import instantcoffee.cinemaxx.error.BadRequestException;
import instantcoffee.cinemaxx.repo.BookingRepo;
import instantcoffee.cinemaxx.repo.SeatRepo;
import instantcoffee.cinemaxx.repo.TheaterHallRepo;
import instantcoffee.cinemaxx.repo.TimeSlotRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {

    @Autowired
    private final BookingRepo bookingRepository;
    @Autowired
    private final TheaterHallRepo theaterHallRepository;
    @Autowired
    private final SeatRepo seatRepo;
    @Autowired
    TimeSlotRepo timeSlotRepo;

    @Override
    public BookingDTOClient createBooking(User user, String theaterHallId, String movieId, String timeSlotId, String seatId) {
        TheaterHall theaterHall = theaterHallRepository
                .findById(Integer.parseInt(theaterHallId))
                .orElseThrow(() -> new BadRequestException("Perhaps you should try with a `theaterHallId` that exists?!"));
        System.out.println();
        Movie movie = theaterHall
                .getMovies()
                .stream()
                .filter(
                        _movie -> _movie.getMovieId() == Integer.parseInt(movieId)
                )
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Perhaps you should try with a `movieId` that exists?!"));

        TimeSlot timeSlot = movie
                .getTimeSlots()
                .stream()
                .filter(
                        _timeSlot -> _timeSlot.getTimeSlotId() == Integer.parseInt(timeSlotId)
                )
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Perhaps you should try with a `timeSlotId` that exists?!"));

        boolean isSeatTaken = timeSlot
                .getBookings()
                .stream()
                .map(
                        _booking -> _booking.getSeat()).filter(seat -> seat.getSeatId() == Integer.parseInt(seatId)
                )
                .findFirst()
                .isPresent();

        if (isSeatTaken) {
            throw new BadRequestException("You cannot sit with us.");
        }

        Seat seat = theaterHall
                .getSeats()
                .stream()
                .filter(
                        _seat -> _seat.getSeatId() == Integer.parseInt(seatId)
                )
                .findFirst()
                .orElseThrow(() -> new BadRequestException("You want to sit outside the Theater Hall or what?"));

        Booking booking = new Booking(user, timeSlot, seat);

        bookingRepository.save(booking);

        return BookingDTOClient.entityToDTO(booking);
    }
    @Override
    public SeatListDTO getSeatListDTO(TheaterHall theaterHall, LocalDateTime startTime) {
        SeatListDTO seatListDTO = new SeatListDTO();
        List<Seat> seatList = seatRepo.findAllByTheaterHall(theaterHall);
        List<TimeSlot> timeSlotList = timeSlotRepo.findAllByScheduledTime(startTime);

        for (Seat seat: seatList) {
            boolean booked = false;
            for (TimeSlot timeSlot: timeSlotList) {
                List<Booking> bookingList = bookingRepository.findAllByTimeSlotAndAndSeat(timeSlot, seat);
                if (!bookingList.isEmpty()) {
                    seatListDTO.addBookedSeat(seat);
                    booked = true;
                    break;
                }
            }
            if (!booked)
                seatListDTO.addFreeSeat(seat);
        }
        return seatListDTO;
    }

    @Override
    public void cancel(User user, int id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        if(Objects.equals(booking.getUser().getId(), user.getId()))
            bookingRepository.delete(booking);
        else
            throw new Exception();
    }

    @Override
    public void edit(User user, BookingDTO bookingDTO) throws Exception {
        Booking booking = BookingDTO.DTOtoEntity(bookingDTO);
        booking.getUser().setId(user.getId());
        booking.getSeat().setSeatId(bookingDTO.getSeatId());
        booking.getTimeSlot().setTimeSlotId(bookingDTO.getTimeSlotId());
        Booking bookingCompare = bookingRepository.findById(booking.getBookingId()).orElseThrow();
        if(Objects.equals(bookingCompare.getUser().getId(), user.getId())) {
            bookingRepository.save(booking);
        }else
            throw new Exception();
    }

}
