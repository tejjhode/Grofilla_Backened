package Grofila.com.backend.service;

import Grofila.com.backend.dto.CartResponseDTO;
import Grofila.com.backend.model.Cart;
import Grofila.com.backend.model.Product;
import Grofila.com.backend.repository.CartRepository;
import Grofila.com.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addToCart(Cart cart) {
        Product product = productRepository.findById(cart.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        double totalPrice = cart.getQuantity() * product.getPrice();
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }

    public List<CartResponseDTO> getCartItemsByUser(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        return cartItems.stream()
                .map(cart -> {
                    Product product = productRepository.findById(cart.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    return new CartResponseDTO(
                            cart.getCartId(),
                            cart.getProductId(),
                            cart.getQuantity(),
                            cart.getTotalPrice(),
                            product.getImageUrl()
                    );
                })
                .collect(Collectors.toList());
    }

    public Cart updateCartItem(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Product product = productRepository.findById(cart.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.setQuantity(quantity);
        cart.setTotalPrice(quantity * product.getPrice());

        return cartRepository.save(cart);
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    public CartResponseDTO convertToDTO(Cart cart) {
        Product product = productRepository.findById(cart.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new CartResponseDTO(
                cart.getCartId(),
                cart.getProductId(),
                cart.getQuantity(),
                cart.getTotalPrice(),
                product.getImageUrl()
        );
    }
}