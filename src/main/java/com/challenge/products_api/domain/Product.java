package com.challenge.products_api.domain;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}
