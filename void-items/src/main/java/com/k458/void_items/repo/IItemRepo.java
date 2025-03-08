package com.k458.void_items.repo;

import com.k458.void_items.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IItemRepo extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByUserId(Long userId);
    Optional<ItemEntity> findByUserIdAndItemId(Long userId, Long itemId);
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
