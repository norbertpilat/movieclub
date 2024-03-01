package lnp.movieclub.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {
    private Long id;
    private String email;
    private String password;
}
