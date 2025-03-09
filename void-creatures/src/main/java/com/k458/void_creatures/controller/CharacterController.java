package com.k458.void_creatures.controller;

import com.k458.void_creatures.model.characters.CharacterEntity;
import com.k458.void_creatures.model.characters.CharactersDto;
import com.k458.void_creatures.service.characters.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDto> get(@PathVariable("id") Long id) {
        CharactersDto dto = new CharactersDto();
        dto.setData(service.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CharacterEntity> save(@PathVariable("id") Long id, @RequestBody CharacterEntity entity){
        if (entity.getUserId() == null){
            entity.setUserId(id);
        }
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/delete/{userId}/{localId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("localId") Long localId){
        service.delete(userId, localId);
        return ResponseEntity.ok().build();
    }

}
