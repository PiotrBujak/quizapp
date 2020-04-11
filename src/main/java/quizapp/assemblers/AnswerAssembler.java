package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.models.Answer;
import quizapp.models.dtos.AnswerDto;

@Component
public class AnswerAssembler implements Assembler<Answer, AnswerDto> {

    @Override
    public AnswerDto map(Answer from) {
        return AnswerDto
                .builder()
                .id(from.getId())
                .content(from.getContent())
                .correct(from.isCorrect())
                .question(from.getQuestion())
                .build();
    }

    @Override
    public Answer revers(AnswerDto to) {
        return Answer
                .builder()
                .id(to.getId())
                .content(to.getContent())
                .correct(to.isCorrect())
                .question(to.getQuestion())
                .build();
    }
}
