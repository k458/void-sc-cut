package com.k458.void_gateway.controller;

import com.k458.void_gateway.model.characters.CharactersDto;
import com.k458.void_gateway.service.CharactersService;
import com.k458.void_gateway.service.SecurityService;
import com.k458.void_gateway.service.UserBottleneckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharactersController {

    private final UserBottleneckService userBottleneckService;
    private final SecurityService securityService;
    private final CharactersService service;

    @GetMapping
    public ResponseEntity<CharactersDto> get(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        ResponseEntity<Long> response = securityService.verifyToken(token).block();
        if (response.getStatusCode() == HttpStatus.OK) {
            Long id = response.getBody();
            //if (!userBottleneckService.tryAllowFor(id)) return ResponseEntity.badRequest().build();
            ResponseEntity<CharactersDto> dtoResponse = service.get(id).block();
            if (dtoResponse.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(dtoResponse.getBody());
            }
            //userBottleneckService.finishedFor(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestHeader("Authorization") String authHeader, @RequestBody CharactersDto dto) {
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