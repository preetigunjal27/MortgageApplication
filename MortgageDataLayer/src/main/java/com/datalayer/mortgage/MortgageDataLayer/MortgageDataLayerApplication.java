package com.datalayer.mortgage.MortgageDataLayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MortgageDataLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageDataLayerApplication.class, args);
	}
}
