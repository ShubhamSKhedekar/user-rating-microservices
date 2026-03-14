package com.microservice.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		
		// sudo apt-get update
		// sudo apt-get install -y mysql-server mysql-client

		//sudo service mysql start

		//sudo service mysql status

		//sudo mysql

		//Step 5: Test DB connection (important)
		// mysql -u user_service -p -h 127.0.0.1 userservice_db

		// Password: password

		// Create DB & user:
		// CREATE DATABASE user_service;
		// CREATE USER 'userservice'@'localhost' IDENTIFIED BY 'password';
		// GRANT ALL PRIVILEGES ON user_service.* TO 'userservice'@'localhost';
		// FLUSH PRIVILEGES;


		// # Stop app
		// CTRL + C

		// # Make code changes

		// # Restart
		// mvn spring-boot:run

		//cat target/surefire-reports/*.txt
	}

}
