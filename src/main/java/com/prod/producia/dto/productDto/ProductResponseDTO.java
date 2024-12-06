package com.prod.producia.dto.productDto;

import com.prod.producia.dto.categoryDto.CategoryEmbeddedDTO;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;
    private CategoryEmbeddedDTO category;
}
