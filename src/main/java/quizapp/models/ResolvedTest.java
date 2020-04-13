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
@Table(name = "resolved_tests")
public class ResolvedTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer userId;

    private Integer testId;

    private Integer questionId;

    private Integer answerId;

    private boolean correct;

}
