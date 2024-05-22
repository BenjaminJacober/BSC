package org.example.repositories;

import org.example.entities.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findAll();

}
