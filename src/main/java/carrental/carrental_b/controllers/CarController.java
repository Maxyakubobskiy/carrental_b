package carrental.carrental_b.controllers;

import carrental.carrental_b.DTO.CarDto;
import carrental.carrental_b.DTO.CarNoAvailDto;
import carrental.carrental_b.DTO.RentDto;
import carrental.carrental_b.filterCriteria.FilterCriteria;
import carrental.carrental_b.models.Car;
import carrental.carrental_b.models.User;
import carrental.carrental_b.services.CarService;
import carrental.carrental_b.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;
    private final UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/list-all-cars")
    public ResponseEntity<?>getAllCars() {
        List<Car> cars = carService.getAllCars();
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/list-noavail-cars")
    public ResponseEntity<?>getNoAvailCars() {
        List<CarNoAvailDto> cars = carService.getNoAvailCars();
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    @PostMapping("/filterCars")
    public ResponseEntity<?> filterCars(@RequestBody FilterCriteria criteria) {
        List<Car> cars = carService.filterCars(criteria);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(cars);
        }
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable Long carId) {
        CarDto car = carService.getCarById(carId);
        if (car == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentCar(@RequestBody RentDto rentDto, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (rentDto == null) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.getUserInfo(principal.getName());
        if (carService.rentCar(rentDto, user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
