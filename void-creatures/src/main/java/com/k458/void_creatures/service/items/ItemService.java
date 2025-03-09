package com.k458.void_creatures.service.items;

import com.k458.void_creatures.model.items.ItemEntity;
import com.k458.void_creatures.repo.items.IItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final I_GlobalIndexCounterService globalIndexCounterService;
    private final I_LocalIndexCounterService localIndexCounterService;
    private final IItemRepo repo;

    public List<ItemEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public ItemEntity save(ItemEntity entity){
        if (entity.getId() == null){
            Long userId = entity.getUserId();
            Long localId = entity.getLocalId();
            if (userId != null && localId != null){
                ItemEntity overwrite = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
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
        ItemEntity e = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
        if (e != null){
            globalIndexCounterService.addRecycleIndex(e.getId());
            localIndexCounterService.addRecycleIndex(userId, localId);
            repo.deleteByUserIdAndLocalId(userId, localId);
        }
    }
}
