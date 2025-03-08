package com.k458.void_characters.repo;

import com.k458.void_characters.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICharacterRepo extends JpaRepository<CharacterEntity, Long> {
    List<CharacterEntity> findByUserId(Long userId);
    Optional<CharacterEntity> findByUserIdAndCharacterId(Long userId, Long characterId);
    void deleteByUserIdAndCharacterId(Long userId, Long characterId);
}
