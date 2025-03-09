package com.k458.void_progression.controller;

import com.k458.void_progression.model.ResourceEntity;
import com.k458.void_progression.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService service;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    @PostMapping("/{id}}")
    public ResponseEntity<ResourceEntity> save(@PathVariable("id") Long id, @RequestBody ResourceEntity entity){
        if (entity.getId() == null || !entity.getId().equals(id)){
            entity.setId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }
}