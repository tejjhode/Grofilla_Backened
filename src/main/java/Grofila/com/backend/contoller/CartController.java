package Grofila.com.backend.contoller;

import Grofila.com.backend.dto.CartResponseDTO;
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

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.addToCart(cart);
        CartResponseDTO responseDTO = cartService.convertToDTO(savedCart);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartResponseDTO>> getCartItems(@PathVariable Long userId) {
        List<CartResponseDTO> cartItems = cartService.getCartItemsByUser(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartResponseDTO> updateCart(@PathVariable Long cartId, @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCartItem(cartId, quantity);
        CartResponseDTO responseDTO = cartService.convertToDTO(updatedCart);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}