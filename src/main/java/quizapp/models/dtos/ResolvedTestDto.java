package quizapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestDto {

    private long id;

    private Integer userId;

    private Integer testId;

    private Integer questionId;

    private Integer answerId;

    private boolean correct;

}
