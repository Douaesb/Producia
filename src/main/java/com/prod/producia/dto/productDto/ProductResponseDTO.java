package com.prod.producia.dto.productDto;

import com.prod.producia.dto.categoryDto.CategoryEmbeddedDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;
    private CategoryEmbeddedDTO category;
}
