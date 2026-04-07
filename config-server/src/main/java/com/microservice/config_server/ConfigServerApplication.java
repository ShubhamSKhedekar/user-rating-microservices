package com.microservice.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);

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
