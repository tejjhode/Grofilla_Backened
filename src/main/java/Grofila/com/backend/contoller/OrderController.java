package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Order;
import Grofila.com.backend.model.OrderStatus;
import Grofila.com.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place a new order
    @PostMapping("/place/{customerId}/{shopkeeperId}")
    public Order placeOrder(@PathVariable Long customerId, @PathVariable Long shopkeeperId) {
        return orderService.placeOrder(customerId, shopkeeperId);
    }

    // Get orders for a customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    // Get orders for a shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}")
    public List<Order> getShopkeeperOrders(@PathVariable Long shopkeeperId) {
        return orderService.getShopkeeperOrders(shopkeeperId);
    }

    // Update order status
    @PutMapping("/update/{orderId}/{status}")
    public Order updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    // Cancel an order
    @DeleteMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "Order cancelled successfully!";
    }
}