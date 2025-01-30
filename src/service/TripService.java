package service;

import entity.Trip;
import repository.TripRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TripService {
    private TripRepository tripRepository = new TripRepository();

    public void addTrip(String name, String country, String city, String description, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Trip trip = new Trip(
                name,
                country,
                city,
                description,
                LocalDate.parse(startDate, formatter),
                LocalDate.parse(endDate, formatter),
                new ArrayList<>()
        );
        tripRepository.addTrip(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.getAllTrips();
    }

    public void updateTrip(String name, String country, String city, String description, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Trip trip = new Trip(
                name,
                country,
                city,
                description,
                LocalDate.parse(startDate, formatter),
                LocalDate.parse(endDate, formatter),
                new ArrayList<>()
        );
        tripRepository.updateTrip(trip);
    }

    public void deleteTrip(String name) {
        tripRepository.deleteTrip(name);
    }
}