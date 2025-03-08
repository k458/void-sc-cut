package com.k458.void_enemies.controller;

import com.k458.void_enemies.model.EnemiesDto;
import com.k458.void_enemies.model.EnemyEntity;
import com.k458.void_enemies.service.EnemyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EnemyController {

    private final EnemyService enemyService;

    @GetMapping("/{id}")
    public ResponseEntity<EnemiesDto> getById(@PathVariable("id") Long id) {
        EnemiesDto dto = new EnemiesDto();
        dto.setEnemies(enemyService.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/save")
    public ResponseEntity<EnemyEntity> save(@RequestBody EnemyEntity enemyEntity){
        if (enemyEntity.getUserId() == null){
            System.out.println("To save enemy userId must be specified");
            return ResponseEntity.badRequest().build();
        }
        enemyService.save(enemyEntity);
        return ResponseEntity.ok().body(enemyEntity);
    }

    @DeleteMapping("/delete/{userId}/{enemyId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("enemyId") Long enemyId){
        enemyService.delete(userId, enemyId);
        return ResponseEntity.ok().build();
    }

}
