package com.k458.void_creatures.repo.items;

import com.k458.void_creatures.model.items.I_GlobalIndexCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface II_GlobalIndexCounterRepo extends JpaRepository<I_GlobalIndexCounterEntity, Long> {
}
