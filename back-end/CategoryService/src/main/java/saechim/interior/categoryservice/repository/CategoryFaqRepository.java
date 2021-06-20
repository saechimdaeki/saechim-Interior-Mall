package saechim.interior.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.categoryservice.entity.CategoryFaq;

@Repository
public interface CategoryFaqRepository extends JpaRepository<CategoryFaq,Long> {
}
