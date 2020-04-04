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

import javax.management.QueryEval;
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
        User user = new User("user2", "password", "email");

        Test test1 = new Test("test1", user);
        Test test2 = new Test("test2", user);

        Answer answer1 = new Answer("answer1", true);
        Answer answer2 = new Answer("answer2", false);
        Answer answer3 = new Answer("answer3", true);
        Answer answer4 = new Answer("answer4", false);

        Question question = new Question("contentOfQuestion1");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        Question question1 = new Question("contentOfQuestion2");
        question.addAnswer(answer3);
        question.addAnswer(answer4);

        test1.addQuestion(question);
        test1.addQuestion(question1);

        user.addTest(test1);

        userRepository.save(user);
        testRepository.save(test1);
        testRepository.save(test2);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
    }
}
