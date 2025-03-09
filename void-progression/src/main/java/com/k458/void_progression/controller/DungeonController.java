package com.k458.void_progression.controller;

import com.k458.void_progression.model.DungeonEntity;
import com.k458.void_progression.service.DungeonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dungeon")
public class DungeonController {

    private final DungeonService service;

    @GetMapping("/{id}")
    public ResponseEntity<DungeonEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    @PostMapping("/{id}}")
    public ResponseEntity<DungeonEntity> save(@PathVariable("id") Long id, @RequestBody DungeonEntity entity){
        if (entity.getId() == null || !entity.getId().equals(id)){
            entity.setId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }
}
