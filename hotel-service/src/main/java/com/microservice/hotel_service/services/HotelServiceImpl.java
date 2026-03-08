package com.microservice.hotel_service.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.hotel_service.entities.Hotel;
import com.microservice.hotel_service.exceptions.NoResourceFoundException;
import com.microservice.hotel_service.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Optional<Hotel> getHotelById(String hotelId) {
        return Optional.ofNullable(hotelRepository.findById(hotelId).orElseThrow(() -> new NoResourceFoundException("Hotel with id " + hotelId + " not found!!")))   ;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel deleteHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NoResourceFoundException("Hotel with id " + hotelId + " not found!!"));
        hotelRepository.delete(hotel);
        return hotel;           
    }

}
