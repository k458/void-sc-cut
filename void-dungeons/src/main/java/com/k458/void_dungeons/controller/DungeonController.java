package com.k458.void_dungeons.controller;

import com.k458.void_dungeons.model.DungeonEntity;
import com.k458.void_dungeons.service.DungeonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DungeonController {

    private final DungeonService dungeonService;

    @GetMapping("/{id}")
    public ResponseEntity<DungeonEntity> getById(@PathVariable("id") Long id) {
        DungeonEntity r = dungeonService.getByUserId(id).orElse(null);
        if(r == null){
            r = new DungeonEntity();
            r.setIsActive(false);
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(r);
    }
    @PostMapping("/save")
    public ResponseEntity<DungeonEntity> save(@RequestBody DungeonEntity dungeonEntity){
        dungeonService.save(dungeonEntity);
        return ResponseEntity.ok().body(dungeonEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId){
        dungeonService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
