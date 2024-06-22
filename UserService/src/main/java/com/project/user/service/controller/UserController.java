package com.project.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.service.dto.UserDto;
import com.project.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto userDto = userService.saveUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        UserDto userDto = userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userService.getAllUser();
        return ResponseEntity.ok(userDtos);
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteUserById(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted Successfully");
    }
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        UserDto userDto = userService.updateUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    } 
}
