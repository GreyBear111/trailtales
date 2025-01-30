package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String name;
    private String country;
    private String city;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> participants;

    public Trip(String name, String country, String city, String description, LocalDate startDate, LocalDate endDate, List<String> participants) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return name + " - " + description + " (" + startDate + " to " + endDate + ")";
    }
}