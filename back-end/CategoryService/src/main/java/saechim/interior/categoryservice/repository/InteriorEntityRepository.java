package saechim.interior.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.categoryservice.entity.InteriorEntity;

import java.util.Optional;

@Repository
public interface InteriorEntityRepository extends JpaRepository<InteriorEntity,Long> {
    Optional<InteriorEntity> findByInteriorId(String interiorId);
}
