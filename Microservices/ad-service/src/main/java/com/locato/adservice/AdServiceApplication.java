package com.locato.adservice;

import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
		private Float price;
		@Field("creationTime")
		private LocalTime timeStamp;
		private Accomodation accomodation;

	public Ad(String id, String title, String description, Float price, LocalTime timeStamp, Accomodation accomodation) {
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
	private Float surface;
	private int rooms;
	private int bathrooms;
	private int best = 0;
	private String[] images;
	private String type;
	Category[] categories;
	public Accomodation(String location, Float surface, int rooms, int bathrooms, int best, String[] images, String type,Category[] categories) {
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
	public Locationad(String id, String title, String description, Float price, LocalTime timeStamp, Accomodation accomodation) {
		super(id, title, description, price, timeStamp, accomodation);
	}
}
@Document(collation = "ads")
class Roommatead extends Ad{
	Boolean gender;

	public Roommatead(String id, String title, String description, Float price, LocalTime timeStamp, Accomodation accomodation,Boolean gender) {
		super(id, title, description, price, timeStamp, accomodation);
		this.gender = gender;
	}
}
@RepositoryRestResource(collectionResourceRel = "ads", path = "ads")
public interface LocationAdRepository extends MongoRepository<Locationad, String> {
}
@RepositoryRestResource(collectionResourceRel = "ads", path = "ads")
public interface RoommateAdRepository extends MongoRepository<Roommatead, String> {
}
	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}
}
