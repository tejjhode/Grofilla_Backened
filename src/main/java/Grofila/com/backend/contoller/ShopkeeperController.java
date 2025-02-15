package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Shopkeeper;
import Grofila.com.backend.service.ShopkeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/shopkeepers")
public class ShopkeeperController {

    @Autowired
    private ShopkeeperService shopkeeperService;

    @PostMapping("/register")
    public Shopkeeper registerShopkeeper(@RequestBody Shopkeeper shopkeeper) {
        return shopkeeperService.registerShopkeeper(shopkeeper);
    }

    @PostMapping("/login")
    public Optional<Shopkeeper> loginShopkeeper(@RequestBody Shopkeeper shopkeeper) {
        return Optional.ofNullable(shopkeeperService.loginShopkeeper(shopkeeper.getEmail(), shopkeeper.getPassword()));
    }

    @GetMapping("/{id}")
    public Optional<Shopkeeper> getShopkeeper(@PathVariable Long id) {
        return Optional.ofNullable(shopkeeperService.getShopkeeperById(id));
    }

    @PutMapping("/{id}")
    public Shopkeeper updateShopkeeper(@PathVariable Long id, @RequestBody Shopkeeper updatedShopkeeper) {
        return shopkeeperService.updateShopkeeper(id, updatedShopkeeper);
    }

    @DeleteMapping("/{id}")
    public String deleteShopkeeper(@PathVariable Long id) {
        shopkeeperService.deleteShopkeeper(id);
        return "Shopkeeper deleted successfully!";
    }
}