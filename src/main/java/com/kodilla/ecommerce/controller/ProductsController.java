package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/products", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " doesn't exist."));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody final ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody final ProductDto productDto) {
        return productService.updateProduct(productDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProductById(id);
    }
}