package com.k458.void_creatures.service.characters;

import com.k458.void_creatures.model.characters.C_GlobalIndexCounterEntity;
import com.k458.void_creatures.repo.characters.IC_GlobalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class C_GlobalIndexCounterService {
    private final IC_GlobalIndexCounterRepo repo;
    private C_GlobalIndexCounterEntity cachedGlobal;
    private static final Object lock = new Object();

    private C_GlobalIndexCounterEntity getGlobal(){
        if (cachedGlobal == null){
            cachedGlobal = repo.findById(1L).orElse(null);
            if (cachedGlobal == null){
                cachedGlobal = new C_GlobalIndexCounterEntity();
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
