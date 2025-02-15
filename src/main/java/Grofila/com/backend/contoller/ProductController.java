package Grofila.com.backend.controller;

import Grofila.com.backend.model.Product;
import Grofila.com.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a new product (shopkeeper must be logged in)
    @PostMapping("/add/{shopkeeperId}")
    public Product addProduct(@RequestBody Product product, @PathVariable Long shopkeeperId) {
        return productService.addProduct(product, shopkeeperId);
    }

    // Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get all products by a specific shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}")
    public List<Product> getProductsByShopkeeper(@PathVariable Long shopkeeperId) {
        return productService.getProductsByShopkeeper(shopkeeperId);
    }

    // Get a single product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Update product details
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete a product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}