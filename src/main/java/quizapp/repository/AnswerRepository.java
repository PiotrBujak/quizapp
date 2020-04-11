package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
