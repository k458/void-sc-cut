package com.k458.void_creatures.controller;

import com.k458.void_creatures.model.items.ItemEntity;
import com.k458.void_creatures.model.items.ItemsDto;
import com.k458.void_creatures.service.items.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    @GetMapping("/{id}")
    public ResponseEntity<ItemsDto> get(@PathVariable("id") Long id) {
        ItemsDto dto = new ItemsDto();
        dto.setData(service.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<ItemEntity> save(@PathVariable("id") Long id, @RequestBody ItemEntity entity){
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