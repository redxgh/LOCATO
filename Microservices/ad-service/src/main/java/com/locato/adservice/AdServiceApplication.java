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

@SpringBootApplication
public class AdServiceApplication {
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}
	@Bean
	public CommandLineRunner initData(LocationAdRepository locationAdRepository, RoommateAdRepository roommateAdRepository) {
		return args -> {
			Locationad locationAd = new Locationad("1", "Location Ad 1", "Description 1", 100.0, LocalTime.now(), new Accomodation("Location 1", 120.0, 3, 2, 0, new String[]{"image1.jpg"}, "Apartment", new Category("cat1","img1")));
			Roommatead roommateAd = new Roommatead("2", "Roommate Ad 1", "Description 2", 200.0, LocalTime.now(), new Accomodation("Location 2", 150.0, 2, 1, 0, new String[]{"image2.jpg"}, "House", new Category("cat2","img2")), 1);
			locationAdRepository.save(locationAd);
			roommateAdRepository.save(roommateAd);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}
}

