package com.k458.void_characters.controller;

import com.k458.void_characters.model.CharacterEntity;
import com.k458.void_characters.model.CharactersDto;
import com.k458.void_characters.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDto> getById(@PathVariable("id") Long id) {
        CharactersDto dto = new CharactersDto();
        dto.setCharacters(characterService.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/save")
    public ResponseEntity<CharacterEntity> save(@RequestBody CharacterEntity characterEntity){
        if (characterEntity.getUserId() == null){
            System.out.println("To save character userId must be specified");
            return ResponseEntity.badRequest().build();
        }
        characterService.save(characterEntity);
        return ResponseEntity.ok().body(characterEntity);
    }

    @DeleteMapping("/delete/{userId}/{characterId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("characterId") Long characterId){
        characterService.delete(userId, characterId);
        return ResponseEntity.ok().build();
    }

}
