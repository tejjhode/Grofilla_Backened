package Grofila.com.backend.dto;

import Grofila.com.backend.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDTO {
    private Long orderId;
    private String status;
    private Long customerId;



    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = BigDecimal.valueOf(totalAmount);
    }

    private Long shopkeeperId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;

    public OrderResponseDTO(Long orderId, OrderStatus status, Long customerId, Long shopkeeperId, LocalDateTime orderDate, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.status = String.valueOf(status);
        this.customerId = customerId;
        this.shopkeeperId = shopkeeperId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
}