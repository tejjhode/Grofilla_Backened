package Grofila.com.backend.repository;

import Grofila.com.backend.model.LiveTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiveTrackingRepository extends JpaRepository<LiveTracking, Long> {
    Optional<LiveTracking> findByOrderId(Long orderId);
}