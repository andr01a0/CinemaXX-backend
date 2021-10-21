package instantcoffee.cinemaxx.dto;

import instantcoffee.cinemaxx.entities.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class BookingDTOClient {

    private int bookingId;
    private String userEmail;
    private LocalDateTime scheduledTime;
    private String seatNumber;

    public BookingDTOClient(String userEmail, LocalDateTime scheduledTime, String seatNumber){
        this.userEmail = userEmail;
        this.scheduledTime = scheduledTime;
        this.seatNumber = seatNumber;
    }

    public BookingDTOClient(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.userEmail = booking.getUser().getEmail();
        this.scheduledTime = booking.getTimeSlot().getScheduledTime();
        this.seatNumber = booking.getSeat().getSeatNumber();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static BookingDTOClient entityToDTO(Booking booking) {
        BookingDTOClient bookingDTOClient = modelMapper.map(booking, BookingDTOClient.class);
        return bookingDTOClient;
    }

    public static List<BookingDTOClient> entityToDTO(List<Booking> bookings) {
        return bookings.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Booking DTOtoEntity(BookingDTOClient bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        return booking;
    }

}
