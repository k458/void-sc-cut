package com.k458.void_enemies.service;

import com.k458.void_enemies.model.EnemyEntity;
import com.k458.void_enemies.repo.IEnemyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnemyService {
    private final GlobalIndexCounterService globalIndexCounterService;
    private final LocalIndexCounterService localIndexCounterService;
    private final IEnemyRepo repo;

    public List<EnemyEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public void save(EnemyEntity enemyEntity){
        if (enemyEntity.getId() == null){
            enemyEntity.setId(globalIndexCounterService.getGlobalIndexNext());
        }
        if (enemyEntity.getEnemyId() == null){
            enemyEntity.setEnemyId(localIndexCounterService.getLocalIndexNext(enemyEntity.getUserId()));
        }
        repo.save(enemyEntity);
    }

    public void delete(Long userId, Long enemyId){
        EnemyEntity enm = repo.findByUserIdAndItemId(userId, enemyId).orElse(null);
        if (enm != null){
            globalIndexCounterService.addRecycleIndex(enm.getId());
            localIndexCounterService.addRecycleIndex(userId, enemyId);
            repo.deleteByUserIdAndItemId(userId, enemyId);
        }
    }
}
