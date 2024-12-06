package com.prod.producia.dto.categoryDto;

import com.prod.producia.dto.productDto.ProductEmbeddedDTO;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<ProductEmbeddedDTO> products;

}