package com.microservice.rating_service.entities;


//import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Document(collection = "ratings")
@Entity
@Table(name = "ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    private String ratingId;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String hotelId;
    @Column(nullable = false)
    private int rating;
    private String comment;

}
