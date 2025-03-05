package com.k458.void_security.repo;

import com.k458.void_security.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNameAndPassword(String name, String password);
    Optional<UserEntity> findByName(String name);
}
