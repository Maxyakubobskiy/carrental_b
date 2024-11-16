package carrental.carrental_b.services;

import carrental.carrental_b.DTO.CarDto;
import carrental.carrental_b.DTO.RentDto;
import carrental.carrental_b.models.*;
import carrental.carrental_b.repository.CarRepository;
import carrental.carrental_b.repository.CardRepository;
import carrental.carrental_b.repository.OrderRepository;
import carrental.carrental_b.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import carrental.carrental_b.filterCriteria.FilterCriteria;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CardRepository cardRepository;
    private final ReviewService reviewService;

    @Autowired
    public CarService(CarRepository carRepository, OrderRepository orderRepository, PaymentRepository paymentRepository, CardRepository cardRepository, ReviewService reviewService) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.cardRepository = cardRepository;
        this.reviewService = reviewService;
    }

    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .sorted(Comparator.comparing(car -> !car.isAvailable()))
                .collect(Collectors.toList());
    }

    public List<Car> filterCars(FilterCriteria filterCriteria) {
        return getAllCars().stream()
                .filter(car -> filterByFuelType(car, filterCriteria.getFuelTypes()))
                .filter(car -> filterByCarClass(car, filterCriteria.getCarClasses()))
                .filter(car -> filterByBrand(car, filterCriteria.getMarks()))
                .filter(car -> filterByYear(car, filterCriteria.getYearFrom(), filterCriteria.getYearTo()))
                .collect(Collectors.toList());
    }

    private boolean filterByFuelType(Car car, List<String> fuelTypes) {
        return fuelTypes == null || fuelTypes.isEmpty() || fuelTypes.contains(car.getFuelType());
    }

    private boolean filterByCarClass(Car car, List<String> carClasses) {
        return carClasses == null || carClasses.isEmpty() || carClasses.contains(car.getCarClass());
    }

    private boolean filterByBrand(Car car, List<String> brands) {
        return brands == null || brands.isEmpty() || brands.contains(car.getBrand());
    }

    private boolean filterByYear(Car car, int yearFrom, int yearTo) {
        return (yearFrom == 0 || car.getYear() >= yearFrom) &&
                (yearTo == 0 || car.getYear() <= yearTo);
    }

    public CarDto getCarById(Long carId) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car == null) {
            return null;
        }

        return new CarDto(
                car.getCarId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getBodyType(),
                car.getTransmissionType(),
                car.getFuelType(),
                car.getEngineCapacity(),
                car.getHorsePower(),
                car.isAvailable(),
                car.getPrice(),
                car.getCarClass(),
                car.getLogoPhotoUrl(),
                reviewService.getReviews(carId),
                car.getPhotos()
        );
    }

    public boolean rentCar(RentDto rentDto, User user) {
        Car car = carRepository.findById(rentDto.getCarId()).orElse(null);
        if (car == null || !car.isAvailable()) {
            return false;
        }

        car.setAvailable(false);

        Order order = new Order(rentDto.getRentFrom(), rentDto.getRentTo(), "ACTIVE", user, car);

        Payment payment = createPayment(rentDto, order);
        try {
            orderRepository.save(order);
            paymentRepository.save(payment);
            carRepository.save(car);
        }catch (Exception e) {
            return false;
        }

        return true;
    }
    private Payment createPayment(RentDto rentDto, Order order) {
        Payment payment;
        if ("card".equalsIgnoreCase(rentDto.getPaymentData().getMethod())) {
            payment = createCardPayment(rentDto, order);
        } else {
            payment = createCashPayment(rentDto, order);
        }
        return payment;
    }

    private Payment createCardPayment(RentDto rentDto, Order order) {
        Card card = new Card(
                rentDto.getPaymentData().getCardNumber(),
                rentDto.getPaymentData().getExpiryDate(),
                rentDto.getPaymentData().getCvv()
        );
        cardRepository.save(card);

        return new Payment(
                order,
                card,
                rentDto.getPaymentData().getMethod(),
                rentDto.getRentAmount(),
                LocalDateTime.now(),
                true
        );
    }

    private Payment createCashPayment(RentDto rentDto, Order order) {
        return new Payment(
                order,
                null,
                rentDto.getPaymentData().getMethod(),
                rentDto.getRentAmount(),
                LocalDateTime.now(),
                true
        );
    }

}
