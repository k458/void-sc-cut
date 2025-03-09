package com.k458.void_progression.controller;

import com.k458.void_progression.model.RoomEntity;
import com.k458.void_progression.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService service;

    @GetMapping("/{id}")
    public ResponseEntity<RoomEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    @PostMapping("/{id}}")
    public ResponseEntity<RoomEntity> save(@PathVariable("id") Long id, @RequestBody RoomEntity entity){
        if (entity.getId() == null || !entity.getId().equals(id)){
            entity.setId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }
}
