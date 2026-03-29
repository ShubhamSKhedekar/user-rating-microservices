package com.microservice.user_service.feigncleint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.user_service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface IFeignHotelService {

    @GetMapping("/hotels/get/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") String hotelId);
}
