package com.eternalgooner.dhis2webapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Dhis2WebApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dhis2WebApiGatewayApplication.class, args);
	}

}
