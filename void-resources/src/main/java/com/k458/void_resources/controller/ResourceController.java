package com.k458.void_resources.controller;

import com.k458.void_resources.model.ResourceEntity;
import com.k458.void_resources.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceEntity> getById(@PathVariable("id") Long id) {
        ResourceEntity r = resourceService.getByUserId(id).orElse(null);
        if(r == null){
            r = new ResourceEntity();
        }
        return ResponseEntity.ok(r);
    }
    @PostMapping("/save")
    public ResponseEntity<ResourceEntity> save(@RequestBody ResourceEntity resourceEntity){
        resourceService.save(resourceEntity);
        return ResponseEntity.ok().body(resourceEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId){
        resourceService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
