package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.model.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
