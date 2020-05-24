package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizapp.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
