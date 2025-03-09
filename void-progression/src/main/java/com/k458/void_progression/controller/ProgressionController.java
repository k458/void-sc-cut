package com.k458.void_progression.controller;

import com.k458.void_progression.model.HubEntity;
import com.k458.void_progression.model.ProgressionDto;
import com.k458.void_progression.service.DungeonService;
import com.k458.void_progression.service.HubService;
import com.k458.void_progression.service.ResourceService;
import com.k458.void_progression.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progression")
public class ProgressionController {

    private final DungeonService dunService;
    private final HubService hubService;
    private final ResourceService resService;
    private final RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgressionDto> get(@PathVariable("id") Long id) {
        ProgressionDto dto = new ProgressionDto();
        dto.setDungeonEntity(dunService.get(id));
        dto.setHubEntity(hubService.get(id));
        dto.setResourceEntity(resService.get(id));
        dto.setRoomEntity(roomService.get(id));;
        return ResponseEntity.ok(dto);
    }
//    @PostMapping("/{id}}")
//    public ResponseEntity<HubEntity> save(@PathVariable("id") Long id, @RequestBody HubEntity entity){
//        if (entity.getId() == null || !entity.getId().equals(id)){
//            entity.setId(id);
//        }
//        return ResponseEntity.ok(service.save(entity));
//    }
}