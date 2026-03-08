package com.microservice.hotel_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.hotel_service.entities.Hotel;
import com.microservice.hotel_service.services.IHotelService;

@RestController
@RequestMapping("/hotels")
public class HotelServiceController {

    @Autowired
    private IHotelService hotelService;

    @GetMapping("/get/{hotelId}")
    public ResponseEntity<Hotel> fetchHotelById(@PathVariable String hotelId) {
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
        Hotel hotelResponse = hotel.get();
        return ResponseEntity.status(HttpStatus.OK).body(hotelResponse);
    }

    @GetMapping("/get/all-hotels")
    public ResponseEntity<List<Hotel>> fetchAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }

    @PostMapping("/create-hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    } 
    
    //to-be-implemented
    @DeleteMapping("/delete-hotel/{hotelId}")
    public ResponseEntity<String> deleteHotelById(@PathVariable String hotelId) {
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body("Hotel with id " + hotelId + " deleted successfully");
    }
}