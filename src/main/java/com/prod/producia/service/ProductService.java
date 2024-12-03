package com.prod.producia.service;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponseDTO> getProducts(Pageable pageable);

    Page<ProductResponseDTO> searchProductsByName(String name, Pageable pageable);

    Page<ProductResponseDTO> getProductsByCategory(Long categoryId, Pageable pageable);


    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);

    void deleteProduct(Long id);
}
