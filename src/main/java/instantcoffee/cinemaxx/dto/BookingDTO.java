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
public class BookingDTO {

    private int bookingId;
    private LocalDateTime scheduledTime;
    private int seatId;
    private int timeSlotId;

    public BookingDTO(Booking booking){
        this.bookingId = booking.getBookingId();
        this.seatId = getSeatId();
        this.scheduledTime = booking.getTimeSlot().getScheduledTime();
        this.timeSlotId = booking.getTimeSlot().getTimeSlotId();
    }


    private static ModelMapper modelMapper = new ModelMapper();

    public static BookingDTO entityToDTO(Booking booking) {
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        return bookingDTO;
    }

    public static List<BookingDTO> entityToDTO(List<Booking> bookings) {
        return bookings.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Booking DTOtoEntity(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        return booking;
    }





}
