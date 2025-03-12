package Grofila.com.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "live_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LiveTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Auto-increment with primary key
    @Column(name = "tracking_id") // ✅ Define explicitly
    private Long trackingId;

    @Column(nullable = false, unique = true) // ✅ Ensure `orderId` is unique
    private Long orderId;

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Column(nullable = false)
    private Long agentId; // Delivery Agent ID

    public Long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Long trackingId) {
        this.trackingId = trackingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String status;  // e.g., "On the way", "Delivered"
}