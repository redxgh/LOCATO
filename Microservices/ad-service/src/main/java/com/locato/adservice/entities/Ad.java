package com.locato.adservice.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;

@Document(collection = "ads")
public class Ad{
    @Getter
    @Id
    private String id;
    private String userId;
    @Getter
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

    public Ad(String id, String userId, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.timeStamp = timeStamp;
        this.accomodation = accomodation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }
}