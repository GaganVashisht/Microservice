package com.project.rating.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.rating.dtos.RatingDto;

@Service
public interface RatingService {
    
    //create a rating
    public RatingDto createRating(RatingDto ratingDto );
    
    // get all rating
    public List<RatingDto> getAllRatings();
    
    // get all by userId
    public List<RatingDto> getRatingByUser(String userId);

    //get all by hotelId
    public List<RatingDto> getRatingByHotel(String hotelId);
    
    //update a rating by userId
    public RatingDto updateRating(String userId,RatingDto ratingDto);

    // delete a rating by userId
    public void deleteRating(String ratingId,String userId);

}
