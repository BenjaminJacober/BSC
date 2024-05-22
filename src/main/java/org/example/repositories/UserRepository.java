package org.example.repositories;

import org.example.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    List<User> findByUserName(String userName);

    User findById(long id);

}
