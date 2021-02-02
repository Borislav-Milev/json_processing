package spring.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.app.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
