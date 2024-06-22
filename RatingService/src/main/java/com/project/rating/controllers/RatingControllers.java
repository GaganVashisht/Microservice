package com.project.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rating.dtos.RatingDto;
import com.project.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {
    
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> create(@RequestBody RatingDto ratingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(ratingDto));
    }
    @GetMapping
    public ResponseEntity<List<RatingDto>> getRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingByUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUser(userId));
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getRatingByHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotel(hotelId));
    }

}
