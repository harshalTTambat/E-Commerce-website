package com.example.ECommerce_website.model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    private String name;


}
