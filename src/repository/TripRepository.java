package repository;

import entity.Trip;
import utility.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private static final String FILE_PATH = "trips.json";
    private List<Trip> trips;

    public TripRepository() {
        this.trips = JsonUtil.fromJsonListFile(FILE_PATH, Trip.class);
        if (this.trips == null) {
            this.trips = new ArrayList<>();
        }
    }

    public List<Trip> getAllTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
        saveTrips();
    }

    public void saveTrips() {
        JsonUtil.toJsonFile(trips, FILE_PATH);
    }

    public Trip findTripByName(String name) {
        return trips.stream()
                .filter(trip -> trip.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateTrip(Trip updatedTrip) {
        Trip trip = findTripByName(updatedTrip.getName());
        if (trip != null) {
            trip.setCountry(updatedTrip.getCountry());
            trip.setCity(updatedTrip.getCity());
            trip.setDescription(updatedTrip.getDescription());
            trip.setStartDate(updatedTrip.getStartDate());
            trip.setEndDate(updatedTrip.getEndDate());
            saveTrips();
        }
    }

    public void deleteTrip(String name) {
        trips.removeIf(trip -> trip.getName().equals(name));
        saveTrips();
    }
}