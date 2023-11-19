package com.locato.adservice.entities;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Accomodation{
    private String location;
    private double surface;
    private int rooms;
    private int bathrooms;
    private int best = 0;
    private ArrayList<String> images;
    private String type;
    Category category;
    public Accomodation(String location, double surface, int rooms, int bathrooms, int best, ArrayList<String> images, String type, Category category) {
        this.location = location;
        this.surface = surface;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.best = best;
        this.images = images;
        this.type = type;
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category getCategories() {
        return category;
    }

    public void setCategories(Category categories) {
        this.category = categories;
    }
}