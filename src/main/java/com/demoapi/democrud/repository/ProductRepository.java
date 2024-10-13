package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Tìm kiếm theo name và description, phân trang
    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.sizes s " +
            "WHERE p.name LIKE %:name% AND " +
            "p.price BETWEEN :minPrice AND :maxPrice " +
            "GROUP BY p " +
            "HAVING SUM(s.stock) > :stock")
    Page<Product> findByNameContainingAndTotalStockGreaterThanAndPriceBetween(
            @Param("name") String name,
            @Param("stock") int stock,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            Pageable pageable);
}
