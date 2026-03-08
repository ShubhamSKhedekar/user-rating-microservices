package com.microservice.hotel_service.services;

import java.util.List;
import java.util.Optional;
import com.microservice.hotel_service.entities.Hotel;

public interface IHotelService {

    public Optional<Hotel> getHotelById(String hotelId);

    public List<Hotel> getAllHotels();

    public Hotel createHotel(Hotel hotel);

    public Hotel deleteHotelById(String hotelId);

}
