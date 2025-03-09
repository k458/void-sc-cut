package com.k458.void_creatures.controller;

import com.k458.void_creatures.model.enemies.EnemiesDto;
import com.k458.void_creatures.model.enemies.EnemyEntity;
import com.k458.void_creatures.service.enemies.EnemyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enemies")
public class EnemyController {

    private final EnemyService service;

    @GetMapping("/{id}")
    public ResponseEntity<EnemiesDto> get(@PathVariable("id") Long id) {
        EnemiesDto dto = new EnemiesDto();
        dto.setData(service.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<EnemyEntity> save(@PathVariable("id") Long id, @RequestBody EnemyEntity entity){
        if (entity.getUserId() == null){
            entity.setUserId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/delete/{userId}/{localId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("localId") Long localId){
        service.delete(userId, localId);
        return ResponseEntity.ok().build();
    }

}
