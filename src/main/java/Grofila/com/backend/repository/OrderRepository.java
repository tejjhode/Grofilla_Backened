package Grofila.com.backend.repository;

import Grofila.com.backend.model.Order;
import Grofila.com.backend.model.Customer;
import Grofila.com.backend.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByShopkeeper(Shopkeeper shopkeeper);
}