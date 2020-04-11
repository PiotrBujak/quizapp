package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.models.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
