package com.challenge.products_api.infraestructure.client;

import com.challenge.products_api.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder, @Value("${products.api.url.base}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public List<String> getSimilarIds(String productId) {
        return restClient.get()
                .uri("/product/{productId}/similarids", productId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Product getProductById(String productId) {
        return restClient.get()
                .uri("product/{productId}", productId)
                .retrieve()
                .body(Product.class);
    }

}
