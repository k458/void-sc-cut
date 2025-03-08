package com.k458.void_rooms.controller;

import com.k458.void_rooms.model.RoomEntity;
import com.k458.void_rooms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<RoomEntity> getById(@PathVariable("id") Long id) {
        RoomEntity r = roomService.getByUserId(id).orElse(null);
        if(r == null){
            r = new RoomEntity();
            r.setIsActive(false);
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(r);
    }
    @PostMapping("/save")
    public ResponseEntity<RoomEntity> save(@RequestBody RoomEntity roomEntity){
        roomService.save(roomEntity);
        return ResponseEntity.ok().body(roomEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId){
        roomService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
