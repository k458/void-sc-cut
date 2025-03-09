package com.k458.void_creatures.repo.characters;

import com.k458.void_creatures.model.characters.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICharacterRepo extends JpaRepository<CharacterEntity, Long> {
    List<CharacterEntity> findByUserId(Long userId);
    Optional<CharacterEntity> findByUserIdAndLocalId(Long userId, Long localId);
    void deleteByUserIdAndLocalId(Long userId, Long localId);
}
