package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    // Tìm kiếm theo name và description, phân trang
    Page<Permission> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
