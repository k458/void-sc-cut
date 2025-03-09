package com.k458.void_progression.controller;

import com.k458.void_progression.model.HubEntity;
import com.k458.void_progression.service.DungeonService;
import com.k458.void_progression.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hub")
public class HubController {
    private final HubService service;

    @GetMapping("/{id}")
    public ResponseEntity<HubEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    @PostMapping("/{id}}")
    public ResponseEntity<HubEntity> save(@PathVariable("id") Long id, @RequestBody HubEntity entity){
        if (entity.getId() == null || !entity.getId().equals(id)){
            entity.setId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }
}
