package com.microservice.user_service.payload;
import lombok.*;
import org.springframework.http.HttpStatus; 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

}
