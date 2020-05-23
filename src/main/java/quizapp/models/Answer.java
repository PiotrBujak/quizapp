package quizapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    private boolean correct;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Question question;

    public Answer(String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }

}
