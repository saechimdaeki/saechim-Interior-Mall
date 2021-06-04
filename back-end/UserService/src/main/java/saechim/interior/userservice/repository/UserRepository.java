package saechim.interior.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.userservice.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, CustomUserRepository {
    Optional<UserEntity> findByUserId(String UserId);
    Optional<UserEntity> findByEmail(String email);
}
