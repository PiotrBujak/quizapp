package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.models.ResolvedTest;
import quizapp.models.dtos.ResolvedTestDto;

@Component
public class ResolvedTestAssembler implements Assembler<ResolvedTest, ResolvedTestDto> {

    @Override
    public ResolvedTestDto map(ResolvedTest from) {
        return ResolvedTestDto
                .builder()
                .id(from.getId())
                .answerId(from.getAnswerId())
                .questionId(from.getQuestionId())
                .testId(from.getTestId())
                .userId(from.getUserId())
                .correct(from.isCorrect())
                .build();
    }

    @Override
    public ResolvedTest revers(ResolvedTestDto to) {
        return ResolvedTest
                .builder()
                .id(to.getId())
                .answerId(to.getAnswerId())
                .questionId(to.getQuestionId())
                .testId(to.getTestId())
                .userId(to.getUserId())
                .correct(to.isCorrect())
                .build();
    }
}
