package com.k458.void_characters.service;

import com.k458.void_characters.model.CharacterEntity;
import com.k458.void_characters.repo.ICharacterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final GlobalIndexCounterService globalIndexCounterService;
    private final LocalIndexCounterService localIndexCounterService;
    private final ICharacterRepo repo;

    public List<CharacterEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public void save(CharacterEntity characterEntity){
        if (characterEntity.getId() == null){
            characterEntity.setId(globalIndexCounterService.getGlobalIndexNext());
        }
        if (characterEntity.getCharacterId() == null){
            characterEntity.setCharacterId(localIndexCounterService.getLocalIndexNext(characterEntity.getUserId()));
        }
        repo.save(characterEntity);
    }

    public void delete(Long userId, Long characterId){
        CharacterEntity ch = repo.findByUserIdAndItemId(userId, characterId).orElse(null);
        if (ch != null){
            globalIndexCounterService.addRecycleIndex(ch.getId());
            localIndexCounterService.addRecycleIndex(userId, characterId);
            repo.deleteByUserIdAndItemId(userId, characterId);
        }
    }
}
