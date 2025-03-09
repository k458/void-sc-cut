package com.k458.void_progression.service;

import com.k458.void_progression.model.RoomEntity;
import com.k458.void_progression.repo.IRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final IRoomRepo repo;

    public RoomEntity get(Long id){
        RoomEntity entity = repo.findById(id).orElse(null);
        if(entity == null){
            entity = new RoomEntity();
            entity.setId(id);
            entity.setIsActive(false);
        }
        repo.save(entity);
        return entity;
    }

    public RoomEntity save(RoomEntity entity){
        return repo.save(entity);
    }
}
