package com.project.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.project.hotel.dto.HotelDto;
import com.project.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping()
    public ResponseEntity<HotelDto> createHotel( @RequestBody HotelDto hotelDto){
       return new ResponseEntity<HotelDto>(hotelService.create(hotelDto),HttpStatus.CREATED);
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelInfo(@PathVariable String hotelId){
        return new ResponseEntity<HotelDto>(hotelService.getHotel(hotelId),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotel(){
        return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.OK);
    }

    @PutExchange
    public ResponseEntity<HotelDto> updateHotelInfo( @RequestBody HotelDto hotelDto){
        return new ResponseEntity<HotelDto>(hotelService.updateHotel(hotelDto),HttpStatus.CREATED);
     } 
     @DeleteMapping
     public ResponseEntity<String> deleteHotel( @PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<String>("Hotel deleted successfully with id "+hotelId,HttpStatus.OK);
     }

}
