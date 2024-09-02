package com.productManagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productManagement.dto.ProductDTO;

import com.productManagement.entity.Product;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.repository.ProductRepository;
import com.productManagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public ProductDTO saveProduct(@Valid ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return objectMapper.convertValue(savedProduct, ProductDTO.class);
    }

    @Override
    public Page<ProductDTO> getProducts(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> objectMapper.convertValue(product, ProductDTO.class));
    }



    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> objectMapper.convertValue(product, ProductDTO.class));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return objectMapper.convertValue(optionalProduct.get(), ProductDTO.class);
        } else {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
    }

//    @Override
//    public List<ProductDTO> findByProductId(Long id) {
//        return productRepository.findById(id).map(product -> objectMapper.convertValue(product,ProductDTO.class))
//                .stream().collect(Collectors.toList());
//    }
}
