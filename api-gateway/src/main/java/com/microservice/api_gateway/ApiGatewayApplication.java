package com.microservice.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

		//mvn clean install 
		//mvn spring-boot:run

		//DOWNGRADE SPRINGBOOT VERSION TO 3.2.5
		// sudo apt update
		// sudo apt install openjdk-17-jdk -y
		// sudo update-alternatives --config java
		// export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
		// export PATH=$JAVA_HOME/bin:$PATH
		// java -version
		// mvn clean install
	}

}
