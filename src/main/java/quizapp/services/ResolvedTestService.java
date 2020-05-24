package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.ResolvedTestAssembler;
import quizapp.models.ResolvedTest;
import quizapp.models.dtos.AnswerDto;
import quizapp.models.dtos.ResolvedTestDto;
import quizapp.models.dtos.UserDto;
import quizapp.repository.ResolvedTestRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResolvedTestService {

    @Autowired
    private ResolvedTestRepository resolvedTestRepository;

    @Autowired
    private ResolvedTestAssembler resolvedTestAssembler;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    public List<ResolvedTestDto> getResolvedTestsDtoByTestId(Integer id) {
        return resolvedTestRepository
                .findResolvedTestByTestId(id)
                .stream()
                .filter(Objects::nonNull)
                .map(resolvedTestAssembler::map)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getScoreByTestIdUserId(Integer testId) {
        Map<String, Integer> score = new HashMap<>();

        for (Integer userId : getResolvedTestsDtoByTestId(testId)
                .stream()
                .map(ResolvedTestDto::getUserId)
                .collect(Collectors.toList())) {
            score.put(userService.getUserDtoById(userId).getLogin(),
                    resolvedTestRepository.findScoredPointsByEachUserByTestId(testId, userId));
        }
        return score;
    }

    public ResolvedTest addResolvedTest(ResolvedTestDto resolvedTestDto) {
        return resolvedTestRepository.save(resolvedTestAssembler.revers(resolvedTestDto));
    }

    public void createResolvedTestDto(Integer testId, UserDto userDto, Map<Integer, Integer> appointedAnswers) {
        for (Integer question : appointedAnswers.keySet()) {
            AnswerDto answerDto = answerService.getAnswerDtoById(question);
            ResolvedTestDto resolvedTestDto = new ResolvedTestDto(
                    userDto.getId(),
                    testId,
                    question,
                    answerDto.getId(),
                    answerDto.isCorrect());
            addResolvedTest(resolvedTestDto);
        }
    }
}
