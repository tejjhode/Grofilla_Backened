package Grofila.com.backend.config;

import Grofila.com.backend.model.LiveTracking;
import Grofila.com.backend.repository.LiveTrackingRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class LiveTrackingWebSocket {

    private final LiveTrackingRepository liveTrackingRepository;

    public LiveTrackingWebSocket(LiveTrackingRepository liveTrackingRepository) {
        this.liveTrackingRepository = liveTrackingRepository;
    }

    // 📡 **Receive updates from the delivery agent and broadcast**
    @MessageMapping("/update-location") // Received from agent
    @SendTo("/topic/order-tracking")    // Sent to customers
    public LiveTracking updateTracking(LiveTracking tracking) {
        liveTrackingRepository.save(tracking); // Save location
        return tracking; // Broadcast update to frontend
    }
}