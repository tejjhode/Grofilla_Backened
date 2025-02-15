package Grofila.com.backend.service;

import Grofila.com.backend.model.Review;
import Grofila.com.backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(updatedReview.getRating());
            review.setComment(updatedReview.getComment());
            return reviewRepository.save(review);
        }).orElse(null);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}