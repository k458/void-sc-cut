package com.k458.void_progression.service;

import com.k458.void_progression.model.HubEntity;
import com.k458.void_progression.repo.IHubRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HubService {
    private final IHubRepo repo;

    public HubEntity get(Long id){
        HubEntity entity = repo.findById(id).orElse(null);
        if(entity == null){
            entity = new HubEntity();
            entity.setId(id);
            entity.setIsActive(false);
        }
        repo.save(entity);
        return entity;
    }

    public HubEntity save(HubEntity entity){
        return repo.save(entity);
    }
}
