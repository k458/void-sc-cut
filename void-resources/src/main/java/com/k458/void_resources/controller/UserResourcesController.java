package com.k458.void_resources.controller;

import com.k458.void_resources.model.UserResourcesEntity;
import com.k458.void_resources.service.UserResourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserResourcesController {
    private final UserResourcesService userResourcesService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResourcesEntity> getUserResources(@PathVariable("id") Long id){
        UserResourcesEntity resources = userResourcesService.getUserResourcesById(id).orElse(null);
        if(resources != null){
            return ResponseEntity.ok(resources);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping
    public ResponseEntity<Void> postUserResources(@RequestBody UserResourcesEntity resources){
        userResourcesService.saveUserResources(resources);
        return ResponseEntity.ok().build();
    }
}
