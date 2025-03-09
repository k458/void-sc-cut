package com.k458.void_creatures.controller;

import com.k458.void_creatures.model.CreaturesDto;
import com.k458.void_creatures.model.characters.CharacterEntity;
import com.k458.void_creatures.model.characters.CharactersDto;
import com.k458.void_creatures.service.characters.CharacterService;
import com.k458.void_creatures.service.enemies.EnemyService;
import com.k458.void_creatures.service.items.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/creatures")
public class CreaturesController {

    private final CharacterService chrService;
    private final EnemyService enmService;
    private final ItemService itmService;

    @GetMapping("/{id}")
    public ResponseEntity<CreaturesDto> get(@PathVariable("id") Long id) {
        CreaturesDto dto = new CreaturesDto();
        dto.setCharacterEntities(chrService.getByUserId(id));
        dto.setEnemyEntities(enmService.getByUserId(id));
        dto.setItemEntities(itmService.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
//    @PostMapping("/{id}")
//    public ResponseEntity<CharacterEntity> save(@PathVariable("id") Long id, @RequestBody CharacterEntity entity){
//        if (entity.getUserId() == null){
//            entity.setUserId(id);
//        }
//        return ResponseEntity.ok(service.save(entity));
//    }
//
//    @DeleteMapping("/delete/{userId}/{localId}")
//    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("localId") Long localId){
//        service.delete(userId, localId);
//        return ResponseEntity.ok().build();
//    }

}