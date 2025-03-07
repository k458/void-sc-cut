package com.k458.void_resources.service;

import com.k458.void_resources.model.UserResourcesEntity;
import com.k458.void_resources.repo.IResourcesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserResourcesService {
    private final IResourcesRepo resourcesRepo;

    public Optional<UserResourcesEntity> getUserResourcesById(Long id){
        return resourcesRepo.findById(id);
    }

    public void saveUserResources(UserResourcesEntity resources){
        resourcesRepo.save(resources);
    }
}
