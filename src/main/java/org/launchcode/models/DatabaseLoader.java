package org.launchcode.models;

import org.launchcode.models.data.PuppyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final PuppyDao repository;

    @Autowired
    public DatabaseLoader(PuppyDao repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Puppy("Frodo", "Beagle", "Seattle, WA", 5));
    }
}