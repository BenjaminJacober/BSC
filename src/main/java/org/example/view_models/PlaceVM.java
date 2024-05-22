package org.example.view_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.Place;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlaceVM {

    private Long id;
    private Date timeArrived;
    private Date timeLeft;
    private String description;

    private Float latitude;
    private Float longitude;

    // todo: maybe only pos needed
    private PlaceVM from;
//    private TransportationModeEnum transportationMode;


    public static PlaceVM from(Place place) {
        if (place == null) return null;
        return new PlaceVM(place.getId(), place.getTimeArrived(), place.getTimeLeft(), place.getDescription(),
                place.getLatitude(), place.getLongitude(), fromWithoutFromPlace(place.getFrom()));
    }

    // todo: refactor this. Probably only want 1 list in frontend that gets u everything
    //  probably a stack
    public static PlaceVM fromWithoutFromPlace(Place place) {
        return new PlaceVM(place.getId(), place.getTimeArrived(), place.getTimeLeft(), place.getDescription(),
                place.getLatitude(), place.getLongitude(), null);
    }

    public static List<PlaceVM> from(Collection<Place> places) {
        return places.stream().map(PlaceVM::from).toList();
    }


}
