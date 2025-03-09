package com.k458.void_creatures.repo.items;

import com.k458.void_creatures.model.items.I_LocalIndexCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface II_LocalIndexCounterRepo extends JpaRepository<I_LocalIndexCounterEntity, Long> {
}
