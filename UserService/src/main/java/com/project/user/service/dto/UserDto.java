package com.project.user.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    
    private String userId;
    private String name;
    private String email;
    private List<RatingDto> ratings;
}
