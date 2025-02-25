package Grofila.com.backend.service;

import Grofila.com.backend.model.Recommendation;
import Grofila.com.backend.repository.RecommendationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendationService {
    private final String AI_API_URL = "https://shfu89p5wc.execute-api.ap-south-1.amazonaws.com/prod";
    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<Recommendation> getRecommendationsForUser(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(AI_API_URL + userId, List.class);
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}