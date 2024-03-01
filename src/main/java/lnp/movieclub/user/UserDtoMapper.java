package lnp.movieclub.user;

import lnp.movieclub.user.dto.UserDto;

public class UserDtoMapper {
    static UserDto map(User user){
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
