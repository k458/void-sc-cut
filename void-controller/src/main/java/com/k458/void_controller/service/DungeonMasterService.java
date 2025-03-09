package com.k458.void_controller.service;

import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.model.characters.CharacterEntity;
import com.k458.void_controller.model.enemies.EnemyEntity;
import com.k458.void_controller.model.progression.DungeonEntity;
import com.k458.void_controller.model.progression.ResourceEntity;
import com.k458.void_controller.model.progression.RoomEntity;
import com.k458.void_controller.service.creatures.CharacterService;
import com.k458.void_controller.service.creatures.EnemyService;
import com.k458.void_controller.service.progression.DungeonService;
import com.k458.void_controller.service.progression.ResourceService;
import com.k458.void_controller.service.progression.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DungeonMasterService {
    private CharacterService characterService;
    private EnemyService enemyService;
    private DungeonService dungeonService;
    private ResourceService resourceService;
    private RoomService roomService;

    public void newDungeon(PlayerSession session, Integer level){
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (dungeonEntity.getIsActive()) return;
        Long id = session.getUserId();
        dungeonEntity.setLevel(level);
        dungeonEntity.setIsActive(true);
        dungeonEntity.setRoomsTotal(1+level/2);
        dungeonEntity.setRoomsCompleted(0);
        newRoom(id, session.getRoomEntity(), dungeonEntity);
    }
    public void onRoomFinish(PlayerSession session){
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (!dungeonEntity.getIsActive()) return;
        Long id = session.getUserId();
        purgeDeadCharacters(id, session);
        purgeEnemies(id, session);
        RoomEntity roomEntity = session.getRoomEntity();
        int roomsCompleted = dungeonEntity.getRoomsCompleted()+1;
        if (roomsCompleted >= dungeonEntity.getRoomsTotal()){
            dungeonEntity.setIsActive(false);
            dungeonService.save(id, dungeonEntity);
            ResourceEntity resourceEntity = session.getResourceEntity();
            resourceEntity.setRes1(resourceEntity.getRes1()+10*dungeonEntity.getLevel());
            resourceService.save(id, resourceEntity);
            improveInFieldCharacters(id, session);
            setRoomInactive(id, session.getRoomEntity());
        } else {
            dungeonEntity.setRoomsCompleted(roomsCompleted);
            newRoom(id, roomEntity, dungeonEntity);
            dungeonService.save(id, dungeonEntity);
        }
    }
    public void onDungeonAbandon(PlayerSession session){
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (!dungeonEntity.getIsActive()) return;
        Long id = session.getUserId();
        dungeonEntity.setIsActive(false);
        dungeonService.save(id, dungeonEntity);
        purgeInFieldCharacters(id, session);
        purgeEnemies(id, session);
        setRoomInactive(id, session.getRoomEntity());
    }
    private void improveInFieldCharacters(Long id, PlayerSession session){
        List<CharacterEntity> chList = session.getCharactersList();
        for(CharacterEntity chEntity: chList){
            if (chEntity.getIsFielded()){
                chEntity.setLevel(chEntity.getLevel()+1);
                characterService.save(id, chEntity);
            }
        }
    }
    private void purgeInFieldCharacters(Long id, PlayerSession session){
        List<CharacterEntity> chList = session.getCharactersList();
        for(CharacterEntity chEntity: chList){
            if (chEntity.getIsFielded()) {
                session.removeCharacterEntity(chEntity.getLocalId());
                characterService.delete(id, chEntity.getLocalId());
            }
        }
    }
    private void purgeDeadCharacters(Long id, PlayerSession session){
        List<CharacterEntity> chList = session.getCharactersList();
        for(CharacterEntity chEntity: chList){
            if (chEntity.getHpLeft() < 1) {
                session.removeCharacterEntity(chEntity.getLocalId());
                characterService.delete(id, chEntity.getLocalId());
            }
        }
    }
    private void purgeEnemies(Long id, PlayerSession session){
        List<EnemyEntity> enList = session.getEnemiesList();
        for(EnemyEntity enEntity: enList){
            session.removeEnemyEntity(enEntity.getLocalId());
            enemyService.delete(id, enEntity.getLocalId());
        }
    }
    private void newRoom(Long id, RoomEntity roomEntity, DungeonEntity dungeonEntity){
        roomEntity.setIsActive(true);
        roomEntity.setSizeX(10+dungeonEntity.getLevel()/2);
        roomEntity.setSizeY(10+dungeonEntity.getLevel()/2);
        roomService.save(id, roomEntity);
    }
    private void setRoomInactive(Long id, RoomEntity roomEntity){
        roomEntity.setIsActive(false);
        roomService.save(id, roomEntity);
    }
}
