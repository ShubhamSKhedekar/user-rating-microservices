package com.microservice.user_service.services;

import java.util.List;
import com.microservice.user_service.entities.User;


public interface IUserService {

    public User getUserById(String id);
    public User getUserByEmail(String userEmail);
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUserById(String id);
    public List<User> getAllUsers();
}
