package Grofila.com.backend.service;

import Grofila.com.backend.model.Product;
import Grofila.com.backend.model.Shopkeeper;
import Grofila.com.backend.repository.ProductRepository;
import Grofila.com.backend.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    // Add a new product
    public Product addProduct(Product product, Long shopkeeperId) {
        Optional<Shopkeeper> shopkeeperOpt = shopkeeperRepository.findById(shopkeeperId);
        if (shopkeeperOpt.isEmpty()) {
            throw new RuntimeException("Shopkeeper not found!");
        }

        product.setShopkeeper(shopkeeperOpt.get());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByShopkeeper(Long shopkeeperId) {
        return productRepository.findByShopkeeperId(shopkeeperId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        product.setCategory(updatedProduct.getCategory());
        product.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found!");
        }
        productRepository.deleteById(id);
    }
}