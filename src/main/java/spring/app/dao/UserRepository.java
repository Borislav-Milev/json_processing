package spring.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.app.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
}
