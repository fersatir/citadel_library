package com.library.repository;

import com.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Long userId);

    Boolean existsByEmail(String email);

}

