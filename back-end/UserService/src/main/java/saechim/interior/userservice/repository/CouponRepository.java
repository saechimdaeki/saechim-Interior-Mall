package saechim.interior.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.userservice.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
