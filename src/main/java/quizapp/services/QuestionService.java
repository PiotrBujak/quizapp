package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.QuestionAssembler;
import quizapp.models.Question;
import quizapp.models.dtos.QuestionDto;
import quizapp.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAssembler questionAssembler;

    public List<Question> getQuestion(){
        return questionRepository.findAll();
    }

    public List<QuestionDto> getQuestionsDto(){
        return questionRepository
                .findAll()
                .stream()
                .map(questionAssembler::map)
                .collect(Collectors.toList());
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
