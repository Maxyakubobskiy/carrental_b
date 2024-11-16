package carrental.carrental_b.repository;

import carrental.carrental_b.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCar_CarId(Long carId);
}
