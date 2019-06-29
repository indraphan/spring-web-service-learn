package com.indraphan.learn.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceLearnApplication.class, args);
	}

}
