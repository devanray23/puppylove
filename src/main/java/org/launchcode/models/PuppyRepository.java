package org.launchcode.models;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Greg Turnquist
 */
// tag::code[]
public interface PuppyRepository extends CrudRepository<Puppy, Integer> {

}
