package saechim.interior.etcservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.etcservice.entity.ConstructQuest;

@Repository
public interface ConstructQuestRepository extends JpaRepository<ConstructQuest,Long>,CustomConstructQuest {
}
