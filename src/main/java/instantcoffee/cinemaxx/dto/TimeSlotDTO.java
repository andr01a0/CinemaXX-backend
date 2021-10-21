package instantcoffee.cinemaxx.dto;

import instantcoffee.cinemaxx.entities.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TimeSlotDTO {

    private LocalDateTime scheduledTime;
    private static ModelMapper modelMapper = new ModelMapper();

    public static TimeSlotDTO entityToDTO(TimeSlot timeSlot) {
        TimeSlotDTO timeSlotDTO = modelMapper.map(timeSlot, TimeSlotDTO.class);
        return timeSlotDTO;
    }

    public static TimeSlot DTOtoEntity(TimeSlotDTO timeSlotDTO) {
        TimeSlot timeSlot = modelMapper.map(timeSlotDTO, TimeSlot.class);
        return timeSlot;
    }

}
