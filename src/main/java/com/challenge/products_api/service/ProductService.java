package com.challenge.products_api.service;

import com.challenge.products_api.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getSimilarProducts(String productId);
}
