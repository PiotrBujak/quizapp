package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizapp.models.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
}
