package lnp.movieclub.user;

import lnp.movieclub.user.dto.UserCredentialsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
  class UserCredentialsDtoMapper {

    static UserCredentialsDto map(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email,password,roles);
    }
}
