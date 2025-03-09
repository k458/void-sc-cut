package com.k458.void_progression.repo;

import com.k458.void_progression.model.DungeonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDungeonRepo extends JpaRepository<DungeonEntity, Long> {
}
