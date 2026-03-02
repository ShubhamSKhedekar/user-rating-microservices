package com.microservice.user_service.controllers;

import com.microservice.user_service.entities.User;
import com.microservice.user_service.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.user_service.exceptions.ResourceNotFoundException;


@RestController
@RequestMapping("/user-service")
public class UserServiceController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable String id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User("NA", "User not present.", "NA", "1234567890"));
        }
        catch (Exception ex) {
            System.out.println("inside catch block of fetchUserById method");
            System.out.println("Exception occurred: " + ex.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new User("NA", "Internal server error occurred.", "NA", "1234567890"));
        }
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> fetchUserByEmail(@PathVariable String email) {
        try {          
            User user = userService.getUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User("NA", "User not present.", email, "1234567890"));
        }
        catch (Exception ex) {
            System.out.println("inside catch block of fetchUserByEmail method");
            System.out.println("Exception occurred: " + ex.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new User("NA", "Internal server error occurred.", email, "1234567890"));
        }
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));  
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id); 
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
