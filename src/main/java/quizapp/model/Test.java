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
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Question> questionList = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addQuestion(Question question) {
        question.setTest(this);
        questionList.add(question);
    }

    public Test(String content, User user) {
        this.content = content;
        this.user = user;
    }
}
