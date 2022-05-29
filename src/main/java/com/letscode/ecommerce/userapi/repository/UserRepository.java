package com.letscode.ecommerce.userapi.repository;

import com.letscode.ecommerce.userapi.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String username);

    Optional<UserEntity> findByIdAndEmailAndPassword(Long id, String email, String password);
}
