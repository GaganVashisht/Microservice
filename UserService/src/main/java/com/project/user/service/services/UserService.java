package com.project.user.service.services;



import java.util.List;


import com.project.user.service.dto.UserDto;

// import com.project.user.service.entites.UserDto;


public interface UserService {

    UserDto saveUser(UserDto user);
    
    List<UserDto> getAllUser();

    UserDto getUser(String userId);

    void deleteUser(String userId);

    UserDto updateUser(UserDto user);
}
