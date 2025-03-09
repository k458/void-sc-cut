package com.k458.void_creatures.service.enemies;

import com.k458.void_creatures.model.enemies.EnemiesDto;
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

    public void save(Long id, EnemiesDto dto){
        purge(id);
        List<EnemyEntity> list = dto.getData();
        for(EnemyEntity entity : list){
            entity.setUserId(id);
            entity.setId(globalIndexCounterService.getGlobalIndexNext());
            entity.setLocalId(localIndexCounterService.getLocalIndexNext(id));
            repo.save(entity);
        }
    }
    private void purge(Long userId){
        List<EnemyEntity> list = repo.findByUserId(userId);
        for (EnemyEntity entity : list){
            globalIndexCounterService.addRecycleIndex(entity.getId());
            localIndexCounterService.addRecycleIndex(userId, entity.getLocalId());
            repo.deleteByUserIdAndLocalId(userId, entity.getLocalId());
        }
    }
}