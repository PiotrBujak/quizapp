package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.QuestionAssembler;
import quizapp.assemblers.TestAssembler;
import quizapp.models.Answer;
import quizapp.models.Question;
import quizapp.models.dtos.QuestionDto;
import quizapp.models.dtos.TestDto;
import quizapp.repository.QuestionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAssembler questionAssembler;

    @Autowired
    private TestAssembler testAssembler;

    public List<Question> getQuestion(){
        return questionRepository.findAll();
    }

    public List<QuestionDto> getQuestionsDtoByTest(Integer testId){
        List<QuestionDto> questionDtoList = questionRepository
                .findAll()
                .stream()
                .filter(question -> question.getTest().getId().equals(testId))
                .map(questionAssembler::map)
                .collect(Collectors.toList());

        questionDtoList.sort(Comparator.comparingInt(QuestionDto::getId).reversed());
        return questionDtoList;
    }

    public QuestionDto createQuestionDto(List<Answer> answerList, String question, TestDto testDto){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setContent(question);
//        for (Answer answer : answerList) {
//            answer.setQuestion(questionAssembler.revers(questionDto));
//        }
        questionDto.setAnswerList(answerList);
        questionDto.setTest(testAssembler.revers(testDto));
        return questionDto;
    }

    public void saveQuestionDtoList(List<QuestionDto> questionDtos){
        for (QuestionDto questionDto : questionDtos){
            questionRepository.save(questionAssembler.revers(questionDto));
        }
    }

    public Question addQuestion(QuestionDto questionDto){
        return questionRepository.save(questionAssembler.revers(questionDto));
    }

    public void deleteQuestion(Integer id){
        questionRepository.deleteById(id);
    }

    public void updateQuestion(QuestionDto questionDto){
        questionRepository.findById(questionDto.getId())
                .ifPresent(question -> {
                    question.setTest(questionDto.getTest());
                    question.setAnswerList(questionDto.getAnswerList());
                    question.setContent(questionDto.getContent());
                    question.setId(questionDto.getId());
                });
    }
}
