package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class PuppyData {

    static ArrayList<Puppy> puppies = new ArrayList<>();

    // getAll
    public static ArrayList<Puppy> getAll() {
        return puppies;
    }

    // add
    public static void add(Puppy newCheese) {
        puppies.add(newCheese);
    }

    // remove
    public static void remove(int id) {
        Puppy puppyToRemove = getById(id);
        puppies.remove(puppyToRemove);
    }

    // getById
    public static Puppy getById(int id) {

        Puppy thePuppy = null;

        for (Puppy candidatePuppy : puppies) {
            if (candidatePuppy.getPuppyId() == id) {
                thePuppy = candidatePuppy;
            }
        }

        return thePuppy;
    }

}
