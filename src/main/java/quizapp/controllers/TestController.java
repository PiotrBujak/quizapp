package quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.models.dtos.QuestionDto;
import quizapp.models.dtos.TestDto;
import quizapp.services.AnswerService;
import quizapp.services.QuestionService;
import quizapp.services.TestService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/tests/{id}/{questionNumber}")
    public String solve(@PathVariable Integer id,
                        @PathVariable Integer questionNumber,
                        @RequestParam(required = false) String appointedAnswer,
                        ModelMap modelMap) {
        currentQuestion = questionNumber;
        QuestionDto question = questionService.getQuestionsDtoByTest(id).get(currentQuestion-1);
//        putCheckedAnswerToAppointedAnswers(question.getId(), appointedAnswer);
        modelMap.put("test", testService.getTestDtoById(id));
        modelMap.put("question", question);
        modelMap.put("answers", answerService.getAnswersDtoByQuestion(question.getId()));
        modelMap.put("currentQuestion", currentQuestion);
        modelMap.put("numberOfQuestions", questionService.getQuestionsDtoByTest(id).size());
        modelMap.put("checkedAnswer", getCheckedAnswerByQuestionNumber(questionNumber));
        return "test";
    }

    @GetMapping("/tests/{id}/finish")
    public String finish(@PathVariable Integer id, Model model){
        appointedAnswers.clear();
        return "finish";
    }

    private Integer getCheckedAnswerByQuestionNumber(Integer questionId){
        return appointedAnswers.keySet().contains(questionId) ? appointedAnswers.get(questionId) : null;
    }

    private void putCheckedAnswerToAppointedAnswers(Integer questionId, Integer answerId){
        if(questionId != null && answerId != null){
            appointedAnswers.put(questionId, answerId);
        }
    }
}
