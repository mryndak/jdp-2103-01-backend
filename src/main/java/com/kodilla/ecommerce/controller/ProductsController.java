package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "This is Products Controller - Here we can do operations related to product")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "This lists our current products", response = List.class)
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This allows us to get product by an id", response = ProductDto.class)
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " doesn't exist."));
    }

    @PostMapping
    @ApiOperation(value = "This will create a brand new Product", response = ProductDto.class)
    public ProductDto createProduct(@RequestBody final ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping
    @ApiOperation(value = "This allows us to update our product", response = ProductDto.class)
    public ProductDto updateProduct(@RequestBody final ProductDto productDto) {
        return productService.updateProduct(productDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This allows us to delete our product by providing id")
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProductById(id);
    }
}
