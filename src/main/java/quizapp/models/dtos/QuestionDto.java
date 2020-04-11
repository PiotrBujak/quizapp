package quizapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizapp.models.Answer;
import quizapp.models.Test;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Integer id;

    private String content;

    private List<Answer> answerList = new ArrayList<>();

    private Test test;
}
