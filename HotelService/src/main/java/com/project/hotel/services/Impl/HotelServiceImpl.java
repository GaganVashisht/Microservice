package com.project.hotel.services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.dto.HotelDto;
import com.project.hotel.entities.Hotel;
import com.project.hotel.exceptions.ResourceNotFoundException;
import com.project.hotel.repositories.HotelRepository;
import com.project.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;    

    @Override
    public HotelDto create(HotelDto hotelDto) {
        String uuid= UUID.randomUUID().toString();
        Hotel hotel=modelMapper.map(hotelDto, Hotel.class);
        hotel.setId(uuid);
        hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotelList=hotelRepository.findAll();
        return hotelList.stream().map(hotel->modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());    
    }

    @Override
    public HotelDto getHotel(String hotelId) {
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found: "+hotelId));

        return modelMapper.map(hotel, HotelDto.class);
    
    }

    @Override
    public void deleteHotel(String hotelId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel with given id not found: "+hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }   

    @Override
    public HotelDto updateHotel(HotelDto hotelDto) {
        if(!hotelRepository.existsById(hotelDto.getId())){
            throw new ResourceNotFoundException("Hotel with given id not found: "+hotelDto.getId());
        }
        Hotel hotel=modelMapper.map(hotelDto,Hotel.class);
        return modelMapper.map(hotelRepository.save(hotel),HotelDto.class);
    }
    
}
