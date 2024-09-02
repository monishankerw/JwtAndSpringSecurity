package com.productManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="product_details")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only letters and spaces")
    private String name;

    @Size(max = 500, message = "Description should not be greater than 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Description must contain only alphanumeric characters and spaces")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Price must be a positive number with up to two decimal places")
    private String price;
}
