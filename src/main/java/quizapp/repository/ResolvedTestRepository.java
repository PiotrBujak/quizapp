package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quizapp.models.ResolvedTest;

import java.util.List;

@Repository
public interface ResolvedTestRepository extends JpaRepository<ResolvedTest, Integer> {

    @Query(value = "SELECT t FROM ResolvedTest t where t.testId = ?1")
    List<ResolvedTest> findResolvedTestByTestId(int id);

    @Query(value = "SELECT count(correct) FROM resolved_tests WHERE correct = 1 AND test_id=?1 AND user_id = ?2 GROUP BY user_id",
            nativeQuery = true)
    Integer findScoredPointsByEachUserByTestId(int id, int user_id);
}
