package com.locato.adservice.entities;

import com.locato.adservice.AdServiceApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;

@Document(collection = "ads")
public class Ad{
    @Id
    private String id;
    private String title;
    private String description;
    private double price;
    @Field("creationTime")
    private LocalTime timeStamp;
    private Accomodation accomodation;
    @Override
    public String toString(){
        return this.title+" "+this.description+" "+this.price;
    }

    public Ad(String id, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.timeStamp = timeStamp;
        this.accomodation = accomodation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }
}