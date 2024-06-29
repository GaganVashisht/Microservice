package com.project.user.service.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.service.dto.HotelDto;
import com.project.user.service.dto.RatingDto;
import com.project.user.service.dto.UserDto;
import com.project.user.service.entites.User;
import com.project.user.service.exceptions.ResourceAlreadyExistException;
import com.project.user.service.exceptions.ResourceNotFoundException;
import com.project.user.service.external.services.HotelService;
import com.project.user.service.external.services.RatingService;
import com.project.user.service.repositories.UserRepository;
import com.project.user.service.services.UserService;

@Service
public class UserServicesImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    // private Logger logger=LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDto saveUser(UserDto userDto) {
        String uuid=UUID.randomUUID().toString();
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ResourceAlreadyExistException("User email already Exists: "+userDto.getEmail());
        }
        // userDto.setUserId(uuid);
        User user=modelMapper.map(userDto, User.class);
        user.setUserId(uuid);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList=userRepository.findAll();
        return userList.stream()
                .map(user ->{ 
                    
                    UserDto userDto=modelMapper.map(user, UserDto.class);

                    List<RatingDto> resList=ratingService.getRatings(userDto.getUserId());
                    List<RatingDto> userRatings=resList.stream().map(rating->{                
                        // using feign client
                        HotelDto hotelDto=hotelService.getHotel(rating.getHotelId());
                        
                        rating.setHotel(hotelDto);
                        return rating;
                    }).collect(Collectors.toList());
                    userDto.setRatings(userRatings);
                    return userDto;
                })
                .collect(Collectors.toList());
            
    }

    @Override
    public UserDto getUser(String userId) {
        
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found: "+userId));
        
        // using rest template
        // RatingDto[] resObj=restTemplate.getForObject("http://RATINGSERVICE/ratings/user/"+userId, RatingDto[].class);
        // List<RatingDto> resList=Arrays.asList(resObj);
        // logger.info("{}",resList);
        
        // using Feign client
        List<RatingDto> resList=ratingService.getRatings(userId);
        List<RatingDto> userRatings=resList.stream().map(rating->{
            // using rest template
            // ResponseEntity<HotelDto> forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), HotelDto.class);
            // HotelDto hotelDto=forEntity.getBody();
            // logger.info("response status code: {}",forEntity.getStatusCode());
            

            // using feign client
            HotelDto hotelDto=hotelService.getHotel(rating.getHotelId());
            
            rating.setHotel(hotelDto);
            return rating;
        }).collect(Collectors.toList());
        UserDto userDto= modelMapper.map(user, UserDto.class);  
        userDto.setRatings(userRatings);
        return userDto;

    }

    @Override
    public void deleteUser(String userId) {
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User id not found: "+userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ResourceAlreadyExistException("User email already Exists: "+userDto.getEmail());
        }
        // userDto.setUserId(uuid);
        User user=modelMapper.map(userDto, User.class);
        // user.setUserId(uuid);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

   
    
}
