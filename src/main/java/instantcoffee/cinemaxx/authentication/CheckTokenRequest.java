package instantcoffee.cinemaxx.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CheckTokenRequest {
    private String token;
}
