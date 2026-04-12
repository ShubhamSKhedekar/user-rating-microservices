package com.microservice.hotel_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @RequestMapping(method = RequestMethod.GET, path = "/get")
    public ResponseEntity<List<String>> getStaff() {
        return ResponseEntity.ok
        (List.of("John Doe", "Jane Smith", "Alice Johnson", "Bob Brown"));
    }
}
