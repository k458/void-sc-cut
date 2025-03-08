package com.k458.void_dungeons.service;

import com.k458.void_dungeons.model.DungeonEntity;
import com.k458.void_dungeons.repo.IDungeonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DungeonService {
    private final IDungeonRepo repo;

    public Optional<DungeonEntity> getByUserId(Long userId){
        return repo.findById(userId);
    }

    public void save(DungeonEntity dungeonEntity){
        repo.save(dungeonEntity);
    }

    public void delete(Long userId){
        repo.deleteById(userId);
    }
}
