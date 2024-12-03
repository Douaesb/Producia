package com.prod.producia.controller;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/user/products")
    public Page<ProductResponseDTO> getProducts(Pageable pageable) {
        log.info("Fetching products with pagination for user");
        return productService.getProducts(pageable);
    }

    @GetMapping("/user/products/search")
    public Page<ProductResponseDTO> searchProductsByName(@RequestParam("name") String name, Pageable pageable) {
        log.info("Searching products by name: {} with pagination", name);
        return productService.searchProductsByName(name, pageable);
    }

    @GetMapping("/user/products/category/{categoryId}")
    public Page<ProductResponseDTO> getProductsByCategory(@PathVariable("categoryId") Long categoryId, Pageable pageable) {
        log.info("Fetching products for category ID: {} with pagination", categoryId);
        return productService.getProductsByCategory(categoryId, pageable);
    }

    @PostMapping("/admin/products")
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        log.info("Adding a new product: {}", productRequestDTO.getName());
        return productService.addProduct(productRequestDTO);
    }

    @PutMapping("/admin/products/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long id,
                                            @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        log.info("Updating product with ID: {}", id);
        return productService.updateProduct(id, productRequestDTO);
    }

    @DeleteMapping("/admin/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
    }
}
