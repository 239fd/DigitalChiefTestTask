package com.spring.taskgradle.Controllers;

import com.spring.taskgradle.Entity.Product;
import com.spring.taskgradle.Entity.Sku;
import com.spring.taskgradle.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{productId}/skus")
    public ResponseEntity<String> addSkuToProduct(@PathVariable Integer productId, @RequestBody Sku sku) {
        productService.addSkuToProduct(productId, sku);
        return ResponseEntity.status(HttpStatus.CREATED).body("SKU added successfully");
    }
}
