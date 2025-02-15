package Grofila.com.backend.repository;

import Grofila.com.backend.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {
    Optional<Shopkeeper> findByEmail(String email);
    Optional<Shopkeeper> findById(Long id);
}

