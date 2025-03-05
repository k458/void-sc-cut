package com.k458.void_users.service;

import com.k458.void_users.model.User;
import com.k458.void_users.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(null);
    }

//    @Override
//    public User getUserByName(String name) {
//        return userRepo.findBy(id).orElseThrow(null);
//    }

    public User updateUser(User u) {
        User userById = getUserById(u.getId());
        userById.setName(u.getName());
        return userRepo.save(userById);
    }

    public User createUser(User u) {
        return userRepo.save(u);
    }

    public void deleteUser(Long id) {
        User userById = getUserById(id);
        userRepo.delete(userById);
    }
}
