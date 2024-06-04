package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.Trip;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TripVM {

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Collection<PlaceVM> places;

    public static TripVM from(Trip trip) {
        return new TripVM(trip.getId(), trip.getName(), trip.getDescription(), trip.getUser().getId(), PlaceVM.from(trip.getPlaces()));
    }

    public static List<TripVM> from(Collection<Trip> trips) {
        return trips.stream().map(TripVM::from).toList();
    }

    @Override
    public String toString() {
        return "TripVM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", places=" + places +
                '}';
    }
}