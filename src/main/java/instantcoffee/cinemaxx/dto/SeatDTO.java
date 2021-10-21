package instantcoffee.cinemaxx.dto;

import instantcoffee.cinemaxx.entities.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class SeatDTO {
    private int seatId;
    private String seatNumber;
    private int theaterHallId;

    public SeatDTO(Seat seat) {
        this.seatId = seat.getSeatId();
        this.seatNumber = seat.getSeatNumber();
        this.theaterHallId = seat.getTheaterHall().getTheaterHallId();
    }

    public SeatDTO(int seatId, String seatNumber, int theaterHallId) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.theaterHallId = theaterHallId;
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static SeatDTO entityToDTO(Seat seat) {
        SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);
        return seatDTO;
    }

    public static List<SeatDTO> entityToDTO(List<Seat> seats) {
        return seats.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

}
