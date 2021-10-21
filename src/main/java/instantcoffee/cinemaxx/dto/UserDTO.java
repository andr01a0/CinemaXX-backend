package instantcoffee.cinemaxx.dto;

import instantcoffee.cinemaxx.authentication.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private static ModelMapper modelMapper = new ModelMapper();

    public static UserDTO entityToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public static User DTOtoEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
