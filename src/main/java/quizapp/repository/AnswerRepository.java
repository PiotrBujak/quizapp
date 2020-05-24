package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizapp.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
