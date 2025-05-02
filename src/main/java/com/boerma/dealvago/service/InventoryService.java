package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.domain.entity.Product;
import com.boerma.dealvago.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDto productDto) {
        logger.info("Adding new product: {}", productDto);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setStock(productDto.getStock());
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        logger.info("Fetching all products — no search term provided");
        List<Product> products = productRepository.findAll();
        return convertToProductDtos(products);
    }

    public Optional<Product> getProduct(String productName) {
        return productRepository.findByName(productName);
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

    public void addProductStock(int productId, int quantity) {
        productRepository.findById(productId).ifPresentOrElse(product -> {
            int addedStock = product.getStock() + quantity;
            product.setStock(addedStock);
            productRepository.save(product);
            logger.info("Updated stock for product ID {}: added {} to stock", productId, quantity);
        }, () -> {
            logger.warn("Product with ID {} not found", productId);
        });
    }

    public void removeProductStock(List<OrderlineDto> soldProducts) {
        for (OrderlineDto dto : soldProducts) {
            productRepository.findById(dto.getProductDto().getId()).ifPresent(product -> {
                int updatedStock = product.getStock() - dto.getQuantity();
                product.setStock(updatedStock);
                productRepository.save(product);
            });
        }
    }
}
