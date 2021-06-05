package saechim.interior.storyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.storyservice.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
