package com.k458.void_creatures.repo.enemies;

import com.k458.void_creatures.model.enemies.EnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEnemyRepo extends JpaRepository<EnemyEntity, Long> {
    List<EnemyEntity> findByUserId(Long userId);
    Optional<EnemyEntity> findByUserIdAndLocalId(Long userId, Long localId);
    void deleteByUserIdAndLocalId(Long userId, Long localId);
}
