package com.microservice.user_service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
 
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String comment;
    private Hotel hotel; //to hold details of the hotel for which the rating is given
}
