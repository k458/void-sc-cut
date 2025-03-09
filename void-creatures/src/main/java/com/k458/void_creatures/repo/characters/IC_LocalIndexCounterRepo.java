package com.k458.void_creatures.repo.characters;

import com.k458.void_creatures.model.characters.C_LocalIndexCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IC_LocalIndexCounterRepo extends JpaRepository<C_LocalIndexCounterEntity, Long> {
}
