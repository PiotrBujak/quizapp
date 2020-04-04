package quizapp.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizapp.model.Question;
import quizapp.model.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {

    private Integer id;

    private String content;

    private List<Question> questionList = new ArrayList<>();

    private User user;
}
