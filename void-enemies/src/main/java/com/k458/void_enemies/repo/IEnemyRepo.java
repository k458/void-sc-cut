package com.k458.void_enemies.repo;

import com.k458.void_enemies.model.EnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEnemyRepo extends JpaRepository<EnemyEntity, Long> {
    List<EnemyEntity> findByUserId(Long userId);
    Optional<EnemyEntity> findByUserIdAndItemId(Long userId, Long enemyId);
    void deleteByUserIdAndItemId(Long userId, Long enemyId);
}
