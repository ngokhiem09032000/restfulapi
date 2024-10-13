package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Size;
import com.demoapi.democrud.entity.SizeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, SizeId> {
    List<Size> findByProductId(String productId);
}
