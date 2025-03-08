package com.k458.void_rooms.service;

import com.k458.void_rooms.model.RoomEntity;
import com.k458.void_rooms.repo.IRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final IRoomRepo repo;

    public Optional<RoomEntity> getByUserId(Long userId){
        return repo.findById(userId);
    }

    public void save(RoomEntity roomEntity){
        repo.save(roomEntity);
    }

    public void delete(Long userId){
        repo.deleteById(userId);
    }
}
