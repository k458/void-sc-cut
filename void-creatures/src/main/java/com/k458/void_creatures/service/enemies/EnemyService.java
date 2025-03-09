package com.k458.void_creatures.service.enemies;

import com.k458.void_creatures.model.enemies.EnemyEntity;
import com.k458.void_creatures.repo.enemies.IEnemyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnemyService {
    private final E_GlobalIndexCounterService globalIndexCounterService;
    private final E_LocalIndexCounterService localIndexCounterService;
    private final IEnemyRepo repo;

    public List<EnemyEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public EnemyEntity save(EnemyEntity entity){
        if (entity.getId() == null){
            Long userId = entity.getUserId();
            Long localId = entity.getLocalId();
            if (userId != null && localId != null){
                EnemyEntity overwrite = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
                if (overwrite != null){
                    entity.setId(overwrite.getId());
                }
            } else {
                entity.setId(globalIndexCounterService.getGlobalIndexNext());
            }
        }
        if (entity.getLocalId() == null){
            entity.setLocalId(localIndexCounterService.getLocalIndexNext(entity.getUserId()));
        }
        return repo.save(entity);
    }

    public void delete(Long userId, Long localId){
        EnemyEntity e = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
        if (e != null){
            globalIndexCounterService.addRecycleIndex(e.getId());
            localIndexCounterService.addRecycleIndex(userId, localId);
            repo.deleteByUserIdAndLocalId(userId, localId);
        }
    }
}
