package com.k458.void_items.controller;

import com.k458.void_items.model.ItemEntity;
import com.k458.void_items.model.UserItemsDto;
import com.k458.void_items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<UserItemsDto> getById(@PathVariable("id") Long id) {
        UserItemsDto dto = new UserItemsDto();
        dto.setUserId(id);
        dto.setItems(itemService.getItemsByUserId(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/saveItem")
    public ResponseEntity<ItemEntity> saveItem(@RequestBody ItemEntity itemEntity){
        if (itemEntity.getUserId() == null){
            System.out.println("To save item userId must be specified");
            return ResponseEntity.badRequest().build();
        }
        itemService.saveItem(itemEntity);
        return ResponseEntity.ok().body(itemEntity);
    }

    @DeleteMapping("/deleteItem/{userId}/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId){
        itemService.deleteItem(userId, itemId);
        return ResponseEntity.ok().build();
    }

}
