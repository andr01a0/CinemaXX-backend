package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.dto.TimeSlotDTO;

public interface TimeSlotService {
    TimeSlotDTO findById(int timeSlotId);
}
