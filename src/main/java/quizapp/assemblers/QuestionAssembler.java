package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.models.Question;
import quizapp.models.dtos.QuestionDto;

@Component
public class QuestionAssembler implements Assembler<Question, QuestionDto> {

    @Override
    public QuestionDto map(Question from) {
        return QuestionDto
                .builder()
                .id(from.getId())
                .content(from.getContent())
                .test(from.getTest())
                .build();
    }

    @Override
    public Question revers(QuestionDto to) {
        return Question
                .builder()
                .id(to.getId())
                .content(to.getContent())
                .test(to.getTest())
                .build();
    }
}
