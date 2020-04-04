package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
