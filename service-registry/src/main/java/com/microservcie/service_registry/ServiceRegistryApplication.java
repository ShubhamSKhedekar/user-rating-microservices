package com.microservcie.service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);

		//mvn clean install
		//mvn spring-boot:run

		//DOWNGRADE SPRINGBOOT VERSION TO 3.2.5
		// sudo apt update
		// sudo apt install openjdk-17-jdk -y
		// sudo update-alternatives --config java
		// export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
		// export PATH=$JAVA_HOME/bin:$PATH
		// java -version
		//mvn clean install
	}

}
