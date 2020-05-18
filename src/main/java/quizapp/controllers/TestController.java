package quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.models.dtos.AnswerDto;
import quizapp.models.dtos.QuestionDto;
import quizapp.models.dtos.ResolvedTestDto;
import quizapp.models.dtos.UserDto;
import quizapp.services.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@Scope(value = "session")
public class TestController {
    private Integer currentQuestion = 1;
    private Map<Integer, Integer> appointedAnswers = new HashMap<>();

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ResolvedTestService resolvedTestService;

    @Autowired
    private UserService userService;

    @GetMapping("/tests/{id}/{questionNumber}")
    public String solve(@PathVariable Integer id,
                        @PathVariable Integer questionNumber,
                        @RequestParam(value = "appointedAnswer", required = false) Integer appointedAnswer,
                        ModelMap modelMap) {

        QuestionDto question = questionService.getQuestionsDtoByTest(id).get(currentQuestion - 1);
        putCheckedAnswerToAppointedAnswers(question.getId(), appointedAnswer);
        currentQuestion = questionNumber;

        modelMap.put("test", testService.getTestDtoById(id));
        modelMap.put("question", question);
        modelMap.put("answers", answerService.getAnswersDtoByQuestion(question.getId()));
        modelMap.put("currentQuestion", currentQuestion);
        modelMap.put("numberOfQuestions", questionService.getQuestionsDtoByTest(id).size());
        return "test";
    }

    @GetMapping("/tests/{id}/finish")
    public String finish(@PathVariable Integer id,
                         @RequestParam(value = "appointedAnswer", required = false) Integer appointedAnswer) {

        putCheckedAnswerToAppointedAnswers(questionService.getQuestionsDtoByTest(id).get(currentQuestion - 1).getId(), appointedAnswer);
        createResolvedTestDto(id);
        return "finish";
    }

    @GetMapping("/create")
    public String create(ModelMap modelMap) {
        return "create";
    }

    @GetMapping("/resolved")
    public String resolved(ModelMap modelMap) {
        return "resolved";
    }

    private void putCheckedAnswerToAppointedAnswers(Integer questionId, Integer answerId) {
        if (questionId != null && answerId != null) {
            appointedAnswers.put(questionId, answerId);
        }
    }

    private void createResolvedTestDto(Integer testId) {
        UserDto userDto = userService.getUserDtoByName(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Integer question : appointedAnswers.keySet()) {
            AnswerDto answerDto = answerService.getAnswerDtoById(question);
            ResolvedTestDto resolvedTestDto = new ResolvedTestDto(
                    userDto.getId(),
                    testId,
                    question,
                    answerDto.getId(),
                    answerDto.isCorrect());
            resolvedTestService.addResolvedTest(resolvedTestDto);
        }
        appointedAnswers.clear();
    }
}
