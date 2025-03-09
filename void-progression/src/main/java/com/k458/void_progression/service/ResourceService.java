package com.k458.void_progression.service;

import com.k458.void_progression.model.HubEntity;
import com.k458.void_progression.model.ResourceEntity;
import com.k458.void_progression.repo.IHubRepo;
import com.k458.void_progression.repo.IResourceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final IResourceRepo repo;

    public ResourceEntity get(Long id){
        ResourceEntity entity = repo.findById(id).orElse(null);
        if(entity == null){
            entity = new ResourceEntity();
            entity.setId(id);
        }
        repo.save(entity);
        return entity;
    }

    public ResourceEntity save(ResourceEntity entity){
        return repo.save(entity);
    }
}
