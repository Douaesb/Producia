package com.prod.producia.service.impl;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.entity.Category;
import com.prod.producia.entity.Product;
import com.prod.producia.exception.ResourceNotFoundException;
import com.prod.producia.mapper.ProductMapper;
import com.prod.producia.repository.CategoryRepository;
import com.prod.producia.repository.ProductRepository;
import com.prod.producia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductResponseDTO> listProducts(int page, int size, String sortBy) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy == null ? "id" : sortBy)))
                .map(productMapper::toResponseDTO);
    }


    @Override
    public Page<ProductResponseDTO> searchProductsByCategory(Long categoryId, int page, int size, String sortBy) {
        return productRepository.findByCategoryId(categoryId, PageRequest.of(page, size, Sort.by(sortBy == null ? "id" : sortBy)))
                .map(productMapper::toResponseDTO);
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow( () -> new ResourceNotFoundException("category not found"));
        Product product = productMapper.toEntity(productRequestDTO).setCategory(category);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product = productMapper.toEntity(productRequestDTO);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
