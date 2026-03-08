package com.microservice.hotel_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @Column(name = "hotel_id", unique = true, nullable = false)
    String hotelId;
    @Column(name = "hotel_name", nullable = false)
    String hotelName;
    @Column(name = "hotel_location", nullable = false)
    String hotelLocation;   
    @Column(name = "hotel_about")
    String hotelAbout;
}
