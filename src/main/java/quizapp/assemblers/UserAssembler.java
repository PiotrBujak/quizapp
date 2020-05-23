package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.models.User;
import quizapp.models.dtos.UserDto;

@Component
public class UserAssembler implements Assembler<User, UserDto> {

    @Override
    public UserDto map(User from) {
        return UserDto
                .builder()
                .id(from.getId())
                .login(from.getLogin())
                .email(from.getEmail())
                .password(from.getPassword())
                .active(from.getActive())
                .roles(from.getRoles())
                .build();
    }

    @Override
    public User revers(UserDto to) {
        return User
                .builder()
                .id(to.getId())
                .login(to.getLogin())
                .email(to.getEmail())
                .password(to.getPassword())
                .active(to.getActive())
                .roles(to.getRoles())
                .build();
    }
}
