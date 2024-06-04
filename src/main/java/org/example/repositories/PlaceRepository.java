package org.example.repositories;

import org.example.entities.Place;
import org.example.entities.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {

	List<Place> findAll();

	// todo: there has to be a way to do "findByTripId" so that we don't have to get the trip from the db first
	List<Place> findByTrip(Trip trip);

}
