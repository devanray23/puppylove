package org.launchcode.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class User{

    @Id
    @GeneratedValue
    int id;

    @NotNull
    String name;

    @Email
    String email;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    @Size(min = 16, max= 80)
    int Age;

    @NotNull
    @Size(min = 0, max = 250)
    String description;

    public User() {}

    public User(String username, String email, String password) {
        this.name = name;
        this.email = email;
        this.pwHash = hashPassword(password);
    }

    @OneToMany
    @JoinColumn(name = "puppy_id")
    private Set<Puppy> puppies;

    public Set<Puppy> getPuppies(){ return this.puppies; }

    public void setPuppies(Set<Puppy> puppies){this.puppies = puppies; }



    private static String hashPassword(String password) { return encoder.encode(password); }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getAge() { return Age; }

    public void setAge(int age) { Age = age; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
