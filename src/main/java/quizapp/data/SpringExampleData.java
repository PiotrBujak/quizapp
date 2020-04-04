package quizapp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import quizapp.model.Answer;
import quizapp.model.Question;
import quizapp.model.Test;
import quizapp.model.User;
import quizapp.repository.AnswerRepository;
import quizapp.repository.QuestionRepository;
import quizapp.repository.TestRepository;
import quizapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringExampleData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("user1", "password", "email");

        Test test1 = new Test("test1", user);

        Answer answer1 = new Answer("answer1", true);
        Answer answer2 = new Answer("answer2", false);
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer1);
        answerList.add(answer2);

        Question question1 = new Question("contentOfQuestion1", answerList, test1);
        Question question2 = new Question("contentOfQuestion2", answerList, test1);

        test1.addQuestion(question1);
        test1.addQuestion(question2);

        user.addTest(test1);
        userRepository.save(user);
        testRepository.save(test1);
        questionRepository.save(question1);
        questionRepository.save(question2);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
    }
}
