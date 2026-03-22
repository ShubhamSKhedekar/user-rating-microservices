package com.microservice.rating_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.rating_service.entities.Rating;
//import com.microservice.rating_service.repositories.IRatingRepositoryMongoDb;
import com.microservice.rating_service.repositories.IRatingRepositoryMySql;
import java.util.UUID;

@Service
public class RatingServiceImpl implements IRatingService {

    // @Autowired
    // private IRatingRepositoryMongoDb ratingRepository;

    @Autowired
    private IRatingRepositoryMySql ratingRepository;

    @Override
    public Optional<Rating> getRatingById(String ratingId) {
        return ratingRepository.findById(ratingId);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Optional<Rating> getRatingByUserIdAndHotelId(String userId, String hotelId) {
        return ratingRepository.findByUserIdAndHotelId(userId, hotelId);
    }

    @Override
    public Rating saveRating(Rating rating) {
        UUID randomRatingId = UUID.randomUUID();
        rating.setRatingId(randomRatingId.toString());
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

    public Rating updateRating(String ratingId, Rating updatedRating) {
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);
        if (existingRating.isPresent()) {
            Rating ratingToUpdate = existingRating.get();
            ratingToUpdate.setUserId(updatedRating.getUserId());
            ratingToUpdate.setHotelId(updatedRating.getHotelId());
            ratingToUpdate.setRating(updatedRating.getRating());
            return ratingRepository.save(ratingToUpdate);
        }
        return null;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
