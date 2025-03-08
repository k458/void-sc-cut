package com.k458.void_hubs.repo;

import com.k458.void_hubs.model.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHubRepo extends JpaRepository<HubEntity, Long> {
}
