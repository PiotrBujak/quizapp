package quizapp.models;

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
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    private Test test;

    public Question(String content) {
        this.content = content;
    }

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        answerList.add(answer);
    }
}
