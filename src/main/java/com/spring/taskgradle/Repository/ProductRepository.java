package com.spring.taskgradle.Repository;

import com.spring.taskgradle.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByStartDateAfter(Date startDate);
    List<Product> findByActiveTrue();

}
