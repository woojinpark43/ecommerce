package com.example.ecommerce.purchase.repository;

import com.example.ecommerce.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}