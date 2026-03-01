package com.microservice.user_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String userId;
    @Column(length = 100)
    private String userName;
    @Column(unique = false, nullable = false)
    private String userEmail;
    @Column(nullable = true)
    private String userInfo;
   
}
