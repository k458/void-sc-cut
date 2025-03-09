package com.k458.void_creatures.repo.items;

import com.k458.void_creatures.model.items.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IItemRepo extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByUserId(Long userId);
    Optional<ItemEntity> findByUserIdAndLocalId(Long userId, Long localId);
    void deleteByUserIdAndLocalId(Long userId, Long localId);
}
