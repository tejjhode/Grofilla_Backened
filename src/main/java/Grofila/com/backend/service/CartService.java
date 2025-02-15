package Grofila.com.backend.service;

import Grofila.com.backend.model.Cart;
import Grofila.com.backend.model.Product;
import Grofila.com.backend.repository.CartRepository;
import Grofila.com.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addToCart(Cart cart) {
        // Fetch product details to get the price
        Product product = productRepository.findById(cart.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        // Calculate total price
        double totalPrice = cart.getQuantity() * product.getPrice();
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    // **GET CART ITEMS FOR A USER**
    public List<Cart> getCartItemsByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart updateCartItem(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Fetch product details again for price update
        Product product = productRepository.findById(cart.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        // Recalculate total price
        cart.setQuantity(quantity);
        cart.setTotalPrice(quantity * product.getPrice());

        return cartRepository.save(cart);
    }

    // **REMOVE ITEM FROM CART**
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    // **CLEAR CART AFTER CHECKOUT**
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}