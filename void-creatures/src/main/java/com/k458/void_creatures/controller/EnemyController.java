package com.k458.void_creatures.controller;

import com.k458.void_creatures.model.enemies.EnemiesDto;
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
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody EnemiesDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}
