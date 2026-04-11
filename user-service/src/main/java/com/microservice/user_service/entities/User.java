package com.microservice.user_service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @Column(name = "userId")
    private String userId;
    @Column(name = "userName", length = 100)
    private String userName;
    @Column(name = "userEmail", unique = false, nullable = false)
    private String userEmail;
    @Column(name = "userInfo", nullable = true)
    private String userInfo;

    @Transient
    private List<Rating> ratings = new ArrayList<>();   

    public User(String userName, String userEmail, String userInfo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userInfo = userInfo;
    }
   
}
