package com.project.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.user.service.dto.RatingDto;

@FeignClient("RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    List<RatingDto> getRatings(@PathVariable String userId);
}
