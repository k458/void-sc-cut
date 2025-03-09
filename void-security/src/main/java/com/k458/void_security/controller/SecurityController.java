package com.k458.void_security.controller;

import com.k458.void_security.config.Variables;
import com.k458.void_security.model.TokenTime;
import com.k458.void_security.model.UserEntity;
import com.k458.void_security.model.UserNamePassword;
import com.k458.void_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> users() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/verifyToken/{token}")
    public ResponseEntity<Long> verifyToken(@PathVariable("token") String token) {
        if (token.equals("aaa")){
            return ResponseEntity.ok(1L);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/recreateToken/{token}")
    public ResponseEntity<TokenTime> recreateToken(@PathVariable("token") String token) {
        TokenTime tokenTime = new TokenTime("aaa", Variables.tokenLifeTime);
        return ResponseEntity.ok(tokenTime);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenTime> login(@RequestBody UserNamePassword unp) {
        UserEntity entity = userService.getByNameAndPassword(unp.getName(), passwordEncoder.encode(unp.getPassword()));
        if (entity != null) {
            TokenTime tokenTime = new TokenTime("aaa", Variables.tokenLifeTime);
            return ResponseEntity.ok(tokenTime);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<TokenTime> createUser(@RequestBody UserNamePassword unp) {
        UserEntity entity = userService.getByName(unp.getName());
        if (entity == null) {
            UserEntity newUser = new UserEntity(unp.getName(), passwordEncoder.encode(unp.getPassword()), "USER");
            userService.save(newUser);
            TokenTime tokenTime = new TokenTime("aaa", Variables.tokenLifeTime);
            return ResponseEntity.ok(tokenTime);
        }
        return ResponseEntity.badRequest().build();
    }
}
