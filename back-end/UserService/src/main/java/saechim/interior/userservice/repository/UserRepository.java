package saechim.interior.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, CustomUserRepository {
}
