package com.productManagement.service;

import com.productManagement.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public ProductDTO saveProduct(@Valid ProductDTO productDTO);
    public Page<ProductDTO> getProducts(int page, int size, String sortBy, String sortDirection);
    public Optional<ProductDTO> getProductById(Long id);

    public void deleteProduct(Long id);

    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);
}
