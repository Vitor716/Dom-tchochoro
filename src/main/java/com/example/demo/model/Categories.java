package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotBlank(message = "Category name must not be blank")
    private String name;

    @NotBlank(message = "Category must have a description")
    private String description;
}
