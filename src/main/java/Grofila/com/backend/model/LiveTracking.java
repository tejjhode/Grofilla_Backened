//package Grofila.com.backend.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//public class LiveTracking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String status;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public String getCurrentLocation() {
//        return currentLocation;
//    }
//
//    public void setCurrentLocation(String currentLocation) {
//        this.currentLocation = currentLocation;
//    }
//
//    public String getEstimatedDeliveryTime() {
//        return estimatedDeliveryTime;
//    }
//
//    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
//        this.estimatedDeliveryTime = estimatedDeliveryTime;
//    }
//
//    @OneToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    private String currentLocation;
//    private String estimatedDeliveryTime;
//}