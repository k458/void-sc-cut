package com.k458.void_controller.controller;

import com.k458.void_controller.model.enemies.EnemiesDto;
import com.k458.void_controller.service.creatures.EnemyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enemies")
public class EnemyController {
    private final EnemyService service;

    @GetMapping("/{id}")
    public ResponseEntity<EnemiesDto> get(@PathVariable("id") Long id) {
        ResponseEntity<EnemiesDto> response = service.get(id).block();
        if (response.getStatusCode() == HttpStatus.OK){
            EnemiesDto dto = response.getBody();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody EnemiesDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}

