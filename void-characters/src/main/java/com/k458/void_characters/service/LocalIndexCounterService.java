package com.k458.void_characters.service;

import com.k458.void_characters.model.LocalIndexCounterEntity;
import com.k458.void_characters.repo.ILocalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LocalIndexCounterService {
    private final ILocalIndexCounterRepo repo;

    private LocalIndexCounterEntity getLocal(Long userId){
        LocalIndexCounterEntity local = repo.findById(userId).orElse(null);
        if (local == null){
            local = new LocalIndexCounterEntity();
            local.setId(userId);
            local.setRecycleIndexes(new ArrayList<>());
            local.setLastIndex(1L);
            repo.save(local);
        }
        return local;
    }

    public void addRecycleIndex(Long userId, Long index){
        LocalIndexCounterEntity local = getLocal(userId);
        local.addRecycleIndex(index);
        repo.save(local);
    }

    public Long getLocalIndexNext(Long userId){
        LocalIndexCounterEntity local = getLocal(userId);
        Long ret = local.getLocalIndexNext();
        repo.save(local);
        return ret;
    }
}
