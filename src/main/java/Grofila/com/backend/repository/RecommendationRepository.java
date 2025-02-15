package Grofila.com.backend.repository;

import Grofila.com.backend.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByCustomerId(Long customerId);
}