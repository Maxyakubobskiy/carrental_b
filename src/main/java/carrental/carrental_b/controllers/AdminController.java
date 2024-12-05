package carrental.carrental_b.controllers;

import carrental.carrental_b.DTO.LoginDto;
import carrental.carrental_b.DTO.StatisticsDto;
import carrental.carrental_b.roles.Role;
import carrental.carrental_b.services.AuthService;
import carrental.carrental_b.services.CarService;
import carrental.carrental_b.services.OrderService;
import carrental.carrental_b.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AuthService authService;
    private final UserService userService;
    private final OrderService orderService;
    private final CarService carService;

    @Autowired
    public AdminController(AuthService authService, UserService userService, OrderService orderService, CarService carService) {
        this.authService = authService;
        this.userService = userService;
        this.orderService = orderService;
        this.carService = carService;
    }

    @PostMapping("/login")
    ResponseEntity<?> adminLogin(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.authenticate(loginDto.getUsername(), loginDto.getPassword(), Role.ROLE_ADMIN.name());
            return ResponseEntity.ok(token);
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/getAdminInfo")
    public ResponseEntity<?> getAdminInfo(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userService.getInfoResponse(principal.getName()));
    }

    @GetMapping("/availablePeriod")
    public ResponseEntity<?> getAvailablePeriod( ){
        LocalDate availableDate = orderService.getAvailablePeriod();
        if (availableDate == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(availableDate);
    }

    @PostMapping("/statistics")
    public ResponseEntity<?> getStatistics(@RequestBody StatisticsDto request) {
        if (request.getDateFrom() == null || request.getDateTo() == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Map<String, Object>> result = orderService.getStatistics(request.getDateFrom(), request.getDateTo());
        if (result == null || result.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/totalAmountPeriod")
    public ResponseEntity<?> getTotalAmountPeriod(@RequestBody StatisticsDto request) {
        if (request.getDateFrom() == null || request.getDateTo() == null) {
            return ResponseEntity.badRequest().build();
        }
        Double totalAmount = orderService.getTotalAmountByPeriod(request.getDateFrom(),request.getDateTo());
        if (totalAmount == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(totalAmount);
    }

    @PutMapping("/car/changeAvailability/{carId}")
    public ResponseEntity<?> changeCarAvailability(@PathVariable Long carId){
        if (carId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = carService.changeCarAvailability(carId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cancelRent/{orderId}")
    public ResponseEntity<?> cancelRent(@PathVariable Long orderId){
        if (orderId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = orderService.cancelRent(orderId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteRent/{orderId}")
    public ResponseEntity<?> deleteRent(@PathVariable Long orderId){
        if (orderId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = orderService.deleteRent(orderId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/lockUser/{userId}")
    public ResponseEntity<?> lockUser(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = userService.blockUser(userId);
        if(result){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/unblockUser/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = userService.unblockUser(userId);
        if(result){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean result = userService.deleteUser(userId);
        if(result){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().build();
    }

}
