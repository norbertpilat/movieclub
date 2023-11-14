package lnp.movieclub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
public class UserCredentialsDto {
    private String email;
    private String password;
    private Set<String> roles;
}
