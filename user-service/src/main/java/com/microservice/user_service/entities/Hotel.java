package com.microservice.user_service.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    String hotelId;
    String hotelName;
    String hotelLocation;   
    String hotelAbout;
}