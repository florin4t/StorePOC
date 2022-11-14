package com.management.store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "STORE_PRODUCT", schema = "PUBLIC")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Product name is required.")
    @Size(min = 5, message = "A product's name must be at least 5 characters in length.")
    private String name;

    @NotNull
    @PositiveOrZero(message = "The product price cannot be a negative value.")
    private Double price;

    @NotEmpty(message = "A value for the currency needs to be provided.")
    @Size(min = 3, max = 3, message = "A currency must have exactly 3 characters.")
    private String currency;
}
