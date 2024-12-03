package com.prod.producia.service.impl;

import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.entity.Category;
import com.prod.producia.exception.ResourceNotFoundException;
import com.prod.producia.mapper.CategoryMapper;
import com.prod.producia.repository.CategoryRepository;
import com.prod.producia.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponseDTO> getCategories(Pageable pageable) {
        log.info("Fetching categories with pagination");
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toResponseDTO);
    }

    @Override
    public Page<CategoryResponseDTO> searchCategoriesByName(String name, Pageable pageable) {
        log.info("Searching categories with name: {} and pagination", name);
        Page<Category> categories = categoryRepository.findByNameContaining(name, pageable);
        return categories.map(categoryMapper::toResponseDTO);
    }

    @Override
    public CategoryResponseDTO addCategory(@Valid CategoryRequestDTO categoryRequestDTO) {
        log.info("Adding a new category: {}", categoryRequestDTO.getName());

        Category category = categoryMapper.toEntity(categoryRequestDTO);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, @Valid CategoryRequestDTO categoryRequestDTO) {
        log.info("Updating category with ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID " + id));

        categoryMapper.updateEntityFromDto(categoryRequestDTO, category);

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(updatedCategory);
    }


    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID " + id));

        categoryRepository.delete(category);
        log.info("Category with ID {} deleted", id);
    }
}
