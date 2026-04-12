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
import java.util.List;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.lang.NullPointerException;
import java.lang.IllegalStateException;

@RestController
@RequestMapping("/user")
public class UserServiceController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fetchUserByIdFallback")
    public ResponseEntity<User> fetchUserById(@PathVariable String id) throws IllegalStateException{    
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } 
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User("NA", "User not present.", "NA", "1234567890", null));
        }
        catch(NullPointerException | IllegalStateException ex) {
            System.out.println("inside catch block of fetchUserById method");
            System.out.println("Exception occurred: " + ex.getClass().getName());
            throw new IllegalStateException("Required service is currently unavailable. Please try again later.");}
        catch (Exception ex) {
            System.out.println("inside catch block of fetchUserById method");
            System.out.println("Exception occurred: " + ex.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new User("NA", "Internal server error occurred.", "NA", "1234567890", null));
        }
    }

    //fallback method for fetchUserById method
    //Fallback method should have same return type and parameters as the original method along with an additional parameter of type Throwable to capture the exception details
    public ResponseEntity<User> fetchUserByIdFallback(String id, Throwable ex) {
        System.out.println("inside fallback method of fetchUserById method");
        System.out.println("Exception occurred: " + ex.getClass().getName());
        User user = User.builder()
                        .userId(id)
                        .userName("NA")
                        .userEmail("NA")
                        .userInfo(ex.getMessage())
                        .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> fetchUserByEmail(@PathVariable String email) {
        try {          
            User user = userService.getUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User("NA", "User not present.", email, "1234567890", null));
        }
        catch (Exception ex) {
            System.out.println("inside catch block of fetchUserByEmail method");
            System.out.println("Exception occurred: " + ex.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new User("NA", "Internal server error occurred.", email, "1234567890", null));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));  
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User existingUser = userService.getUserById(user.getUserId());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User("NA", "User not present.", "NA", "1234567890", null));
        }else{
            existingUser.setUserName(user.getUserName());
            existingUser.setUserEmail(user.getUserEmail());
            existingUser.setUserInfo(user.getUserInfo());
            User updatedUser = userService.updateUser(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id); 
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/fetch-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
