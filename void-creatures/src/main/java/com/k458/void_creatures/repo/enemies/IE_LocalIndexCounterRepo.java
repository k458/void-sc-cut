package com.k458.void_creatures.repo.enemies;

import com.k458.void_creatures.model.enemies.E_LocalIndexCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IE_LocalIndexCounterRepo extends JpaRepository<E_LocalIndexCounterEntity, Long> {
}