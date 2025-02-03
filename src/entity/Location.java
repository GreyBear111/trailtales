package entity;

/**
 * Клас представляє місцезнаходження, що включає країну, місто та координати.
 * Дотримується принципу інкапсуляції.
 */
public class Location {
    private String country;      // Назва країни
    private String city;         // Назва міста
    private String coordinates;  // Координати у форматі рядка

    /**
     * Конструктор класу, що ініціалізує місцезнаходження.
     */
    public Location(String country, String city, String coordinates) {
        this.country = country;
        this.city = city;
        this.coordinates = coordinates;
    }

    // Гетери та сетери для доступу до полів класу

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

    /**
     * Перевизначений метод toString для зручного виведення інформації про місцезнаходження.
     */
    @Override
    public String toString() {
        return city + ", " + country + " (" + coordinates + ")";
    }
}
