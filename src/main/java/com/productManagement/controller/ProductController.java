package com.productManagement.controller;


import com.productManagement.dto.ProductDTO;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
 


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product) {
        ProductDTO savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

//    @GetMapping
//    public Page<ProductDTO> getProducts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "CREATE_DATE") String sortBy,
//            @RequestParam(defaultValue = "desc") String sortDirection) {
//
//        return productService.getProducts(page, size, sortBy, sortDirection);
//    }
@GetMapping
public List<ProductDTO> findAllProduct() {
    return productService.findAllProducts();
}

@GetMapping("/{id}")
public ResponseEntity<ProductDTO> findByProductById(@PathVariable Long id) {
    ProductDTO product = productService.findProductById(id);
    return ResponseEntity.ok(product);
}

     @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO product) {
        try {
            productService.findProductById(id); 
            product.setId(id);
            ProductDTO updatedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

      @DeleteMapping("/delete/{id}")
      public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}