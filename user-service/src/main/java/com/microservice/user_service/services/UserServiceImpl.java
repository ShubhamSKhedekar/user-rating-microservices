package com.microservice.user_service.services;

import com.microservice.user_service.entities.User;
import com.microservice.user_service.repositories.IUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.user_service.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(()-> {
            System.out.println("Exception occurred: ResourceNotFoundException");
            return new ResourceNotFoundException("User not present.");
        });
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
