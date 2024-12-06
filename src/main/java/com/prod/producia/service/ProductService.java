package com.prod.producia.service;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponseDTO> listProducts(int page, int size, String sortBy);
    Page<ProductResponseDTO> searchProductsByCategory(Long categoryId, int page, int size, String sortBy);
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long productId);
}
