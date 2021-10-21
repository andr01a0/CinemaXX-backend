package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.dto.TimeSlotDTO;
import instantcoffee.cinemaxx.error.ResourceNotFoundException;
import instantcoffee.cinemaxx.repo.TimeSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImp implements TimeSlotService{

    TimeSlotRepo timeSlotRepo;

    private String errorMessage(long id){
        return "Not found Team Slot with id = " + id;
    }

    @Autowired
    public TimeSlotServiceImp(TimeSlotRepo timeSlotRepo){
        this.timeSlotRepo = timeSlotRepo;
    }

    @Override
    public TimeSlotDTO findById(int timeSlotId) {
        return TimeSlotDTO.entityToDTO(timeSlotRepo.findById(timeSlotId)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(timeSlotId))));
    }
}
