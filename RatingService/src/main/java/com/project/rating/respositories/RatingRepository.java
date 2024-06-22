package com.project.rating.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.rating.enitities.Rating;
import java.util.List;


@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {
    
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
