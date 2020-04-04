package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.TestAssembler;
import quizapp.model.Test;
import quizapp.model.dtos.TestDto;
import quizapp.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestAssembler testAssembler;

    public List<Test> getTests(){
        return testRepository.findAll();
    }

    public List<TestDto> getTestsDto(){
        return testRepository
                .findAll()
                .stream()
                .map(testAssembler::map)
                .collect(Collectors.toList());
    }

    public Test addTest(TestDto testDto){
        return testRepository.save(testAssembler.revers(testDto));
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
                    test.setQuestionList(testDto.getQuestionList());
                });
    }
}
