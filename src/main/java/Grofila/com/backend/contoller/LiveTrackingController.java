//package Grofila.com.backend.contoller;
//
//import Grofila.com.backend.model.LiveTracking;
//import Grofila.com.backend.service.LiveTrackingService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/live-tracking")
//public class LiveTrackingController {
//
//    private final LiveTrackingService liveTrackingService;
//
//    public LiveTrackingController(LiveTrackingService liveTrackingService) {
//        this.liveTrackingService = liveTrackingService;
//    }
//
//    // **UPDATE ORDER LOCATION**
//    @PutMapping("/{orderId}/update")
//    public ResponseEntity updateTracking(@PathVariable Long orderId, @RequestParam String location) {
//        return ResponseEntity.ok();
//    }
//
//    // **GET LIVE TRACKING STATUS**
//    @GetMapping("/{orderId}")
//    public ResponseEntity<LiveTracking> getTracking(@PathVariable Long orderId) {
//        return liveTrackingService.getTrackingInfo(orderId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//}