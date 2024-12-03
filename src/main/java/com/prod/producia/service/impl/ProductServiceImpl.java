package com.prod.producia.service.impl;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.entity.Product;
import com.prod.producia.exception.ResourceNotFoundException;
import com.prod.producia.mapper.ProductMapper;
import com.prod.producia.repository.ProductRepository;
import com.prod.producia.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponseDTO> getProducts(Pageable pageable) {
        log.info("Fetching products with pagination");
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toResponseDTO);
    }

    @Override
    public Page<ProductResponseDTO> searchProductsByName(String name, Pageable pageable) {
        log.info("Searching products with name: {} and pagination", name);
        Page<Product> products = productRepository.findByNameContaining(name, pageable);
        return products.map(productMapper::toResponseDTO);
    }

    @Override
    public Page<ProductResponseDTO> getProductsByCategory(Long categoryId, Pageable pageable) {
        log.info("Fetching products for category ID: {} with pagination", categoryId);
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        return products.map(productMapper::toResponseDTO);
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        log.info("Adding a new product: {}", productRequestDTO.getName());
        Product product = productMapper.toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        log.info("Updating product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID " + id));
        productMapper.updateEntityFromDto(productRequestDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID " + id));
        productRepository.delete(product);
        log.info("Product with ID {} deleted", id);
    }
}
