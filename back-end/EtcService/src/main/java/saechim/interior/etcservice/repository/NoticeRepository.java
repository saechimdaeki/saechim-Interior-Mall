package saechim.interior.etcservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.etcservice.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
