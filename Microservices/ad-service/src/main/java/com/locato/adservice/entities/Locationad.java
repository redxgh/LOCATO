package com.locato.adservice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "ads")
@Getter
@Setter
public class Locationad extends Ad {
    public Locationad(String id,String userId, String title, String description, double price, LocalTime timeStamp, Accomodation accomodation) {
        super(id, userId, title, description, price, timeStamp, accomodation);
    }
}