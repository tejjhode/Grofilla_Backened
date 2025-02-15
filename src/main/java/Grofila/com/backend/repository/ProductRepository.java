package Grofila.com.backend.repository;

import Grofila.com.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByShopkeeperId(Long shopkeeperId);
}