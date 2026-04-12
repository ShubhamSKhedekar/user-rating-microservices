package com.microservice.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
 

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
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
		// CREATE DATABASE userservice_db;
		// CREATE USER 'userservice'@'localhost' IDENTIFIED BY 'password';
		// GRANT ALL PRIVILEGES ON userservice_db.* TO 'userservice'@'localhost';
		// FLUSH PRIVILEGES;

		// CREATE USER 'user_service'@'127.0.0.1' IDENTIFIED BY 'password';
		// GRANT ALL PRIVILEGES ON userservice_db.* TO 'user_service'@'127.0.0.1';
		// FLUSH PRIVILEGES;

		// # Stop app
		// CTRL + C

		// # Make code changes

		// # Restart
		// mvn spring-boot:run

		// cat target/surefire-reports/*.txt

		// DOWNGRADE SPRINGBOOT VERSION TO 3.2.5
		// sudo apt update
		// sudo apt install openjdk-17-jdk -y
		// sudo update-alternatives --config java
		// export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
		// export PATH=$JAVA_HOME/bin:$PATH
		// java -version
		// mvn clean install
	}

}
