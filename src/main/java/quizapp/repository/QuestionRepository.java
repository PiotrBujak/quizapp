package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
