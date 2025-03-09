package com.k458.void_creatures.service.characters;

import com.k458.void_creatures.model.characters.CharacterEntity;
import com.k458.void_creatures.repo.characters.ICharacterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final C_GlobalIndexCounterService globalIndexCounterService;
    private final C_LocalIndexCounterService localIndexCounterService;
    private final ICharacterRepo repo;

    public List<CharacterEntity> getByUserId(Long id){
        return repo.findByUserId(id);
    }

    public CharacterEntity save(CharacterEntity entity){
        if (entity.getId() == null){
            Long userId = entity.getUserId();
            Long localId = entity.getLocalId();
            if (userId != null && localId != null){
                CharacterEntity overwrite = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
                if (overwrite != null){
                    entity.setId(overwrite.getId());
                }
            } else {
                entity.setId(globalIndexCounterService.getGlobalIndexNext());
            }
        }
        if (entity.getLocalId() == null){
            entity.setLocalId(localIndexCounterService.getLocalIndexNext(entity.getUserId()));
        }
        return repo.save(entity);
    }

    public void delete(Long userId, Long localId){
        CharacterEntity ch = repo.findByUserIdAndLocalId(userId, localId).orElse(null);
        if (ch != null){
            globalIndexCounterService.addRecycleIndex(ch.getId());
            localIndexCounterService.addRecycleIndex(userId, localId);
            repo.deleteByUserIdAndLocalId(userId, localId);
        }
    }
}
