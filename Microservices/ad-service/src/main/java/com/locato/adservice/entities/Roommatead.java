package com.locato.adservice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "ads")
@Getter
@Setter
public class Roommatead extends Ad{
    int gender;
    public Roommatead(String id, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation, int gender) {
        super(id, title, description, price, timeStamp, accomodation);
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}