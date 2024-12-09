package carrental.carrental_b.services;

import carrental.carrental_b.models.Car;
import carrental.carrental_b.models.Order;
import carrental.carrental_b.models.Payment;
import carrental.carrental_b.repository.CarRepository;
import carrental.carrental_b.repository.OrderRepository;
import carrental.carrental_b.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CarRepository carRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentRepository paymentRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.carRepository = carRepository;
    }
    public LocalDate getAvailablePeriod() {
        try{return orderRepository.getMinStartDate();}
        catch (Exception e){
            return null;
        }
    }
    public List<Map<String, Object>> getStatistics (LocalDate startDate, LocalDate endDate){
        List<Map<String, Object>> results = orderRepository.findRentalsByDay(startDate, endDate);

        // Перетворення TupleBackedMap у звичайний HashMap
        return results.stream()
                .map(HashMap::new)
                .collect(Collectors.toList());
    }

    public Double getTotalAmountByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime startDateTime = dateFrom.atStartOfDay();
        LocalDateTime endDateTime = dateTo.atTime(23, 59, 59);

        List<Payment> payments = paymentRepository.findPaymentsByPeriod(startDateTime, endDateTime).stream().filter(payment -> payment.getOrder().getStatus().equalsIgnoreCase("INACTIVE")).toList();
        return payments.stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public boolean cancelRent(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return false;
        Car car = carRepository.findById(order.getCar().getCarId()).orElse(null);
        if (car == null) return false;
        order.setStatus("INACTIVE");
        orderRepository.save(order);
        car.setAvailable(true);
        carRepository.save(car);
        return true;
    }

    public boolean deleteRent(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return false;
        Car car = carRepository.findById(order.getCar().getCarId()).orElse(null);
        if (car == null) return false;
        car.setAvailable(true);
        carRepository.save(car);
        try{
            orderRepository.delete(order);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
