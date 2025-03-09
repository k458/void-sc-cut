package com.k458.void_controller.controller;

import com.k458.void_controller.model.enemies.EnemiesDto;
import com.k458.void_controller.model.enemies.EnemyEntity;
import com.k458.void_controller.service.PlayerSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enemies")
public class EnemyController {
    private final PlayerSessionService playerSessionService;

    @GetMapping("/{id}")
    public ResponseEntity<EnemiesDto> getAll(@PathVariable("id") Long id) {
        EnemiesDto ret = new EnemiesDto();
        ret.setData(playerSessionService.getSession(id).getEnemiesList());
        return ResponseEntity.ok(ret);
    }
//    @PostMapping("/{id}")
//    public ResponseEntity<EnemyEntity> save(@PathVariable("id") Long id, @RequestBody EnemyEntity entity){
//        EnemyEntity ret = playerSessionService.getSession(id).updateEnemy(entity);
//        if (ret == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(ret);
//    }
}
