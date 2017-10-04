package org.launchcode.models;

import java.awt.*;

public class Puppy {

    private String name;
    private int age;
    private String breed;
    private String location;
    private Image photo;


    public Puppy(String name, String breed, String location, int age, Image photo) {
        this.name = name;
        this.breed = breed;
        this.location = location;
        this.photo = photo;
    }

    public Puppy() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}


