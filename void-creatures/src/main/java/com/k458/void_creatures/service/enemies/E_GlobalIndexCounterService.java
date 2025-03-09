package com.k458.void_creatures.service.enemies;

import com.k458.void_creatures.model.enemies.E_GlobalIndexCounterEntity;
import com.k458.void_creatures.repo.enemies.IE_GlobalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class E_GlobalIndexCounterService {
    private final IE_GlobalIndexCounterRepo repo;
    private E_GlobalIndexCounterEntity cachedGlobal;
    private static final Object lock = new Object();

    private E_GlobalIndexCounterEntity getGlobal(){
        if (cachedGlobal == null){
            cachedGlobal = repo.findById(1L).orElse(null);
            if (cachedGlobal == null){
                cachedGlobal = new E_GlobalIndexCounterEntity();
                cachedGlobal.setId(1L);
                cachedGlobal.setRecycleIndexes(new ArrayList<>());
                cachedGlobal.setLastIndex(1L);
                repo.save(cachedGlobal);
            }
        }
        return cachedGlobal;
    }

    private void saveGlobal(){
        repo.save(cachedGlobal);
    }

    public void addRecycleIndex(Long index){
        synchronized (lock) {
            getGlobal().addRecycleIndex(index);
            saveGlobal();
        }
    }

    public Long getGlobalIndexNext(){
        synchronized (lock) {
            Long ret = getGlobal().getGlobalIndexNext();
            saveGlobal();
            return ret;
        }
    }
}
