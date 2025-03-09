package com.k458.void_creatures.service.enemies;

import com.k458.void_creatures.model.enemies.E_LocalIndexCounterEntity;
import com.k458.void_creatures.repo.enemies.IE_LocalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class E_LocalIndexCounterService {
    private final IE_LocalIndexCounterRepo repo;

    private E_LocalIndexCounterEntity getLocal(Long userId){
        E_LocalIndexCounterEntity local = repo.findById(userId).orElse(null);
        if (local == null){
            local = new E_LocalIndexCounterEntity();
            local.setId(userId);
            local.setRecycleIndexes(new ArrayList<>());
            local.setLastIndex(1L);
            repo.save(local);
        }
        return local;
    }

    public void addRecycleIndex(Long userId, Long index){
        E_LocalIndexCounterEntity local = getLocal(userId);
        local.addRecycleIndex(index);
        repo.save(local);
    }

    public Long getLocalIndexNext(Long userId){
        E_LocalIndexCounterEntity local = getLocal(userId);
        Long ret = local.getLocalIndexNext();
        repo.save(local);
        return ret;
    }
}
