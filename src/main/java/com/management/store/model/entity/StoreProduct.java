package com.management.store.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "STORE_PRODUCT")
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
