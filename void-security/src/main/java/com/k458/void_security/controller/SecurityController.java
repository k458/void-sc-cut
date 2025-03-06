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
        return new ResponseEntity<>( userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/verifyToken/{token}")
    public ResponseEntity<Long> verifyToken(@PathVariable("token") String token) {
        System.out.println("Token "+token+" verified");
        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @PostMapping("/recreateToken")
    public ResponseEntity<TokenTime> recreateToken(@RequestBody String token) {
        TokenTime tt = new TokenTime("aaa", Variables.tokenLifeTime);
        return new ResponseEntity<>(tt, HttpStatus.OK);
    }

    @PostMapping("/createToken")
    public ResponseEntity<TokenTime> createToken(@RequestBody UserNamePassword unp) {
        UserEntity userEntity = userService.getUserByNameAndPassword(unp.getName(), passwordEncoder.encode(unp.getPassword())).orElse(null);
        if (userEntity != null) {
            TokenTime tt = new TokenTime("aaa", Variables.tokenLifeTime);
            return new ResponseEntity<>(tt, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TokenTime(), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserNamePassword unp) {
        System.out.println(unp.getName()+" "+unp.getPassword());
        UserEntity userEntity = userService.getUserByName(unp.getName()).orElse(null);
        if (userEntity == null) {
            UserEntity newUser = new UserEntity(unp.getName(), passwordEncoder.encode(unp.getPassword()), "USER");
            String name = userService.addUser(newUser).getName();
            return new ResponseEntity<>("User "+name+" was created", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with name "+unp.getName()+" already exists", HttpStatus.FORBIDDEN);
    }
}
