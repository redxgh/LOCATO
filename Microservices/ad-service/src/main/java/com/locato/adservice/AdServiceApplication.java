package com.locato.adservice;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@SpringBootApplication
public class AdServiceApplication {

	class Category{
		String name;
		String image;
	}
	class Ad{
			@Id
			private String id;
			private String title;
			private String description;
			private double price;
			@Field("creationTime")
			private LocalTime timeStamp;
			private Accomodation accomodation;

		public Ad(String id, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation) {
			this.id = id;
			this.title = title;
			this.description = description;
			this.price = price;
			this.timeStamp = timeStamp;
			this.accomodation = accomodation;
		}
	}
	class Accomodation{
		private String location;
		private double surface;
		private int rooms;
		private int bathrooms;
		private int best = 0;
		private String[] images;
		private String type;
		Category[] categories;
		public Accomodation(String location, double surface, int rooms, int bathrooms, int best, String[] images, String type, Category[] categories) {
			this.location = location;
			this.surface = surface;
			this.rooms = rooms;
			this.bathrooms = bathrooms;
			this.best = best;
			this.images = images;
			this.type = type;
			this.categories = categories;
		}
	}
	@Document(collation = "ads")
	class Locationad extends Ad{
		public Locationad(String id, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation) {
			super(id, title, description, price, timeStamp, accomodation);
		}
	}
	@Document(collation = "ads")
	class Roommatead extends Ad{
		Boolean gender;
		public Roommatead(String id, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation, Boolean gender) {
			super(id, title, description, price, timeStamp, accomodation);
				this.gender = gender;
			}
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}
	@Bean
	public CommandLineRunner initData(LocationAdRepository locationAdRepository, RoommateAdRepository roommateAdRepository) {
		return args -> {
			// Add some sample data
			Locationad locationAd = new Locationad("1", "Location Ad 1", "Description 1", 100.0, LocalTime.now(), new Accomodation("Location 1", 120.0, 3, 2, 0, new String[]{"image1.jpg"}, "Apartment", new Category[0]));
			Roommatead roommateAd = new Roommatead("2", "Roommate Ad 1", "Description 2", 200.0, LocalTime.now(), new Accomodation("Location 2", 150.0, 2, 1, 0, new String[]{"image2.jpg"}, "House", new Category[0]), true);

			locationAdRepository.save(locationAd);
			roommateAdRepository.save(roommateAd);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}
}

