package com.k458.void_controller.controller;

import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.model.progression.ProgressionDto;
import com.k458.void_controller.service.PlayerSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progression")
public class ProgressionController {
    private final PlayerSessionService playerSessionService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgressionDto> get(@PathVariable("id") Long id) {
        PlayerSession session = playerSessionService.getSession(id);
        if (session == null){
            System.out.println("ERROR failed to retrieve progression for: " +id);
            return ResponseEntity.notFound().build();
        }
        ProgressionDto dto = new ProgressionDto();
        dto.setDungeonEntity(session.getDungeonEntity());
        dto.setHubEntity(session.getHubEntity());
        dto.setResourceEntity(session.getResourceEntity());
        dto.setRoomEntity(session.getRoomEntity());
        return ResponseEntity.ok(dto);
    }
}