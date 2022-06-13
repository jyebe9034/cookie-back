package com.example.cookie.user.repository;

import com.example.cookie.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {
    Optional<User> findById(@Param("id") String id);
}
