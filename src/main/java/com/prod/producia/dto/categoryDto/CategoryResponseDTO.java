package com.prod.producia.dto.categoryDto;

import com.prod.producia.dto.productDto.ProductEmbeddedDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<ProductEmbeddedDTO> products;
}