package saechim.interior.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saechim.interior.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
