package com.microservice.rating_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);

		//docker run -d -p 27017:27017 --name mongodb mongo
		//docker start mongodb
		//docker ps

		//yml
		//spring.data.mongodb.uri=mongodb://localhost:27017/ratingservice_db

		// You should see something like:
		// CONTAINER ID   IMAGE     NAME
		// xxxxxxx        mongo     mongodb

		// Now open MongoDB shell inside the container:
		// docker exec -it mongodb mongosh

		// Now you are inside MongoDB shell.

		// Check databases
		// show dbs

		// Use your database
		// use ratingdb

		// Check collections
		// show collections		
		
		//db.ratings.find()
			
		// mvn spring-boot:run
	}

}
