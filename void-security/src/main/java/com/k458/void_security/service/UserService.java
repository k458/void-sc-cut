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

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserByNameAndPassword(String name, String password) {
        UserEntity userEntity = userRepo.findByNameAndPassword(name, password).orElse(null);
        return Optional.ofNullable(userEntity);
    }

    public Optional<UserEntity> getUserByName(String name) {
        UserEntity userEntity = userRepo.findByName(name).orElse(null);
        return Optional.ofNullable(userEntity);
    }

    public UserEntity addUser(UserEntity userEntity){
        userRepo.save(userEntity);
        return userEntity;
    }
}
