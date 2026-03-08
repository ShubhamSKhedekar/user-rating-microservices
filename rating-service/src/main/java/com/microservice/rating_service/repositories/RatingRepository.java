package com.microservice.rating_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.microservice.rating_service.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

}
