package com.microservice.rating_service.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rating_service.entities.Rating;
import com.microservice.rating_service.services.IRatingService;

@RestController
@RequestMapping("/ratings")
public class RatingServiceController {

    @Autowired
    private IRatingService ratingService;

    @GetMapping("/get/id/{ratingId}")
    public ResponseEntity<Rating> fetchRatingById(@PathVariable String ratingId) {
        Optional<Rating> rating = ratingService.getRatingById(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body(rating.get());
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<Rating> fetchRatingByUserId(@PathVariable String userId) {
        Optional<Rating> rating = ratingService.getRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(rating.get());     
    }

    @GetMapping("/get/hotel/{hotelId}")
    public ResponseEntity<Rating> fetchRatingByHotelId(@PathVariable String hotelId) {
        Optional<Rating> rating = ratingService.getRatingByHotelId(hotelId);    
        return ResponseEntity.status(HttpStatus.OK).body(rating.get());
    }

    @GetMapping("/get/user/{userId}/hotel/{hotelId}")
    public ResponseEntity<Rating> fetchRatingByUserIdAndHotelId(@PathVariable String userId, @PathVariable String hotelId) {
        Optional<Rating> rating = ratingService .getRatingByUserIdAndHotelId(userId, hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(rating.get()); 
    }

    @PostMapping("/save")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);     
    }

    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<Rating> deleteRating(@PathVariable String ratingId) {
        Rating rating = ratingService.deleteRatingById(ratingId);
        if(rating == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rating);
        }
        return ResponseEntity.status(HttpStatus.OK).body(rating);
    }
    
}
