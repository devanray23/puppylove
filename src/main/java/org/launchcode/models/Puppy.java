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

    @NotNull
    @Max(25)
    private int age;

    @ManyToOne
    private User user;

    public Puppy(String name, String breed, String location, int age) {
        this.name = name;
        this.breed = breed;
        this.location = location;
        this.age = age;
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

    public int setAge() {
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}


