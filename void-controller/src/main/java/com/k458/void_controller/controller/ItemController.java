package com.k458.void_controller.controller;

import com.k458.void_controller.model.items.ItemsDto;
import com.k458.void_controller.service.creatures.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    @GetMapping("/{id}")
    public ResponseEntity<ItemsDto> get(@PathVariable("id") Long id) {
        ResponseEntity<ItemsDto> response = service.get(id).block();
        if (response.getStatusCode() == HttpStatus.OK){
            ItemsDto dto = response.getBody();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable("id") Long id, @RequestBody ItemsDto dto){
        service.save(id, dto);
        return ResponseEntity.ok().build();
    }
}