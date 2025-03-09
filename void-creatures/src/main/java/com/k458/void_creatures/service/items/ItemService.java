package com.k458.void_creatures.service.items;

import com.k458.void_creatures.model.items.ItemEntity;
import com.k458.void_creatures.model.items.ItemsDto;
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

    public void save(Long id, ItemsDto dto){
        purge(id);
        List<ItemEntity> list = dto.getData();
        for(ItemEntity entity : list){
            entity.setUserId(id);
            entity.setId(globalIndexCounterService.getGlobalIndexNext());
            entity.setLocalId(localIndexCounterService.getLocalIndexNext(id));
            repo.save(entity);
        }
    }
    private void purge(Long userId){
        List<ItemEntity> list = repo.findByUserId(userId);
        for (ItemEntity entity : list){
            globalIndexCounterService.addRecycleIndex(entity.getId());
            localIndexCounterService.addRecycleIndex(userId, entity.getLocalId());
            repo.deleteByUserIdAndLocalId(userId, entity.getLocalId());
        }
    }
}
