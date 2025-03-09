package com.k458.void_controller.controller;

import com.k458.void_controller.model.progression.ProgressionDto;
import com.k458.void_controller.service.progression.ProgressionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progression")
public class ProgressionController {
    private final ProgressionService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProgressionDto> get(@PathVariable("id") Long id) {
        ResponseEntity<ProgressionDto> response = service.get(id).block();
        if (response.getStatusCode() == HttpStatus.OK){
            ProgressionDto dto = response.getBody();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody ProgressionDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}