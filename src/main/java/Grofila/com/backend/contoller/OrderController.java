package Grofila.com.backend.contoller;

import Grofila.com.backend.dto.OrderResponseDTO;
import Grofila.com.backend.model.Order;
import Grofila.com.backend.model.OrderStatus;
import Grofila.com.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{customerId}/{shopkeeperId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long customerId, @PathVariable Long shopkeeperId) {
        try {
            OrderResponseDTO order = orderService.placeOrder(customerId, shopkeeperId);
            return ResponseEntity.status(200).body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable Long customerId) {
        List<OrderResponseDTO> orders = orderService.getCustomerOrders(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/shopkeeper/{shopkeeperId}")
    public ResponseEntity<?> getShopkeeperOrders(@PathVariable Long shopkeeperId) {
        List<OrderResponseDTO> orders = orderService.getShopkeeperOrders(shopkeeperId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/update/{orderId}/{status}")
    public Order updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order cancelled successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}