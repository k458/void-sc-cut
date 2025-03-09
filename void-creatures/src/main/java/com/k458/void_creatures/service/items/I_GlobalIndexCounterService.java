package com.k458.void_creatures.service.items;

import com.k458.void_creatures.model.items.I_GlobalIndexCounterEntity;
import com.k458.void_creatures.repo.items.II_GlobalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class I_GlobalIndexCounterService {
    private final II_GlobalIndexCounterRepo repo;
    private I_GlobalIndexCounterEntity cachedGlobal;
    private static final Object lock = new Object();

    private I_GlobalIndexCounterEntity getGlobal(){
        if (cachedGlobal == null){
            cachedGlobal = repo.findById(1L).orElse(null);
            if (cachedGlobal == null){
                cachedGlobal = new I_GlobalIndexCounterEntity();
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
