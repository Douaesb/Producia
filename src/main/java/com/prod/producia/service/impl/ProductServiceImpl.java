package com.prod.producia.service.impl;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.entity.Product;
import com.prod.producia.mapper.ProductMapper;
import com.prod.producia.repository.ProductRepository;
import com.prod.producia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

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
        Product product = productMapper.toEntity(productRequestDTO);
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
