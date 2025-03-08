package com.k458.void_rooms.repo;

import com.k458.void_rooms.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoomRepo extends JpaRepository<RoomEntity, Long> {
}
