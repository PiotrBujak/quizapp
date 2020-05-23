package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import quizapp.assemblers.AnswerAssembler;
import quizapp.assemblers.QuestionAssembler;
import quizapp.models.Answer;
import quizapp.models.Question;
import quizapp.models.dtos.AnswerDto;
import quizapp.models.dtos.QuestionDto;
import quizapp.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerAssembler answerAssembler;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionAssembler questionAssembler;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public List<AnswerDto> getAnswersDtoByQuestion(Integer questionId) {
        return answerRepository
                .findAll()
                .stream()
                .filter(answer -> answer.getQuestion().getId().equals(questionId))
                .map(answerAssembler::map)
                .collect(Collectors.toList());
    }

    public AnswerDto getAnswerDtoById(Integer answerId) {
        return answerRepository.findById(answerId)
                .map(answerAssembler::map)
                .orElse(null);
    }

    public List<AnswerDto> createAnswerDtoList(List<String> answerContentList, String correct, QuestionDto questionDto) {
        List<AnswerDto> answersList = new ArrayList<>();
        for (String answer : answerContentList) {
            if (!StringUtils.isEmpty(answer)) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setContent(answer);
                answerDto.setQuestion(questionAssembler.revers(questionDto));
                answersList.add(answerDto);
            }
        }
        if (correct != null) {
            answersList.get(Integer.valueOf(correct)).setCorrect(true);
        }
        return answersList;
    }

    public List<Answer> createAnswerList(List<String> answerContentList, String correct) {
        List<Answer> answersList = new ArrayList<>();
        for (String answer : answerContentList) {
            if (!StringUtils.isEmpty(answer)) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setContent(answer);

                answersList.add(answerAssembler.revers(answerDto));
            }
        }
        if (correct != null) {
            answersList.get(Integer.valueOf(correct)).setCorrect(true);
        }
        return answersList;
    }

    public void saveAnswerList(List<AnswerDto> answers) {
        for (AnswerDto answer : answers) {
            answerRepository.save(answerAssembler.revers(answer));
        }
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }

    public void updateAnswer(AnswerDto answerDto) {
        answerRepository.findById(answerDto.getId())
                .ifPresent(answer -> {
                    answer.setQuestion(answerDto.getQuestion());
                    answer.setContent(answerDto.getContent());
                    answer.setCorrect(answerDto.isCorrect());
                    answer.setId(answerDto.getId());
                });

    }
}
