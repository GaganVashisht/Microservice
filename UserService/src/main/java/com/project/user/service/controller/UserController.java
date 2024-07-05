package com.project.user.service.controller;

import java.util.List;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto userDto = userService.saveUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    // @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        UserDto userDto = userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // fallback method for circuit breaker
    public ResponseEntity<UserDto> ratingHotelFallback(String userId,Exception ex){
        logger.info("Fallback is executed because service is down",ex.getMessage());
        UserDto user=UserDto.builder().name("Dummy").email("dummyemail@gmail.com").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
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
