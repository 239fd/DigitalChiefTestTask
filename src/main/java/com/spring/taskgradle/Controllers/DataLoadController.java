package com.spring.taskgradle.Controllers;

import com.spring.taskgradle.Service.ProductIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/load")
public class DataLoadController {

    private final ProductIndexService dataLoadService;

    @Autowired
    public DataLoadController(ProductIndexService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @GetMapping("/active")
    public ResponseEntity<String> loadActiveProducts() {
        try {
            dataLoadService.recreateProductIndex();
            dataLoadService.loadActiveProducts();
            return ResponseEntity.ok("Active products have been uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with loading active products");
        }
    }

    @GetMapping("/afterStartDate")
    public ResponseEntity<String> loadProductsAfterStartDate(@RequestParam String startDate) {
        try {
            dataLoadService.recreateProductIndex();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            Date parsedDate = formatter.parse(startDate);
            dataLoadService.loadProductsAfterDate(parsedDate);
            return ResponseEntity.ok("Products after " + startDate + " have been loaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with loading");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        }
    }
}
