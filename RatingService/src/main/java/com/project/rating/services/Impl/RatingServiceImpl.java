package com.project.rating.services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rating.dtos.RatingDto;
import com.project.rating.enitities.Rating;
import com.project.rating.respositories.RatingRepository;
import com.project.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RatingDto createRating(RatingDto ratingDto) {
       
        Rating rating=modelMapper.map(ratingDto, Rating.class);
        return modelMapper.map(ratingRepository.save(rating),RatingDto.class);
    }

    @Override
    public List<RatingDto> getAllRatings() {
        List<Rating> ratingList= ratingRepository.findAll();
        return ratingList.stream().map(rating->modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingByUser(String userId) {

        List<Rating> ratingList= ratingRepository.findByUserId(userId);
        return ratingList.stream().map(rating->modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
   }

    @Override
    public List<RatingDto> getRatingByHotel(String hotelId) {
       List<Rating> ratingList= ratingRepository.findByHotelId(hotelId);
        return ratingList.stream().map(rating->modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
   }

    @Override
    public RatingDto updateRating(String userId, RatingDto ratingDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRating'");
    }

    @Override
    public void deleteRating(String ratingId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRating'");
    }
    
}
