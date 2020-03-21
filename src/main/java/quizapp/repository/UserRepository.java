package quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
