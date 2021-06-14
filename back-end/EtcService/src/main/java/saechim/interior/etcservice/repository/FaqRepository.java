package saechim.interior.etcservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.etcservice.entity.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}
