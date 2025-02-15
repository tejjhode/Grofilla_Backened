package Grofila.com.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Recommendation {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<String> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<String> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    private List<String> recommendedProducts;
}