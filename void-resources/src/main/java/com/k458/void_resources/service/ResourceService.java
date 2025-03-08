package com.k458.void_resources.service;

import com.k458.void_resources.model.ResourceEntity;
import com.k458.void_resources.repo.IResourceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final IResourceRepo repo;

    public Optional<ResourceEntity> getByUserId(Long userId){
        return repo.findById(userId);
    }

    public void save(ResourceEntity resourceEntity){
        repo.save(resourceEntity);
    }

    public void delete(Long userId){
        repo.deleteById(userId);
    }
}
