package com.microservice.rating_service;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);

		//MongoDB Setup Instructions: (Didn't work with Spring Data MongoDB, so I switched to MySQL)
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
			
		//mvn spring-boot:run

		//----------------------------------------------
		// MySQL Setup Instructions:
		// sudo apt-get update
		// sudo apt-get install -y mysql-server mysql-client

		//sudo service mysql start

		//sudo service mysql status

		//sudo mysql

		//Step 5: Test DB connection (important)
		// mysql -u rating_service -p -h 127.0.0.1 ratingservice_db

		// Password: password

		// Create DB & user:
		// CREATE DATABASE rating_service;
		//CREATE USER 'rating_service'@'%' IDENTIFIED BY 'password';
		// GRANT ALL PRIVILEGES ON ratingservice_db.* TO 'rating_service'@'%';
		// FLUSH PRIVILEGES;

		// # Stop app
		// CTRL + C

		// # Make code changes

		// # Restart
		// mvn spring-boot:run

		//cat target/surefire-reports/*.txt
	}

	// @Bean
    // CommandLineRunner checkMongo(MongoTemplate mongoTemplate) {
    //     return args -> {
    //         System.out.println("🔥 DB Name: " + mongoTemplate.getDb().getName());
    //         System.out.println("🔥 Mongo Details: " + mongoTemplate.getMongoDatabaseFactory());
    //     };
    // }

	//DOWNGRADE SPRINGBOOT VERSION TO 3.2.5
	// sudo apt update
	// sudo apt install openjdk-17-jdk -y
	// sudo update-alternatives --config java
	// export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
	// export PATH=$JAVA_HOME/bin:$PATH
	// java -version
	//mvn clean install

}
