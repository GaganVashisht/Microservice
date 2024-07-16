package com.project.user.service.controller;

import java.util.List;

import com.project.user.service.dto.UserDto;
import com.project.user.service.services.UserService;

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
