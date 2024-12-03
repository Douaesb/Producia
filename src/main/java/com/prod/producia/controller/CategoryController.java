package com.prod.producia.controller;

import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/user/categories")
    public Page<CategoryResponseDTO> getCategories(Pageable pageable) {
        log.info("Fetching categories with pagination for user");
        return categoryService.getCategories(pageable);
    }

    @GetMapping("/user/categories/search")
    public Page<CategoryResponseDTO> searchCategoriesByName(@RequestParam("name") String name, Pageable pageable) {
        log.info("Searching categories by name: {} with pagination", name);
        return categoryService.searchCategoriesByName(name, pageable);
    }

//    @GetMapping("/user/categories/{categoryId}/products")
//    public List<ProductResponseDTO> getProductsByCategory(@PathVariable("categoryId") Long categoryId, Pageable pageable) {
//        log.info("Fetching products for category with ID: {} and pagination", categoryId);
//        return categoryService.getProductsByCategory(categoryId, pageable);
//    }

    @PostMapping("/admin/categories")
    public CategoryResponseDTO addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        log.info("Adding a new category: {}", categoryRequestDTO.getName());
        return categoryService.addCategory(categoryRequestDTO);
    }

    @PutMapping("/admin/categories/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable("id") Long id,
                                              @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        log.info("Updating category with ID: {}", id);
        return categoryService.updateCategory(id, categoryRequestDTO);
    }

    @DeleteMapping("/admin/categories/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        log.info("Deleting category with ID: {}", id);
        categoryService.deleteCategory(id);
    }
}
