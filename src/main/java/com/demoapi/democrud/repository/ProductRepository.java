package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Tìm kiếm theo name và description, phân trang
    Page<Product> findByNameContaining(String name, Pageable pageable);

    Page<Product> findByNameContainingAndStockGreaterThanAndPriceBetween(
            String name, int stock, double minPrice, double maxPrice, Pageable pageable);
}
