package Grofila.com.backend.service;

import Grofila.com.backend.model.*;
import Grofila.com.backend.repository.OrderRepository;
import Grofila.com.backend.repository.CartRepository;
import Grofila.com.backend.repository.CustomerRepository;
import Grofila.com.backend.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Order placeOrder(Long customerId, Long shopkeeperId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Shopkeeper> shopkeeperOpt = shopkeeperRepository.findById(shopkeeperId);

        if (customerOpt.isEmpty() || shopkeeperOpt.isEmpty()) {
            throw new RuntimeException("Customer or Shopkeeper not found!");
        }

        Customer customer = customerOpt.get();
        Shopkeeper shopkeeper = shopkeeperOpt.get();

        // Fetch all cart items for the user
        List<Cart> cartItems = cartRepository.findByUserId(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        // Create new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setShopkeeper(shopkeeper);
        order.setOrderItems(cartItems);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        order.calculateTotalAmount();

        // Save order and update cart items to link with the order
        Order savedOrder = orderRepository.save(order);

        for (Cart item : cartItems) {
            item.setOrder(savedOrder);
            cartRepository.save(item);
        }

        return savedOrder;
    }

    // Get orders for a customer
    public List<Order> getCustomerOrders(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found!");
        }
        return orderRepository.findByCustomer(customerOpt.get());
    }

    // Get orders for a shopkeeper
    public List<Order> getShopkeeperOrders(Long shopkeeperId) {
        Optional<Shopkeeper> shopkeeperOpt = shopkeeperRepository.findById(shopkeeperId);
        if (shopkeeperOpt.isEmpty()) {
            throw new RuntimeException("Shopkeeper not found!");
        }
        return orderRepository.findByShopkeeper(shopkeeperOpt.get());
    }

    // Update order status
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    // Cancel an order
    public void cancelOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found!");
        }
        orderRepository.deleteById(orderId);
    }
}