package Grofila.com.backend.service;

import Grofila.com.backend.model.Shopkeeper;
import Grofila.com.backend.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ShopkeeperService {

    private final ShopkeeperRepository shopkeeperRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ShopkeeperService(ShopkeeperRepository shopkeeperRepository, BCryptPasswordEncoder passwordEncoder) {
        this.shopkeeperRepository = shopkeeperRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register Shopkeeper with encrypted password
    public Shopkeeper registerShopkeeper(Shopkeeper shopkeeper) {
        if (shopkeeperRepository.findByEmail(shopkeeper.getEmail()).isPresent()) {
            throw new RuntimeException("Shopkeeper with this email already exists.");
        }
        shopkeeper.setPassword(passwordEncoder.encode(shopkeeper.getPassword())); // Encrypt Password
        return shopkeeperRepository.save(shopkeeper);
    }

    // ✅ Login Shopkeeper and validate password
    public Shopkeeper loginShopkeeper(String email, String password) {
        Shopkeeper shopkeeper = shopkeeperRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));

        if (!passwordEncoder.matches(password, shopkeeper.getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }

        return shopkeeper;
    }

    // ✅ Fetch Shopkeeper by ID
    public Shopkeeper getShopkeeperById(Long id) {
        return shopkeeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopkeeper not found."));
    }

    // ✅ Update Shopkeeper with password check
    public Shopkeeper updateShopkeeper(Long id, Shopkeeper updatedShopkeeper) {
        return shopkeeperRepository.findById(id).map(shopkeeper -> {
            shopkeeper.setName(updatedShopkeeper.getName());
            shopkeeper.setEmail(updatedShopkeeper.getEmail());

            // Only update password if provided
            if (updatedShopkeeper.getPassword() != null && !updatedShopkeeper.getPassword().isEmpty()) {
                shopkeeper.setPassword(passwordEncoder.encode(updatedShopkeeper.getPassword()));
            }

            shopkeeper.setShopName(updatedShopkeeper.getShopName());
            shopkeeper.setShopAddress(updatedShopkeeper.getShopAddress());
            return shopkeeperRepository.save(shopkeeper);
        }).orElseThrow(() -> new RuntimeException("Shopkeeper not found."));
    }

    // ✅ Delete Shopkeeper by ID
    public void deleteShopkeeper(Long id) {
        if (!shopkeeperRepository.existsById(id)) {
            throw new RuntimeException("Shopkeeper not found.");
        }
        shopkeeperRepository.deleteById(id);
    }
}