package com.project.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel.entities.Hotel;

public interface HotelRepository  extends JpaRepository<Hotel,String>{
    
}
