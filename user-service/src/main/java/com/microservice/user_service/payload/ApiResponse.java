package main.java.com.microservice.user_service.payload;

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
