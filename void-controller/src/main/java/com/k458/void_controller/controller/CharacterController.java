package com.k458.void_controller.controller;

import com.k458.void_controller.model.characters.CharacterEntity;
import com.k458.void_controller.model.characters.CharactersDto;
import com.k458.void_controller.service.PlayerSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final PlayerSessionService playerSessionService;

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDto> getAll(@PathVariable("id") Long id) {
        CharactersDto ret = new CharactersDto();
        ret.setData(playerSessionService.getSession(id).getCharactersList());
        return ResponseEntity.ok(ret);
    }
//    @PostMapping("/{id}")
//    public ResponseEntity<CharacterEntity> save(@PathVariable("id") Long id, @RequestBody CharacterEntity entity){
//        CharacterEntity ret = playerSessionService.getSession(id).updateCharacter(entity);
//        if (ret == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(ret);
//    }
}
