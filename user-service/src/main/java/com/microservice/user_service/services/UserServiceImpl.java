package com.microservice.user_service.services;

import com.microservice.user_service.entities.Hotel;
import com.microservice.user_service.entities.Rating;
import com.microservice.user_service.entities.User;
import com.microservice.user_service.repositories.IUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user_service.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            //fetch details of ratings and hotels from respective microservices using restTemplate
            //get ratings of the user from rating-service
            //example url: https://potential-cod-7qq9jvjg7v6h95g-8082.app.github.dev/ratings/get/user/b49ed02f-b88b-4288-aec9-4fa0d470ce6f
            //String ratingsUrl = "https://potential-cod-7qq9jvjg7v6h95g-8082.app.github.dev/ratings/get/user/" + id;
            //Making ratingsUrl dynamic using - RATING-SERVICE
            String ratingsUrl = "http://RATING-SERVICE/ratings/get/user/" + id;
            
            Rating[] ratings = restTemplate.getForObject(ratingsUrl, Rating[].class);
            List<Rating> ratingsList = Arrays.asList(ratings);

            logger.info("Ratings fetched successfully for user with id: " + id);
            logger.info("Ratings: " + ratingsList);

            ratingsList.stream().forEach(rating -> {
                //get hotel details for each rating from hotel-service
                //Example url: https://potential-cod-7qq9jvjg7v6h95g-8081.app.github.dev/hotels/get/606054b3-8229-4d01-b821-2aaf86064810
                //String hotelUrl = "https://potential-cod-7qq9jvjg7v6h95g-8081.app.github.dev/hotels/get/" + rating.getHotelId();      
                //Making hotelUrl dynamic using - HOTEL-SERVICE
                String hotelUrl = "http://HOTEL-SERVICE/hotels/get/" + rating.getHotelId();

                try {
                    Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);
                    logger.info("Hotel details fetched successfully for hotel with id: " + rating.getHotelId());
                    logger.info("Hotel details: " + hotel);
                    rating.setHotel(hotel);
                } catch (Exception ex) {
                    logger.severe("Failed to fetch hotel details for hotel with id: " + rating.getHotelId());
                    logger.severe("Exception occurred: " + ex.getClass().getName() + " - " + ex.getMessage());
                }
            });

            optionalUser.get().setRatings(ratingsList);
            return optionalUser.get();
        } else {    
            throw new ResourceNotFoundException("User not present.");
        }
    }

    @Override
    public User getUserByEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail).orElseThrow(()-> {
            System.out.println("Exception occurred: ResourceNotFoundException");
            return new ResourceNotFoundException("User not present.");
        });
    }

    @Override
    public User saveUser(User user) {
        String randonUserId = java.util.UUID.randomUUID().toString();
        user.setUserId(randonUserId);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
