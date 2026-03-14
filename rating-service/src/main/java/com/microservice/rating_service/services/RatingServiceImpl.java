package com.microservice.rating_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.rating_service.entities.Rating;
import com.microservice.rating_service.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Optional<Rating> getRatingById(String ratingId) {
        return ratingRepository.findById(ratingId);
    }

    @Override
    public Optional<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public Optional<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Optional<Rating> getRatingByUserIdAndHotelId(String userId, String hotelId) {
        return ratingRepository.findByUserIdAndHotelId(userId, hotelId);
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating deleteRatingById(String ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (rating.isPresent()) {
            ratingRepository.deleteById(ratingId);
            return rating.get();
        }
        return null;
    }
}
