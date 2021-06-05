package saechim.interior.storyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.storyservice.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>,CustomPostRepository {
}
