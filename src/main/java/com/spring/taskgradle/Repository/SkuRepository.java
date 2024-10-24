package com.spring.taskgradle.Repository;

import com.spring.taskgradle.Entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<Sku, Integer> {
}
