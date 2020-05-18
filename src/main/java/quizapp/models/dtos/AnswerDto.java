package quizapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizapp.models.Question;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private Integer id;

    private String content;

    private boolean correct;

    private Question question;



}
