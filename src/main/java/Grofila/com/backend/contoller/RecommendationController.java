package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Recommendation;
import Grofila.com.backend.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // **GET RECOMMENDATIONS FOR A USER**
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsForUser(userId));
    }

    // **ADD RECOMMENDATION (AI MODEL WILL CALL THIS API)**
    @PostMapping
    public ResponseEntity<Recommendation> addRecommendation(@RequestBody Recommendation recommendation) {
        return ResponseEntity.ok(recommendationService.saveRecommendation(recommendation));
    }


}