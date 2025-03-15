//package Grofila.com.backend.service;
//
//
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LiveTrackingService {
//
//    private final StringRedisTemplate redisTemplate;
//
//    public LiveTrackingService(StringRedisTemplate redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    public void updateLocation(Long orderId, Double latitude, Double longitude) {
//        String key = "order:" + orderId;
//        redisTemplate.opsForValue().set(key, latitude + "," + longitude);
//    }
//
//
//    public String getLocation(Long orderId) {
//        String key = "order:" + orderId;
//        return redisTemplate.opsForValue().get(key);
//    }
//}