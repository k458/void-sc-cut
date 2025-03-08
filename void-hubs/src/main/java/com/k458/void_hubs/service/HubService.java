package com.k458.void_hubs.service;

import com.k458.void_hubs.model.HubEntity;
import com.k458.void_hubs.repo.IHubRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HubService {
    private final IHubRepo repo;

    public Optional<HubEntity> getByUserId(Long userId){
        return repo.findById(userId);
    }

    public void save(HubEntity hubEntity){
        repo.save(hubEntity);
    }

    public void delete(Long userId){
        repo.deleteById(userId);
    }
}
