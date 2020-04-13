package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.ResolvedTestAssembler;
import quizapp.models.ResolvedTest;
import quizapp.models.dtos.ResolvedTestDto;
import quizapp.repository.ResolvedTestRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResolvedTestService {

    @Autowired
    private ResolvedTestRepository resolvedTestRepository;

    @Autowired
    private ResolvedTestAssembler resolvedTestAssembler;

    public List<ResolvedTestDto> getResolvedTestsDto(){
        List<ResolvedTestDto> list =  resolvedTestRepository
                .findAll()
                .stream()
                .map(resolvedTestAssembler::map)
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparing(ResolvedTestDto::getTestId));
        return list;
    }

    public ResolvedTest addResolvedTest(ResolvedTestDto resolvedTestDto){
        return resolvedTestRepository.save(resolvedTestAssembler.revers(resolvedTestDto));
    }
}
