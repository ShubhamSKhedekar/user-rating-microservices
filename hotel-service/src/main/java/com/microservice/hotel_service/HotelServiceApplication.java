package com.microservice.hotel_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);

		//sudo apt update
		//sudo apt install postgresql postgresql-contrib

		//sudo service postgresql start

		//sudo -u postgres psql
		//CREATE DATABASE hotelservice_db;
		//CREATE USER hotel_service WITH PASSWORD 'password';
		//GRANT ALL PRIVILEGES ON DATABASE hotelservice_db TO hotel_service;
	
		//{
		// "ssh": "Disabled",
		// "previewLimit": 50,
		// "server": "127.0.0.1",
		// "port": 5432,
		// "driver": "PostgreSQL",
		// "name": "Microservices 2",
		// "database": "hotelservice_db",
		// "username": "hotel_service",
		// "password": "password",
		// "connectionTimeout": 30
		// }

		//\l    → list databases
		// \du   → list users
		// \dt   → list tables
		// \q    → quit

		//-----------------------------------------
		//1️⃣ Open PostgreSQL authentication config
		// Run:
		// sudo nano /etc/postgresql/16/main/pg_hba.conf

		// 2️⃣ Find this line
		// You will see something like:
		// local   all             postgres                                peer
		// local   all             all                                     peer

		// Change peer → trust
		// local   all             postgres                                trust
		// local   all             all                                     trust
		// Save the file.

		// Nano commands:
		// CTRL + O → Enter (save)
		// CTRL + X (exit)

		// 3️⃣ Restart PostgreSQL
		// sudo service postgresql restart

		// 4️⃣ Login without password
		// Now run:
		// psql -U postgres

		// You should see:
		// postgres=#

		// 5️⃣ Set password for your user
		// ALTER USER hotel_service WITH PASSWORD 'hotel_service';
		// If the user doesn't exist, create it
		// CREATE USER hotel_service WITH PASSWORD 'hotel_service';

		// 6️⃣ Give database access
		//\c hotelservice_db
		// ALTER SCHEMA public OWNER TO hotel_service;
		// GRANT ALL ON SCHEMA public TO hotel_service;
		// GRANT USAGE, CREATE ON SCHEMA public TO hotel_service;
		// GRANT ALL PRIVILEGES ON DATABASE hotelservice_db TO hotel_service;

		// Exit:
		// \q

		// 7️⃣ Restore security (important)
		// Open the config again:
		// sudo nano /etc/postgresql/16/main/pg_hba.conf
		// Change back:
		// trust → md5
		// Example:
		// local   all             postgres                                md5
		// local   all             all                                     md5

		// Restart again:
		// sudo service postgresql restart
		
		// 8️⃣ Test login
		// psql -h 127.0.0.1 -U hotel_service -d hotelservice_db

		// Password:
		// password

		// mvn spring-boot:run
	}

}
