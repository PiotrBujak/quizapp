package quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.assemblers.UserAssembler;
import quizapp.models.dtos.AnswerDto;
import quizapp.models.dtos.QuestionDto;
import quizapp.models.dtos.TestDto;
import quizapp.models.dtos.UserDto;
import quizapp.services.AnswerService;
import quizapp.services.QuestionService;
import quizapp.services.TestService;
import quizapp.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin
@Scope(value = "session")
public class QuestionController {
    private TestDto testDto = new TestDto();
    private List<QuestionDto> questionList = new ArrayList<>();
    private List<AnswerDto> answerList = new ArrayList<>();
    private UserDto userDto = new UserDto();

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/addQuestion")
    public String addQuestion(@RequestParam(value = "content", required = false) String content,
                              @RequestParam(value = "description", required = false) String description,
                              ModelMap modelMap) {
        clearData();
        userDto = userService.getUserDtoByName(SecurityContextHolder.getContext().getAuthentication().getName());
        testDto.setContent(content);
        testDto.setDescription(description);
        testDto.setUser(userAssembler.revers(userDto));
        modelMap.put("test", testDto.getContent());
        modelMap.put("questionListSize", questionList.size());
        return "addQuestion";
    }

    @GetMapping("/continue")
    public String continueAddingQuestions(@RequestParam(value = "question", required = false) String question,
                                          @RequestParam(value = "answer1", required = false) String answer1,
                                          @RequestParam(value = "answer2", required = false) String answer2,
                                          @RequestParam(value = "answer3", required = false) String answer3,
                                          @RequestParam(value = "answer4", required = false) String answer4,
                                          @RequestParam(value = "correct", required = false) String correct,
                                          @RequestParam(value = "finish", required = false) String finish,
                                          ModelMap modelMap) {
        QuestionDto questionDto = questionService.createQuestionDto(
                answerService.createAnswerList(Arrays.asList(answer1, answer2, answer3, answer4), correct),
                question,
                testDto);
        answerList.addAll(answerService.createAnswerDtoList(Arrays.asList(answer1, answer2, answer3, answer4), correct, questionDto));
        questionList.add(questionDto);
        modelMap.put("test", testDto.getContent());
        if (Boolean.valueOf(finish)) {
            answerService.saveAnswerList(answerList);
            clearData();
            modelMap.addAttribute("message", SecurityContextHolder.getContext().getAuthentication().getName());
            modelMap.addAttribute("tests", testService.getTestsDto());
            return "index";
        }
        return "addQuestion";
    }

    private void clearData() {
        questionList.clear();
        answerList.clear();
        testDto = new TestDto();
        userDto = new UserDto();
    }

}
