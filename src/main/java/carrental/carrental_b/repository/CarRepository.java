package carrental.carrental_b.repository;

import carrental.carrental_b.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
