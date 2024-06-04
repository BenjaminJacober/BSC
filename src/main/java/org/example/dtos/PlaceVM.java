package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.Place;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlaceVM {

    private Long id;
    private Long tripId;
    private LocalDate timeArrived;
    private LocalDate timeLeft;
    private String description;

    private Float latitude;
    private Float longitude;

    private Long fromPlaceId;
//    private TransportationModeEnum transportationMode;


    public static PlaceVM from(Place place) {
        Long fromPlaceId = place.getFrom() == null ? null : place.getFrom().getId();
        return new PlaceVM(
                place.getId(),
                place.getTrip().getId(),
                place.getTimeArrived().toLocalDate(),
                place.getTimeLeft().toLocalDate(),
                place.getDescription(),
                place.getLatitude(),
                place.getLongitude(),
                fromPlaceId);
    }

    public static List<PlaceVM> from(Collection<Place> places) {
        return places.stream().map(PlaceVM::from).toList();
    }

}
