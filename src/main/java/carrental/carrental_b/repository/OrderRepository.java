package carrental.carrental_b.repository;

import carrental.carrental_b.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
