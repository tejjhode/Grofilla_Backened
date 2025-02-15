//package Grofila.com.backend.service;
//
//import Grofila.com.backend.model.LiveTracking;
//import Grofila.com.backend.repository.LiveTrackingRepository;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class LiveTrackingService {
//
//
//        private final StringRedisTemplate redisTemplate;
//
//        public LiveTrackingService(StringRedisTemplate redisTemplate) {
//            this.redisTemplate = redisTemplate;
//        }
//
//        public void updateOrderLocation(Long orderId, String location) {
//            redisTemplate.opsForValue().set("order:" + orderId, location);
//        }
//
//        public String getOrderLocation(Long orderId) {
//            return redisTemplate.opsForValue().get("order:" + orderId);
//        }
//
//}
//
