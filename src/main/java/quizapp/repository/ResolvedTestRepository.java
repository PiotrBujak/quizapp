package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizapp.models.ResolvedTest;

public interface ResolvedTestRepository extends JpaRepository<ResolvedTest, Integer> {
}
