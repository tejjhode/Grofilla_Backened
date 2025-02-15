package Grofila.com.backend.repository;

import Grofila.com.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);  // Change UUID to Long
    List<Review> findByCustomerId(Long customerId);
}