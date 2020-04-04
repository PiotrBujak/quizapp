package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.assemblers.UserAssembler;
import quizapp.model.User;
import quizapp.model.dtos.UserDto;
import quizapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<UserDto> getTestsDto(){
        return userRepository
                .findAll()
                .stream()
                .map(userAssembler::map)
                .collect(Collectors.toList());
    }

    public User addUser(UserDto userDto){
        return userRepository.save(userAssembler.revers(userDto));
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void updateUser(UserDto userDto){
        userRepository.findById(userDto.getId())
                .ifPresent(user -> {
                    user.setEmail(userDto.getEmail());
                    user.setId(userDto.getId());
                    user.setLogin(userDto.getLogin());
                    user.setPassord(userDto.getPassord());
                    user.setTestList(userDto.getTestList());
                });
    }
}
