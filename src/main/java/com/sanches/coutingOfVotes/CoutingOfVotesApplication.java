package com.sanches.coutingOfVotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoutingOfVotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoutingOfVotesApplication.class, args);
	}

}
