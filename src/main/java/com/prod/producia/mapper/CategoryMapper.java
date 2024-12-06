package com.prod.producia.mapper;

import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO toResponseDTO(Category category);

}
