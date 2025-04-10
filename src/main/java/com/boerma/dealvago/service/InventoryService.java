package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.domain.entity.Product;
import com.boerma.dealvago.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        logger.info("Fetching all products — no search term provided");
        List<Product> products = productRepository.findAll();
        return convertToProductDtos(products);
    }

    public List<ProductDto> getProducts(String searchString) {
        logger.info("Searching products with keyword '{}'", searchString);
        List<Product> products;
        if (searchString != null && !searchString.isBlank()) {
            products = productRepository.findByNameContainingIgnoreCase(searchString);
        } else {
            products = productRepository.findAll();
        }
        return convertToProductDtos(products);
    }

    private List<ProductDto> convertToProductDtos(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getUnitPrice(),
                    product.getStock()
            ));
        }
        return productDtos;
    }

}
