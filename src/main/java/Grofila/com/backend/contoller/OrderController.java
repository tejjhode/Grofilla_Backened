package Grofila.com.backend.controller;

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

    // ✅ Place a new order
    @PostMapping("/place/{customerId}/{shopkeeperId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long customerId, @PathVariable Long shopkeeperId) {
        try {
            OrderResponseDTO order = orderService.placeOrder(customerId, shopkeeperId);
            return ResponseEntity.status(201).body(order); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }

    // ✅ Get orders for a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable Long customerId) {
        List<OrderResponseDTO> orders = orderService.getCustomerOrders(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(orders);
    }

    // ✅ Get orders for a shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}")
    public ResponseEntity<?> getShopkeeperOrders(@PathVariable Long shopkeeperId) {
        List<OrderResponseDTO> orders = orderService.getShopkeeperOrders(shopkeeperId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(orders);
    }

    // ✅ Update order status
    @PutMapping("/update/{orderId}/{status}")
    public Order updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase()); // Convert String to Enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    // ✅ Cancel an order
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order cancelled successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }
}