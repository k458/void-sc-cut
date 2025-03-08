package com.k458.void_enemies.service;

import com.k458.void_enemies.model.GlobalIndexCounterEntity;
import com.k458.void_enemies.repo.IGlobalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GlobalIndexCounterService {
    private final IGlobalIndexCounterRepo repo;
    private GlobalIndexCounterEntity cachedGlobal;
    private static final Object lock = new Object();

    private GlobalIndexCounterEntity getGlobal(){
        if (cachedGlobal == null){
            cachedGlobal = repo.findById(1L).orElse(null);
            if (cachedGlobal == null){
                cachedGlobal = new GlobalIndexCounterEntity();
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
