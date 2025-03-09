package com.k458.void_gateway.controller;


import com.k458.void_gateway.model.items.ItemsDto;
import com.k458.void_gateway.service.ItemsService;
import com.k458.void_gateway.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemsController {

    private final SecurityService securityService;
    private final ItemsService service;

    @GetMapping
    public ResponseEntity<ItemsDto> get(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        ResponseEntity<Long> response = securityService.verifyToken(token).block();
        if (response.getStatusCode() == HttpStatus.OK) {
            Long id = response.getBody();
            ResponseEntity<ItemsDto> dtoResponse = service.get(id).block();
            if (dtoResponse.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(dtoResponse.getBody());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestHeader("Authorization") String authHeader, @RequestBody ItemsDto dto) {
        String token = authHeader.replace("Bearer ", "");
        ResponseEntity<Long> response = securityService.verifyToken(token).block();
        if (response.getStatusCode() == HttpStatus.OK) {
            Long id = response.getBody();
            ResponseEntity<Void> dtoResponse = service.save(id, dto).block();
            if (dtoResponse.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}