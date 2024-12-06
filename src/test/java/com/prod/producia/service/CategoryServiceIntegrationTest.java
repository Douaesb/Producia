package com.prod.producia.service;

import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.entity.Category;
import com.prod.producia.repository.CategoryRepository;
import com.prod.producia.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=dev")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testListCategories_Success() {
        Category category = Category.builder()
                .name("Category 1")
                .description("Description 1")
                .build();
        categoryRepository.save(category);

        Page<CategoryResponseDTO> result = categoryService.listCategories(0, 10, "id");

        assertEquals(1, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
    }

    @Test
    void testListCategories_EmptyResult() {
        Page<CategoryResponseDTO> result = categoryService.listCategories(0, 10, "id");

        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchCategories_Success() {
        Category category1 = Category.builder()
                .name("Category 1")
                .description("Description 1")
                .build();
        categoryRepository.save(category1);

        Category category2 = Category.builder()
                .name("Category 2")
                .description("Description 2")
                .build();
        categoryRepository.save(category2);

        Page<CategoryResponseDTO> result = categoryService.searchCategories("Category", 0, 10, "id");

        assertEquals(2, result.getTotalElements());
    }

    @Test
    void testSearchCategories_EmptyResult() {
        Page<CategoryResponseDTO> result = categoryService.searchCategories("NonExisting", 0, 10, "id");
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddCategory_Success() {
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder()
                .name("New Category")
                .description("New Description")
                .build();

        CategoryResponseDTO responseDTO = categoryService.addCategory(requestDTO);

        assertNotNull(responseDTO.getId());
        assertEquals("New Category", responseDTO.getName());
        assertTrue(categoryRepository.findById(responseDTO.getId()).isPresent());
    }

    @Test
    void testUpdateCategory_Success() throws Exception {
        Category existingCategory = Category.builder()
                .name("Old Category")
                .description("Old Description")
                .build();
        categoryRepository.save(existingCategory);

        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder()
                .name("Updated Category")
                .description("Updated Description")
                .build();

        CategoryResponseDTO updatedCategory = categoryService.updateCategory(existingCategory.getId(), requestDTO);

        assertEquals("Updated Category", updatedCategory.getName());
        assertEquals("Updated Description", updatedCategory.getDescription());
    }

    @Test
    void testUpdateCategory_NotFound() {
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder()
                .name("Non-existent Category")
                .description("Description")
                .build();

        Exception exception = assertThrows(Exception.class, () -> categoryService.updateCategory(999L, requestDTO));

        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void testDeleteCategory_Success() {
        Category category = Category.builder()
                .name("Category to Delete")
                .description("Description to Delete")
                .build();
        categoryRepository.save(category);

        categoryService.deleteCategory(category.getId());

        assertFalse(categoryRepository.findById(category.getId()).isPresent());
    }



    @Test
    void testListProductsInCategory_EmptyResult() {
        Category category = Category.builder()
                .name("Empty Category")
                .description("No Products")
                .build();
        categoryRepository.save(category);

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<ProductResponseDTO> result = categoryService.listProductsInCategory(category.getId(), 0, 10, "id");

        assertTrue(result.isEmpty());
    }
}
