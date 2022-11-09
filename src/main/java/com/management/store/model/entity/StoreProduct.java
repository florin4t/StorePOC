package com.management.store.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "StoreProduct")
@Table(name = "STORE_PRODUCT")
@Data
@NoArgsConstructor
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;

    private String currency;
}
