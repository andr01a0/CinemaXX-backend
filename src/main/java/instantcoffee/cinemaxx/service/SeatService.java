package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.dto.SeatDTO;

public interface SeatService {
    SeatDTO findById(int seatId);
}
