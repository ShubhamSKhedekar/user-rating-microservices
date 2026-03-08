package com.microservice.user_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name", length = 100)
    private String userName;
    @Column(name = "user_email", unique = false, nullable = false)
    private String userEmail;
    @Column(name = "user_info", nullable = true)
    private String userInfo;

    public User(String userName, String userEmail, String userInfo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userInfo = userInfo;
    }
   
}
