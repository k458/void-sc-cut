package com.k458.void_controller.controller;

import com.k458.void_controller.model.characters.CharacterEntity;
import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.model.characters.CharactersDto;
import com.k458.void_controller.model.enemies.EnemiesDto;
import com.k458.void_controller.model.enemies.EnemyEntity;
import com.k458.void_controller.model.progression.DungeonEntity;
import com.k458.void_controller.model.progression.ResourceEntity;
import com.k458.void_controller.service.DungeonMasterService;
import com.k458.void_controller.service.EntityMergerService;
import com.k458.void_controller.service.PlayerSessionService;
import com.k458.void_controller.service.creatures.CharacterService;
import com.k458.void_controller.service.creatures.EnemyService;
import com.k458.void_controller.service.creatures.ItemService;
import com.k458.void_controller.service.progression.DungeonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final PlayerSessionService playerSessionService;
    private final EntityMergerService entityMergerService;
    private final CharacterService characterService;
    private final EnemyService enemyService;
    private final ItemService itemService;
    private final DungeonMasterService dungeonMasterService;
    private final DungeonService dungeonService;

    @PostMapping("/updateCharacterBulk/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable("id") Long id, @RequestBody CharactersDto dto){
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null) return ResponseEntity.notFound().build();
        if (dto == null) return ResponseEntity.badRequest().build();
        List<CharacterEntity> entityList = dto.getData();
        for (CharacterEntity entity : entityList)
        {
            CharacterEntity existingEntity = session.getCharacterEntity(entity.getLocalId());
            if (existingEntity != null){
                existingEntity = entityMergerService.mergeCharacterEntity(existingEntity, entity);
                characterService.save(id, existingEntity);
            }
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/updateEnemyBulk/{id}")
    public ResponseEntity<Void> updateEnemy(@PathVariable("id") Long id, @RequestBody EnemiesDto dto){
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null) return ResponseEntity.notFound().build();
        if (dto == null) return ResponseEntity.badRequest().build();
        List<EnemyEntity> entityList = dto.getData();
        for (EnemyEntity entity : entityList)
        {
            EnemyEntity existingEntity = session.getEnemyEntity(entity.getLocalId());
            if (existingEntity != null){
                existingEntity = entityMergerService.mergeEnemyEntity(existingEntity, entity);
                enemyService.save(id, existingEntity);
            }
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/onRoomFinish/{id}")
    public ResponseEntity<Void> onRoomFinish(@PathVariable("id") Long id) {
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null) return ResponseEntity.notFound().build();
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (!dungeonEntity.getIsActive()) return ResponseEntity.badRequest().build();
        dungeonMasterService.onRoomFinish(session);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/newDungeon/{id}/{level}")
    public ResponseEntity<Void> newDungeon(@PathVariable("id") Long id, @PathVariable("level") Integer level) {
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null) return ResponseEntity.notFound().build();
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (dungeonEntity.getIsActive()) return ResponseEntity.badRequest().build();
        if (level < 1) level = 1;
        if (level > 10) level = 10;
        dungeonMasterService.newDungeon(session, level);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/abandonDungeon/{id}")
    public ResponseEntity<Void> abandonDungeon(@PathVariable("id") Long id) {
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null) return ResponseEntity.notFound().build();
        DungeonEntity dungeonEntity = session.getDungeonEntity();
        if (!dungeonEntity.getIsActive()) return ResponseEntity.badRequest().build();
        dungeonMasterService.onDungeonAbandon(session);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/updateCharacter/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable("id") Long id, @RequestBody CharacterEntity entity){
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null){
            return ResponseEntity.notFound().build();
        }
        CharacterEntity existingEntity = session.getCharacterEntity(entity.getLocalId());
        if (existingEntity == null){
            return ResponseEntity.notFound().build();
        }
        existingEntity = entityMergerService.mergeCharacterEntity(existingEntity, entity);
        characterService.save(id, existingEntity);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/updateEnemy/{id}")
    public ResponseEntity<Void> updateEnemy(@PathVariable("id") Long id, @RequestBody EnemyEntity entity){
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null){
            return ResponseEntity.notFound().build();
        }
        EnemyEntity existingEntity = session.getEnemyEntity(entity.getLocalId());
        if (existingEntity == null){
            return ResponseEntity.notFound().build();
        }
        existingEntity = entityMergerService.mergeEnemyEntity(existingEntity, entity);
        enemyService.save(id, existingEntity);
        return ResponseEntity.ok().build();
    }
}
