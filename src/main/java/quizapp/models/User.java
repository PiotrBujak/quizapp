package quizapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private String password;

    private String email;

    private int active;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Test> testList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id")
    )
    private Set<Role> roles;

    public void addTest(Test test) {
        test.setUser(this);
        testList.add(test);
    }

    public User(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.testList = user.getTestList();
        this.roles = user.getRoles();
    }
}
