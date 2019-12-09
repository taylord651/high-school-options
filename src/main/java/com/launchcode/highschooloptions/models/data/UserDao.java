package com.launchcode.highschooloptions.models.data;

import com.launchcode.highschooloptions.forms.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    public User findByName(String Name);
}
