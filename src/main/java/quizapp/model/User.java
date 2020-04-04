package quizapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String passord;

    private String email;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Test> testList = new ArrayList<>();

    public User(String login, String passord, String email) {
        this.login = login;
        this.passord = passord;
        this.email = email;
    }

    public void addTest(Test test) {
        test.setUser(this);
        testList.add(test);
    }
}
