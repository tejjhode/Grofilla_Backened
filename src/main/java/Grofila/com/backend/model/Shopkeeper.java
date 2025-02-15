package Grofila.com.backend.model;

import Grofila.com.backend.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopkeepers")
public class Shopkeeper extends User {

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String shopAddress;

    // Constructors, Getters, and Setters
}