package com.k458.void_creatures.service.items;

import com.k458.void_creatures.model.items.I_LocalIndexCounterEntity;
import com.k458.void_creatures.repo.items.II_LocalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class I_LocalIndexCounterService {
    private final II_LocalIndexCounterRepo repo;

    private I_LocalIndexCounterEntity getLocal(Long userId){
        I_LocalIndexCounterEntity local = repo.findById(userId).orElse(null);
        if (local == null){
            local = new I_LocalIndexCounterEntity();
            local.setId(userId);
            local.setRecycleIndexes(new ArrayList<>());
            local.setLastIndex(1L);
            repo.save(local);
        }
        return local;
    }

    public void addRecycleIndex(Long userId, Long index){
        I_LocalIndexCounterEntity local = getLocal(userId);
        local.addRecycleIndex(index);
        repo.save(local);
    }

    public Long getLocalIndexNext(Long userId){
        I_LocalIndexCounterEntity local = getLocal(userId);
        Long ret = local.getLocalIndexNext();
        repo.save(local);
        return ret;
    }
}
