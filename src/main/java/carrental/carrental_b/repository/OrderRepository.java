package carrental.carrental_b.repository;

import carrental.carrental_b.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT MIN(o.startDate) FROM Order o")
    LocalDate getMinStartDate();

    @Query(value = "SELECT start_date AS day, COUNT(*) AS rental_count " +
            "FROM orders " +
            "WHERE start_date BETWEEN :startDate AND :endDate " +
            "AND status = 'INACTIVE' " +
            "GROUP BY start_date " +
            "ORDER BY start_date", nativeQuery = true)
    List<Map<String,Object>> findRentalsByDay(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
