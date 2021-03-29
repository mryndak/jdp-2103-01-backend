package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @GetMapping
    public List<ProductDto> getProducts() {
        /**
         * Dane pochodzą z pliku: specs/product.spec.json
         */
        return Arrays.asList(
                ProductDto.builder()
                        .id(1L)
                        .name("kurtka zimowa")
                        .description("")
                        .price(new BigDecimal(100))
                        .groupId(1L)
                        .build()
        );
    }

    // TODO: Pozostałe metody z zadania
}
