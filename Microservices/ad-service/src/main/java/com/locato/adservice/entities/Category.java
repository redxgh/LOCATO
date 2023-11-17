package com.locato.adservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "ads")
public class Category{
    @Id
    String id;
    String name;
    String image;

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }
}