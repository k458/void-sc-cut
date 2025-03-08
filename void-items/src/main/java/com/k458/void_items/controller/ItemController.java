package com.k458.void_items.controller;

import com.k458.void_items.model.ItemEntity;
import com.k458.void_items.model.ItemsDto;
import com.k458.void_items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemsDto> getById(@PathVariable("id") Long id) {
        ItemsDto dto = new ItemsDto();
        dto.setItems(itemService.getByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/save")
    public ResponseEntity<ItemEntity> save(@RequestBody ItemEntity itemEntity){
        if (itemEntity.getUserId() == null){
            System.out.println("To save item userId must be specified");
            return ResponseEntity.badRequest().build();
        }
        itemService.save(itemEntity);
        return ResponseEntity.ok().body(itemEntity);
    }

    @DeleteMapping("/delete/{userId}/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId){
        itemService.delete(userId, itemId);
        return ResponseEntity.ok().build();
    }

}
