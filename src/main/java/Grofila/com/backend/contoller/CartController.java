package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Cart;
import Grofila.com.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // **ADD ITEM TO CART**
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.addToCart(cart));
    }

    // **GET CART ITEMS FOR A USER**
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItemsByUser(userId));
    }

    // **UPDATE CART ITEM (QUANTITY & PRICE)**
    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateCartItem(cartId, quantity));
    }

    // **REMOVE ITEM FROM CART**
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.ok().build();
    }

    // **CLEAR CART AFTER CHECKOUT**
    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}