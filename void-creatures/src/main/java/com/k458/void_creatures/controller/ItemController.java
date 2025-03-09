package com.k458.void_creatures.controller;

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
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody ItemsDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}