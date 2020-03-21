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
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @OneToMany
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    private Test test;

    public Question(String content, List<Answer> answerList, Test test) {
        this.content = content;
        this.answerList = answerList;
        this.test = test;
    }
}
