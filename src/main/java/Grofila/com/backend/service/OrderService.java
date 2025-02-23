package Grofila.com.backend.service;

import Grofila.com.backend.dto.OrderResponseDTO;
import Grofila.com.backend.model.*;
import Grofila.com.backend.repository.OrderRepository;
import Grofila.com.backend.repository.CartRepository;
import Grofila.com.backend.repository.CustomerRepository;
import Grofila.com.backend.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    // Place a new order
    public OrderResponseDTO placeOrder(Long customerId, Long shopkeeperId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        Shopkeeper shopkeeper = shopkeeperRepository.findById(shopkeeperId)
                .orElseThrow(() -> new RuntimeException("Shopkeeper not found!"));

        List<Cart> cartItems = cartRepository.findByUserId(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        // Calculate total amount
        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getTotalPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setCustomer(customer);
        order.setShopkeeper(shopkeeper);
        order.setOrderItems(cartItems);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.valueOf(totalAmount));

        Order savedOrder = orderRepository.save(order);

        // Link cart items to the order
        cartItems.forEach(item -> {
            item.setOrder(savedOrder);
            cartRepository.save(item);
        });

        return convertToDTO(savedOrder);
    }

    // Get orders for a customer
    public List<OrderResponseDTO> getCustomerOrders(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        List<Order> orders = orderRepository.findByCustomer(customer);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get orders for a shopkeeper
    public List<OrderResponseDTO> getShopkeeperOrders(Long shopkeeperId) {
        Shopkeeper shopkeeper = shopkeeperRepository.findById(shopkeeperId)
                .orElseThrow(() -> new RuntimeException("Shopkeeper not found!"));
        List<Order> orders = orderRepository.findByShopkeeper(shopkeeper);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Update order status
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        order.setStatus(status); // ⚠️ Issue likely related here

        return orderRepository.save(order);
    }

    // Cancel an order
    public void cancelOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found!");
        }
        orderRepository.deleteById(orderId);
    }

    // Convert Order object to OrderResponseDTO
    private OrderResponseDTO convertToDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getStatus(),
                order.getCustomer().getId(),
                order.getShopkeeper().getId(),
                order.getOrderDate(),
                order.getTotalAmount()
        );
    }
}