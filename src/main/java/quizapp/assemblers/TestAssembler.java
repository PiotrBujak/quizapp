package quizapp.assemblers;

import org.springframework.stereotype.Component;
import quizapp.commons.Assembler;
import quizapp.models.Test;
import quizapp.models.dtos.TestDto;

@Component
public class TestAssembler implements Assembler<Test, TestDto> {

    @Override
    public TestDto map(Test from) {
        return TestDto
                .builder()
                .id(from.getId())
                .content(from.getContent())
                .user(from.getUser())
                .description(from.getDescription())
                .build();
    }

    @Override
    public Test revers(TestDto to) {
        return Test
                .builder()
                .id(to.getId())
                .content(to.getContent())
                .user(to.getUser())
                .description(to.getDescription())
                .build();
    }
}
