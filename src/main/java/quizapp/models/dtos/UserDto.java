package quizapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizapp.models.Role;
import quizapp.models.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String login;

    private String password;

    private String email;

    private int active;

    private List<Test> testList = new ArrayList<>();

    private Set<Role> roles;

}
