package com.k458.void_controller.controller;

import com.k458.void_controller.model.CreaturesDto;
import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.service.PlayerSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/creatures")
public class CreaturesController {
    private final PlayerSessionService playerSessionService;

    @GetMapping("/{id}")
    public ResponseEntity<CreaturesDto> get(@PathVariable("id") Long id) {
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null){
            System.out.println("ERROR failed to retrieve creatures for: " +id);
            return ResponseEntity.notFound().build();
        }
        CreaturesDto dto = new CreaturesDto();
        dto.setCharacterEntities(session.getCharactersList());
        dto.setItemEntities(session.getItemsList());
        dto.setEnemyEntities(session.getEnemiesList());
        return ResponseEntity.ok(dto);
    }
}