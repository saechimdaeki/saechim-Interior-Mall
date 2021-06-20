package saechim.interior.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.categoryservice.entity.CategoryReview;

@Repository
public interface CategoryReviewRepository extends JpaRepository<CategoryReview,Long> {
}
