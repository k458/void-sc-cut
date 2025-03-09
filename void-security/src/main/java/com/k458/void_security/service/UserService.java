package com.k458.void_security.service;

import com.k458.void_security.model.UserEntity;
import com.k458.void_security.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public UserEntity getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public UserEntity getByNameAndPassword(String name, String password) {
        return userRepo.findByNameAndPassword(name, password).orElse(null);
    }

    public UserEntity getByName(String name) {
        return userRepo.findByName(name).orElse(null);
    }

    public UserEntity save(UserEntity entity){
        return userRepo.save(entity);
    }
}
