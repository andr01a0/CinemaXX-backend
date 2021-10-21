package instantcoffee.cinemaxx.service;

import instantcoffee.cinemaxx.dto.SeatDTO;
import instantcoffee.cinemaxx.error.ResourceNotFoundException;
import instantcoffee.cinemaxx.repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImp implements SeatService{

    SeatRepo seatRepo;

    private String errorMessage(long id){
        return "Not found Seat with id = " + id;
    }

    @Autowired
    public SeatServiceImp(SeatRepo seatRepo){
        this.seatRepo = seatRepo;
    }

    @Override
    public SeatDTO findById(int seatId) {
        return SeatDTO.entityToDTO(seatRepo.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(seatId))));
    }
}
