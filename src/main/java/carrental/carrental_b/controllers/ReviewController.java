package carrental.carrental_b.controllers;

import carrental.carrental_b.DTO.ReviewDto;
import carrental.carrental_b.DTO.ReviewNewDto;
import carrental.carrental_b.models.User;
import carrental.carrental_b.services.ReviewService;
import carrental.carrental_b.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }


    @PostMapping("/submitReview/{carId}")
    public ResponseEntity<?> submitReview(
            @RequestBody ReviewDto reviewDto,
            @PathVariable Long carId,
            Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userService.getUserInfo(principal.getName());
        if (reviewService.addReview(carId, reviewDto, user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/new-reviews/{carId}")
    public ResponseEntity<?> getNewReviews(@PathVariable Long carId) {
        List<ReviewNewDto> reviews = reviewService.getReviews(carId);

        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(reviews);
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userService.getUserInfo(principal.getName());
        if (reviewService.deleteReview(reviewId,user))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
