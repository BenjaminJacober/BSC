package org.example.controllers;

import jakarta.transaction.Transactional;
import org.example.dtos.PlaceVM;
import org.example.entities.Place;
import org.example.entities.Trip;
import org.example.repositories.PlaceRepository;
import org.example.repositories.TripRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceController {

	private final PlaceRepository placeRepository;
	private final TripRepository tripRepository;

	public PlaceController(PlaceRepository placeRepository, TripRepository tripRepository) {
		this.placeRepository = placeRepository;
		this.tripRepository = tripRepository;
	}

	@GetMapping("/places")
	public List<PlaceVM> getAllPlaces(@RequestParam(name = "tripId") Long tripId) {
		Trip trip = tripRepository.findById(tripId).get();
		List<Place> byTrip = placeRepository.findByTrip(trip);
		return PlaceVM.from(byTrip);
	}

	@Transactional
	@PostMapping("/places/create")
	public PlaceVM createPlace(@RequestBody PlaceVM placeVM) {

		Trip trip = tripRepository.findById(placeVM.getTripId()).get();

		Place fromPlace = placeVM.getFromPlaceId() == null
				? null
				: placeRepository.findById(placeVM.getFromPlaceId()).get();

		Place place = new Place(trip,
				Date.valueOf(placeVM.getTimeArrived()),
				Date.valueOf(placeVM.getTimeLeft()),
				placeVM.getDescription(),
				placeVM.getLatitude(),
				placeVM.getLongitude(),
				fromPlace);

		placeRepository.save(place);

		return PlaceVM.from(place);
	}

}
