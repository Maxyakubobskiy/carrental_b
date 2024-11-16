package carrental.carrental_b.repository;

import carrental.carrental_b.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
