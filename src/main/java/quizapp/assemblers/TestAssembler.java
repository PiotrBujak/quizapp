package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.model.Test;
import quizapp.model.dtos.TestDto;

@Component
public class TestAssembler implements Assembler<Test, TestDto> {

    @Override
    public TestDto map(Test from) {
        return TestDto
                .builder()
                .id(from.getId())
                .content(from.getContent())
                .user(from.getUser())
                .questionList(from.getQuestionList())
                .build();
    }

    @Override
    public Test revers(TestDto to) {
        return Test
                .builder()
                .id(to.getId())
                .content(to.getContent())
                .user(to.getUser())
                .questionList(to.getQuestionList())
                .build();
    }
}
