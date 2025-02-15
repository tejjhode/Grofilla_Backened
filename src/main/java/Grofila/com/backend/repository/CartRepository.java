package Grofila.com.backend.repository;

import Grofila.com.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    List<Cart> findByOrderId(Long orderId);
    void deleteByUserId(Long userId);
}