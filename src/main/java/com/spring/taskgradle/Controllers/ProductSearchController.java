package com.spring.taskgradle.Controllers;

import com.spring.taskgradle.Entity.Product;
import com.spring.taskgradle.Service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    private final ProductSearchService searchService;

    @Autowired
    public ProductSearchController(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        try {
            List<Product> products = searchService.search(query);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(products);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }

}