package com.project.hotel.services;
import java.util.List;

import com.project.hotel.dto.HotelDto;

public interface HotelService {
   
    public HotelDto create(HotelDto hotelDto);

    public List<HotelDto> getAllHotels();
    
    public HotelDto getHotel(String hotelId);

    public void deleteHotel(String hotelId);

    public HotelDto updateHotel(HotelDto hotelDto);
    
}
