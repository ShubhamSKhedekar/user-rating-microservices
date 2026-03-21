package com.microservice.rating_service.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.rating_service.entities.Rating;

public interface IRatingRepositoryMySql extends JpaRepository<Rating, String> {

    public Optional<Rating> findByRatingId(String ratingId);
    public Optional<Rating> findByUserId(String userId);
    public Optional<Rating> findByHotelId(String hotelId);
    public Optional<Rating> findByUserIdAndHotelId(String userId, String hotelId);
}
