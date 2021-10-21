package instantcoffee.cinemaxx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import instantcoffee.cinemaxx.entities.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatListDTO {

    private Set<String> freeSeats;
    private Set<String> bookedSeats;

    public SeatListDTO() {
        this.freeSeats = new HashSet<>();
        this.bookedSeats = new HashSet<>();
    }

    public void addFreeSeat(Seat seat){
        freeSeats.add(seat.getSeatNumber());
    }

    public void addBookedSeat(Seat seat){
        bookedSeats.add(seat.getSeatNumber());
    }
}

