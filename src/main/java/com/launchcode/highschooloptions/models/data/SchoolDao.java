package com.launchcode.highschooloptions.models.data;

import com.launchcode.highschooloptions.models.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SchoolDao extends CrudRepository<School, Integer> {

}
