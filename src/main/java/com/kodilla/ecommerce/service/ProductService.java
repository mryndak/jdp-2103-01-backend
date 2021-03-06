package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.repository.GroupRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final GroupRepository groupRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(final Long id) {
        return productRepository.findById(id)
                .map(productMapper::mapToProductDto);
    }

    @SneakyThrows
    public ProductDto saveProduct(final ProductDto productDto) {
        Product productToSave = productMapper.mapToProduct(productDto);
        Optional<Group> group = groupRepository.findById(productDto.getGroupId());
        if (group.isPresent()) {
            Group productGroup = group.get();
            productToSave.setGroup(productGroup);
        } else {
            throw new NotFoundException("Group doesn't exist, please change the group.");
        }
        productToSave = productRepository.save(productToSave);
        return productMapper.mapToProductDto(productToSave);
    }

    public Optional<ProductDto> updateProduct(final ProductDto productDto) {
        Optional<Product> product = productRepository.findById(productDto.getId());
        if (product.isPresent()) {
            product.ifPresent(p -> {
                p.setName(productDto.getName());
                p.setDescription(productDto.getDescription());
                p.setPrice(productDto.getPrice());
                Optional<Group> group = groupRepository.findById(productDto.getGroupId());
                if (group.isPresent()) {
                    Group productGroup = group.get();
                    p.setGroup(productGroup);
                }
            });
            productRepository.save(product.get());
            return product.map(productMapper::mapToProductDto);
        }
        return Optional.empty();
    }

    public void deleteProductById(final Long id) {
        productRepository.deleteById(id);
    }
}