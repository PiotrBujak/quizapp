package quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quizapp.assemblers.UserAssembler;
import quizapp.commons.security.CustomUserDetails;
import quizapp.models.User;
import quizapp.models.dtos.UserDto;
import quizapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return Optional.of(userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!")))
                .map(CustomUserDetails::new).get();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<UserDto> getUsersDto(){
        return userRepository
                .findAll()
                .stream()
                .map(userAssembler::map)
                .collect(Collectors.toList());
    }

    public UserDto getUserDtoByName(String userName){
         return userRepository.findUserByLogin(userName)
                 .map(userAssembler::map)
                 .orElseThrow(() -> new UsernameNotFoundException("user not found!"));
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
                    user.setPassword(userDto.getPassword());
                    user.setActive(userDto.getActive());
//                    user.setTestList(userDto.getTestList());
                    user.setRoles(userDto.getRoles());
                });
    }
}
