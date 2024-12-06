package com.prod.producia.service;

import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import org.springframework.data.domain.Page;


public interface CategoryService {

    Page<CategoryResponseDTO> listCategories(int page, int size, String sortBy);

    Page<CategoryResponseDTO> searchCategories(String name, int page, int size, String sortBy);

    Page<ProductResponseDTO> listProductsInCategory(Long categoryId, int page, int size, String sortBy);

    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) throws Exception;

    void deleteCategory(Long categoryId);
}
