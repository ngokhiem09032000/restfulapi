package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.Role;
import com.demoapi.democrud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    // Tìm kiếm theo name và description, phân trang
    Page<Role> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
