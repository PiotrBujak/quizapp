package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.QuestionAssembler;
import quizapp.assemblers.TestAssembler;
import quizapp.assemblers.UserAssembler;
import quizapp.models.Test;
import quizapp.models.dtos.QuestionDto;
import quizapp.models.dtos.TestDto;
import quizapp.models.dtos.UserDto;
import quizapp.repository.TestRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestAssembler testAssembler;

    @Autowired
    private QuestionAssembler questionAssembler;

    @Autowired
    private UserAssembler userAssembler;

    public List<Test> getTests(){
        return testRepository.findAll();
    }

    public List<TestDto> getTestsDto(){
        List<TestDto> list = testRepository
                .findAll()
                .stream()
                .map(testAssembler::map)
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparing(TestDto::getContent));
        return list;
    }

    public TestDto getTestDtoById(Integer id){
        return getTestsDto()
                .stream()
                .filter(test -> test.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveTestDto(TestDto testDto, UserDto userDto){
        testDto.setUser(userAssembler.revers(userDto));
        testRepository.saveAndFlush(testAssembler.revers(testDto));
    }

    public void deleteTest(Integer id){
        testRepository.deleteById(id);
    }

    public void updateTest(TestDto testDto){
        testRepository.findById(testDto.getId())
                .ifPresent(test -> {
                    test.setUser(testDto.getUser());
                    test.setContent(testDto.getContent());
                    test.setId(testDto.getId());
//                    test.setQuestionList(testDto.getQuestionList());
                });
    }
}
