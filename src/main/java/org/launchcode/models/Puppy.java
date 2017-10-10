package org.launchcode.models;

import java.awt.*;

public class Puppy {

    private String name;
    private int age;
    private String breed;
    private String location;
    private Image photo;
    private int weight;
    private int puppyId;
    private static int nextId = 1;


    public Puppy(String name, String breed, String location, int age, Image photo, int weight) {
        this.name = name;
        this.breed = breed;
        this.location = location;
        this.photo = photo;
        this.age = age;
        this.weight = weight;
    }

    public Puppy() {
        puppyId = nextId;
        nextId++;
    }

    public int getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(int puppyId) {
        this.puppyId = puppyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public int setAge(int age) {
        this.age = age;
        return age;
    }
    public int getWeight() {
        return weight;
    }
    public int setWeight(int weight) {
        this.weight = weight;
        return weight;
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


