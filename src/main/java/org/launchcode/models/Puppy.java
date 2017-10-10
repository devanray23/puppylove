package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Puppy {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=15)
    private String name;

    @NotNull
    @Size(min=2, max=15)
    private String breed;

    @NotNull
    @Size(min=2, max=15)
    private String location;
<<<<<<< HEAD
=======
    private Image photo;
    private int weight;
    private int puppyId;
    private static int nextId = 1;
>>>>>>> 1247ca60a936881f945112d0a2d35cea9863e956

    @NotNull
    @Max(25)
    private int age;

    @ManyToOne
    private User user;

<<<<<<< HEAD
    public Puppy(String name, String breed, String location, int age) {
=======
    public Puppy(String name, String breed, String location, int age, Image photo, int weight) {
>>>>>>> 1247ca60a936881f945112d0a2d35cea9863e956
        this.name = name;
        this.breed = breed;
        this.location = location;
        this.age = age;
        this.weight = weight;
    }

    public Puppy() { }

    public int getId() {
        return id;
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
<<<<<<< HEAD

    public int setAge() {
        return age;
    }

=======
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
>>>>>>> 1247ca60a936881f945112d0a2d35cea9863e956
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}


