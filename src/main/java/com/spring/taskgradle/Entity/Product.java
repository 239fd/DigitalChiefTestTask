package com.spring.taskgradle.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;

    private boolean active;

    @Column(name = "start_date")
    private Date startDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Sku> skuList;

}
