package instantcoffee.cinemaxx.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingDTO {

    private final String seatId;
    private final String timeSlotId;
    private final String movieId;
    private final String theaterHallId;

    public CreateBookingDTO(String seatId, String timeSlotId, String movieId, String theaterHallId) {
        this.seatId = seatId;
        this.timeSlotId = timeSlotId;
        this.movieId = movieId;
        this.theaterHallId = theaterHallId;
    }
}
