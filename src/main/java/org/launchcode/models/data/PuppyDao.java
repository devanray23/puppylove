package org.launchcode.models.data;

import org.launchcode.models.Puppy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PuppyDao extends CrudRepository<Puppy, Integer> {

}

