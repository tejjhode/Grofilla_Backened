package Grofila.com.backend.contoller;

import Grofila.com.backend.model.LiveTracking;
import Grofila.com.backend.repository.LiveTrackingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tracking")
public class LiveTrackingController {

    private final LiveTrackingRepository trackingRepository;

    public LiveTrackingController(LiveTrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<LiveTracking> getOrderTracking(@PathVariable Long orderId) {
        Optional<LiveTracking> tracking = trackingRepository.findByOrderId(orderId);
        return tracking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<LiveTracking> updateTracking(@RequestBody LiveTracking tracking) {
        if (tracking.getOrderId() == null || tracking.getAgentId() == null) {
            return ResponseEntity.badRequest().body(null); 
        }
        LiveTracking savedTracking = trackingRepository.save(tracking);
        return ResponseEntity.ok(savedTracking);
    }
}