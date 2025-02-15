package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Review;
import Grofila.com.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getReviewsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Review review = reviewService.updateReview(id, updatedReview);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id)
                ? ResponseEntity.ok("Review deleted successfully.")
                : ResponseEntity.notFound().build();
    }
}