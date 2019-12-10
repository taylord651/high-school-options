package com.launchcode.highschooloptions.models.data;

import com.launchcode.highschooloptions.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    User findByName(String Name);

    User findById(int userId);
}
