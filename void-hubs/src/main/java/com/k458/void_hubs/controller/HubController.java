package com.k458.void_hubs.controller;

import com.k458.void_hubs.model.HubEntity;
import com.k458.void_hubs.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HubController {

    private final HubService hubService;

    @GetMapping("/{id}")
    public ResponseEntity<HubEntity> getById(@PathVariable("id") Long id) {
        HubEntity r = hubService.getByUserId(id).orElse(null);
        if(r == null){
            r = new HubEntity();
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(r);
    }
    @PostMapping("/save")
    public ResponseEntity<HubEntity> save(@RequestBody HubEntity hubEntity){
        hubService.save(hubEntity);
        return ResponseEntity.ok().body(hubEntity);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId){
        hubService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
