package com.project.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.user.service.dto.HotelDto;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    HotelDto getHotel(@PathVariable String hotelId);
}
