package com.management.store.model.repo;

import com.management.store.model.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
}
