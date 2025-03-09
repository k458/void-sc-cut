package com.k458.void_security.controller;

import com.k458.void_security.config.Variables;
import com.k458.void_security.model.TokenTime;
import com.k458.void_security.model.UserEntity;
import com.k458.void_security.model.UserNamePassword;
import com.k458.void_security.service.JwtService;
import com.k458.void_security.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> users() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/verifyToken/{token}")
    public ResponseEntity<Long> verifyToken(@PathVariable("token") String token) {
        try{
            String userIds = jwtService.parseToken(token);
            Long userId = Long.parseLong(userIds);
            UserEntity entity = userService.getById(userId);
            if (entity != null){
                return ResponseEntity.ok(userId);
            }
        } catch (Exception e){
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/recreateToken/{token}")
    public ResponseEntity<TokenTime> recreateToken(@PathVariable("token") String token) {
        try{
            String userIds = jwtService.parseToken(token);
            Long userId = Long.parseLong(userIds);
            UserEntity entity = userService.getById(userId);
            if (entity != null){
                String tokenNew = jwtService.createToken(userId.toString());
                return ResponseEntity.ok(new TokenTime(tokenNew, Variables.tokenLifeTime));
            }
        } catch (Exception e){
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenTime> login(@RequestBody UserNamePassword unp) {
        UserEntity entity = userService.getByName(unp.getName());
        if (entity != null) {
            if (!passwordEncoder.matches(unp.getPassword(), entity.getPassword())) return ResponseEntity.badRequest().build();
            String tokenNew = jwtService.createToken(entity.getId().toString());
            return ResponseEntity.ok(new TokenTime(tokenNew, Variables.tokenLifeTime));
        }
//        System.out.println("---------------------------------------notfound"+unp.toString());
//        System.out.println(":::"+passwordEncoder.encode(unp.getPassword()));
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<TokenTime> createUser(@RequestBody UserNamePassword unp) {
        UserEntity entity = userService.getByName(unp.getName());
        if (entity == null) {
            UserEntity newUser = new UserEntity(unp.getName(), passwordEncoder.encode(unp.getPassword()), "USER");
            newUser = userService.save(newUser);
            //System.out.println("---------------------------------------"+newUser.toString());
            String tokenNew = jwtService.createToken(newUser.getId().toString());
            //System.out.println("---------------------------------------"+newUser.toString());
            return ResponseEntity.ok(new TokenTime(tokenNew, Variables.tokenLifeTime));
        }
        return ResponseEntity.badRequest().build();
    }
}
