package carrental.carrental_b.services;

import carrental.carrental_b.DTO.ReviewDto;
import carrental.carrental_b.DTO.ReviewNewDto;
import carrental.carrental_b.models.Car;
import carrental.carrental_b.models.Review;
import carrental.carrental_b.models.User;
import carrental.carrental_b.repository.CarRepository;
import carrental.carrental_b.repository.ReviewRepository;
import carrental.carrental_b.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CarRepository carRepository) {
        this.reviewRepository = reviewRepository;
        this.carRepository = carRepository;
    }

    public boolean addReview(Long carId, ReviewDto reviewDto, User user) {
        try {
            Car car = carRepository.findById(carId).orElse(null);
            if (car == null) {
                return false;
            }
            Review review = new Review(
                    reviewDto.getRating(),
                    reviewDto.getComment(),
                    car,
                    user
            );

            reviewRepository.save(review);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public List<ReviewNewDto> getReviews(Long carId) {
        return reviewRepository.findByCar_CarId(carId).stream()
                .map(review -> new ReviewNewDto(
                        review.getReviewId(),
                        review.getRating(),
                        review.getComment(),
                        review.getUser().getUsername(),
                        review.getUser().getUserId()
                ))
                .collect(Collectors.toList());
    }

    public boolean deleteReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false;
        }
        if (user.getRole().equals(Role.ROLE_USER)) {
            if (!review.getUser().getUserId().equals(user.getUserId())) {
                return false;
            }
        }
        try {
            reviewRepository.delete(review);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
