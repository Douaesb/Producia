package com.prod.producia.mapper;

import com.prod.producia.dto.productDto.ProductEmbeddedDTO;
import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    Product toEntity(ProductRequestDTO dto);
    ProductResponseDTO toResponseDTO(Product product);

    ProductEmbeddedDTO toEmbeddedDTO(Product product);
    void updateEntityFromDto(ProductRequestDTO requestDTO, Product product);

}

