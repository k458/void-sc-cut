package com.k458.void_progression.service;

import com.k458.void_progression.model.DungeonEntity;
import com.k458.void_progression.repo.IDungeonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DungeonService {
    private final IDungeonRepo repo;

    public DungeonEntity get(Long id){
        DungeonEntity entity = repo.findById(id).orElse(null);
        if(entity == null){
            entity = new DungeonEntity();
            entity.setId(id);
            entity.setIsActive(false);
        }
        repo.save(entity);
        return entity;
    }

    public DungeonEntity save(DungeonEntity entity){
        return repo.save(entity);
    }
}