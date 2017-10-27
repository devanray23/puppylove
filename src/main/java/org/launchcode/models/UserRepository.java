package org.launchcode.models;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;

 @RepositoryRestResource(exported = false)
 public interface UserRepository extends Repository<User, Long> {

         User save(User user);

         User findByName(String name);

 }