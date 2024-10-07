package com.demoapi.democrud.repository;

import com.demoapi.democrud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , String> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);

    // Tìm kiếm theo userName và fullName, phân trang
    Page<User> findByUserNameContainingOrFullNameContaining(String userName, String fullName, Pageable pageable);
}
