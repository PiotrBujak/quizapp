package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.model.User;
import quizapp.model.dtos.UserDto;

@Component
public class UserAssembler implements Assembler<User, UserDto> {

    @Override
    public UserDto map(User from) {
        return UserDto
                .builder()
                .id(from.getId())
                .login(from.getLogin())
                .email(from.getEmail())
                .passord(from.getPassord())
                .testList(from.getTestList())
                .build();
    }

    @Override
    public User revers(UserDto to) {
        return User
                .builder()
                .id(to.getId())
                .login(to.getLogin())
                .email(to.getEmail())
                .passord(to.getPassord())
                .testList(to.getTestList())
                .build();
    }
}
