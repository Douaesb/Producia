package com.prod.producia.mapper;

import com.prod.producia.dto.categoryDto.CategoryEmbeddedDTO;
import com.prod.producia.dto.categoryDto.CategoryRequestDTO;
import com.prod.producia.dto.categoryDto.CategoryResponseDTO;
import com.prod.producia.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toResponseDTO(Category category);

    CategoryEmbeddedDTO toEmbeddedDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CategoryRequestDTO dto, @MappingTarget Category category);
}
