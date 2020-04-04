package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.AnswerAssembler;
import quizapp.model.Answer;
import quizapp.model.dtos.AnswerDto;
import quizapp.repository.AnswerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerAssembler answerAssembler;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    public List<AnswerDto> getAnswersDto(){
        return answerRepository
                .findAll()
                .stream()
                .map(answerAssembler::map)
                .collect(Collectors.toList());
    }

    public Answer addAnswer(AnswerDto answerDto){
        return answerRepository.save(answerAssembler.revers(answerDto));
    }

    public void deleteAnswer(Integer id){
        answerRepository.deleteById(id);
    }

    public void updateAnswer(AnswerDto answerDto){
        answerRepository.findById(answerDto.getId())
                .ifPresent(answer -> {
                    answer.setQuestion(answerDto.getQuestion());
                    answer.setContent(answerDto.getContent());
                    answer.setCorrect(answerDto.isCorrect());
                    answer.setId(answerDto.getId());
                });

    }
}
