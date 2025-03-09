package com.k458.void_creatures.service.characters;

import com.k458.void_creatures.model.characters.C_LocalIndexCounterEntity;
import com.k458.void_creatures.repo.characters.IC_LocalIndexCounterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class C_LocalIndexCounterService {
    private final IC_LocalIndexCounterRepo repo;

    private C_LocalIndexCounterEntity getLocal(Long userId){
        C_LocalIndexCounterEntity local = repo.findById(userId).orElse(null);
        if (local == null){
            local = new C_LocalIndexCounterEntity();
            local.setId(userId);
            local.setRecycleIndexes(new ArrayList<>());
            local.setLastIndex(1L);
            repo.save(local);
        }
        return local;
    }

    public void addRecycleIndex(Long userId, Long index){
        C_LocalIndexCounterEntity local = getLocal(userId);
        local.addRecycleIndex(index);
        repo.save(local);
    }

    public Long getLocalIndexNext(Long userId){
        C_LocalIndexCounterEntity local = getLocal(userId);
        Long ret = local.getLocalIndexNext();
        repo.save(local);
        return ret;
    }
}
