package com.k458.void_controller.controller;

import com.k458.void_controller.model.characters.CharactersDto;
import com.k458.void_controller.service.creatures.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDto> get(@PathVariable("id") Long id) {
        ResponseEntity<CharactersDto> response = service.get(id).block();
        if (response.getStatusCode() == HttpStatus.OK){
            CharactersDto dto = response.getBody();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody CharactersDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}
