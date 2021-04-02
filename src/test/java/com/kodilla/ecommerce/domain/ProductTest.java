package com.kodilla.ecommerce.domain;


import com.kodilla.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void SaveProduct() {
        //Given
        Product productTest = new Product();

        productTest.setName("Save product test");
        productTest.setPrice(new BigDecimal(16));
        productTest.setDescription("Test Description");

        //When
        productRepository.save(productTest);


        //Then
        Long productTestId= productTest.getId();
        Optional<Product> readProduct = productRepository.findById(productTestId);
        assertTrue(readProduct.isPresent());
        //CleanUp
        productRepository.deleteById(productTestId);
    }

    @Test
    public void updateProduct() {
        //Given
        Product productTest = new Product();

        productTest.setName("Test Product Update");
        productTest.setPrice(new BigDecimal(16));
        productTest.setDescription("Test Description");

        productRepository.save(productTest);

        //When
        productTest.setName("Update");
        productRepository.save(productTest);

        //Then
        Long productTestId = productTest.getId();
        Product readProduct = productRepository.findById(productTestId).orElse(null);
        assertEquals(readProduct.getName(),"Update");

        //CleanUp
        productRepository.deleteById(productTestId);

    }

    @Test
    public void deleteProduct() {
        //Given
        Product productTest = new Product();

        productTest.setName("Test Product Delete");
        productTest.setPrice(new BigDecimal(16));
        productTest.setDescription("Test Description");

        productRepository.save(productTest);

        //When
        Long productTestId = productTest.getId();
        productRepository.deleteById(productTestId);
        Optional<Product> readProduct = productRepository.findById(productTestId);

        //Then
        assertFalse(readProduct.isPresent());
    }
}
