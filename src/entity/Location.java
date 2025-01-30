package entity;

public class Location {
    private String country;
    private String city;
    private String coordinates;

    public Location(String country, String city, String coordinates) {
        this.country = country;
        this.city = city;
        this.coordinates = coordinates;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return city + ", " + country + " (" + coordinates + ")";
    }
}