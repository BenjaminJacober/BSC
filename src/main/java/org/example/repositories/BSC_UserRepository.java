package org.example.repositories;

import org.example.entities.BSC_User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BSC_UserRepository extends CrudRepository<BSC_User, Long> {

    List<BSC_User> findAll();

    List<BSC_User> findByUserName(String userName);

    BSC_User findById(long id);

}
