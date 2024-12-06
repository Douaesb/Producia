package com.prod.producia.controller;

import com.prod.producia.dto.productDto.ProductRequestDTO;
import com.prod.producia.dto.productDto.ProductResponseDTO;
import com.prod.producia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/user/products", "/admin/products"})
    public Page<ProductResponseDTO> listProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        return productService.listProducts(page, size, sortBy);
    }


    @GetMapping({"/user/products/by-category", "/admin/products/by-category"})
    public Page<ProductResponseDTO> searchProductsByCategory(
            @RequestParam Long categoryId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        return productService.searchProductsByCategory(categoryId, page, size, sortBy);
    }

    @PostMapping("/admin/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequestDTO));
    }

    @PutMapping("/admin/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId, productRequestDTO));
    }

    @DeleteMapping("/admin/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}

