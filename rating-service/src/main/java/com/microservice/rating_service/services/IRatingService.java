package com.microservice.rating_service.services;

import java.util.List;
import java.util.Optional;
import com.microservice.rating_service.entities.Rating;

public interface IRatingService {

    public Optional<Rating> getRatingById(String ratingId);
    public List<Rating> getRatingByUserId(String userId);
    public List<Rating> getRatingByHotelId(String hotelId);
    public Optional<Rating> getRatingByUserIdAndHotelId(String userId, String hotelId);
    public Rating saveRating(Rating rating);
    public Rating deleteRatingById(String ratingId);
    public Rating updateRating(String ratingId, Rating updatedRating);
    public java.util.List<Rating> getAllRatings();
}
