package com.spring.taskgradle.Service;

import com.spring.taskgradle.Entity.Product;
import com.spring.taskgradle.Entity.Sku;
import com.spring.taskgradle.Repository.ProductRepository;
import com.spring.taskgradle.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SkuRepository skuRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SkuRepository skuRepository) {
        this.productRepository = productRepository;
        this.skuRepository = skuRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addSkuToProduct(Integer productId, Sku sku) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        sku.setProduct(product);
        skuRepository.save(sku);
    }
}
