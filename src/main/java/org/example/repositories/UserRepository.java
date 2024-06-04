package org.example.repositories;

import org.example.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAll();

	// todo: optionals for other repos also
	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);

	User findById(long id);

}
