package com.microservice.user_service.exceptions;

import com.microservice.user_service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;    

@ControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ex.getMessage())
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
    //     ApiResponse apiResponse = ApiResponse.builder()
    //             .message(ex.getMessage()+" Please contact support.")
    //             .success(true)
    //             .status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .build();
    //     return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

}
