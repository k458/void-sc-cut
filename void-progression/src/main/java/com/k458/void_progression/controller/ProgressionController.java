package com.k458.void_progression.controller;

import com.k458.void_progression.model.HubEntity;
import com.k458.void_progression.model.ProgressionDto;
import com.k458.void_progression.model.ResourceEntity;
import com.k458.void_progression.service.HubService;
import com.k458.void_progression.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progression")
public class ProgressionController {

    private final HubService hubService;
    private final ResourceService resService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgressionDto> get(@PathVariable("id") Long id) {
        ProgressionDto dto = new ProgressionDto();
        dto.setHubEntity(hubService.get(id));
        dto.setResourceEntity(resService.get(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/{id}}")
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody ProgressionDto dto){
        HubEntity hubEntity = dto.getHubEntity();
        if (hubEntity != null){
            hubEntity = hubService.save(hubEntity);
        }
        ResourceEntity resourceEntity = dto.getResourceEntity();
        if (resourceEntity != null){
            resourceEntity = resService.save(resourceEntity);
        }
        return ResponseEntity.ok().build();
    }
}