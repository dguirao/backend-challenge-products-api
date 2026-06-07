package com.challenge.products_api.service;

import com.challenge.products_api.domain.Product;
import com.challenge.products_api.infraestructure.client.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductClient productClient;

    @Override
    public List<Product> getSimilarProducts(String productId){
        if (productId == null || productId.isEmpty()){
            return List.of();
        }
        List<String> productIds;
        try {
            productIds = productClient.getSimilarIds(productId);
        } catch (Exception e) {
            log.warn("Error retrieving similar ids for product {}", productId, e);
            return List.of();
        }
        return productIds.parallelStream()
                .map(this::getProductById)
                .filter(Objects::nonNull)
                .toList();
    }

    private Product getProductById(String productId){
        try {
            return productClient.getProductById(productId);
        } catch (Exception e){
            log.warn("Error retrieving product {}", productId, e);
            return null;
        }

    }
}
