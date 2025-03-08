package com.k458.void_items.service;

import com.k458.void_items.model.ItemEntity;
import com.k458.void_items.repo.IItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final GlobalIndexCounterService globalIndexCounterService;
    private final LocalIndexCounterService localIndexCounterService;
    private final IItemRepo repo;

    public List<ItemEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public void save(ItemEntity itemEntity){
        if (itemEntity.getId() == null){
            itemEntity.setId(globalIndexCounterService.getGlobalIndexNext());
        }
        if (itemEntity.getItemId() == null){
            itemEntity.setItemId(localIndexCounterService.getLocalIndexNext(itemEntity.getUserId()));
        }
        repo.save(itemEntity);
    }

    public void delete(Long userId, Long itemId){
        ItemEntity itm = repo.findByUserIdAndItemId(userId, itemId).orElse(null);
        if (itm != null){
            globalIndexCounterService.addRecycleIndex(itm.getId());
            localIndexCounterService.addRecycleIndex(userId, itemId);
            repo.deleteByUserIdAndItemId(userId, itemId);
        }
    }
}
