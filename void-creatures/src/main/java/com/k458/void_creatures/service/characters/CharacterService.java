package com.k458.void_creatures.service.characters;

import com.k458.void_creatures.model.characters.CharacterEntity;
import com.k458.void_creatures.model.characters.CharactersDto;
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

    public void save(Long id, CharactersDto dto){
        purge(id);
        List<CharacterEntity> list = dto.getData();
        for(CharacterEntity entity : list){
            entity.setUserId(id);
            entity.setId(globalIndexCounterService.getGlobalIndexNext());
            entity.setLocalId(localIndexCounterService.getLocalIndexNext(id));
            repo.save(entity);
        }
    }
    private void purge(Long userId){
        List<CharacterEntity> list = repo.findByUserId(userId);
        for (CharacterEntity entity : list){
            globalIndexCounterService.addRecycleIndex(entity.getId());
            localIndexCounterService.addRecycleIndex(userId, entity.getLocalId());
            repo.deleteByUserIdAndLocalId(userId, entity.getLocalId());
        }
    }
}
