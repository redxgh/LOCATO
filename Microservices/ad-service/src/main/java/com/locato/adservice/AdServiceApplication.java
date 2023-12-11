package com.locato.adservice;

import com.locato.adservice.dao.LocationAdRepository;
import com.locato.adservice.dao.RoommateAdRepository;
import com.locato.adservice.entities.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootApplication
public class AdServiceApplication {
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}
	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}
}

