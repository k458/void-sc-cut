package com.k458.void_creatures.repo.characters;

import com.k458.void_creatures.model.characters.C_GlobalIndexCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IC_GlobalIndexCounterRepo extends JpaRepository<C_GlobalIndexCounterEntity, Long> {
}
