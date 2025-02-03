package repository;

import entity.Trip;
import utility.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Репозиторій для управління даними про подорожі.
 * Включає методи для отримання, додавання, оновлення та видалення подорожей.
 * Використовує збереження даних у JSON-файл.
 */
public class TripRepository {
    private static final String FILE_PATH = "trips.json"; // Шлях до файлу, в якому зберігаються дані про подорожі
    private List<Trip> trips;  // Список подорожей

    /**
     * Конструктор ініціалізує список подорожей, зчитуючи дані з JSON-файлу.
     * Якщо файл не містить даних, створюється порожній список.
     */
    public TripRepository() {
        this.trips = JsonUtil.fromJsonListFile(FILE_PATH, Trip.class); // Зчитування з файлу
        if (this.trips == null) {
            this.trips = new ArrayList<>(); // Якщо файл порожній, створюємо новий список
        }
    }

    /**
     * Отримує всі подорожі з репозиторію.
     * @return список усіх подорожей
     */
    public List<Trip> getAllTrips() {
        return trips;
    }

    /**
     * Отримує подорожі для конкретного користувача за email власника.
     * @param ownerEmail email власника подорожі
     * @return список подорожей, що належать користувачу
     */
    public List<Trip> getTripsForUser(String ownerEmail) {
        return trips.stream()
                .filter(trip -> ownerEmail.equals(trip.getOwnerEmail())) // Фільтрація за email власника
                .collect(Collectors.toList());
    }

    /**
     * Додає нову подорож до репозиторію та зберігає зміни в файл.
     * @param trip об'єкт подорожі
     */
    public void addTrip(Trip trip) {
        trips.add(trip); // Додавання нової подорожі
        saveTrips(); // Збереження змін у файл
    }

    /**
     * Зберігає всі подорожі в JSON-файл.
     */
    public void saveTrips() {
        JsonUtil.toJsonFile(trips, FILE_PATH); // Використання утиліти для запису в файл
    }

    /**
     * Знаходить подорож за назвою та email власника.
     * @param name Назва подорожі
     * @param ownerEmail Email власника подорожі
     * @return знайдена подорож або null, якщо не знайдено
     */
    public Trip findTripByNameAndOwner(String name, String ownerEmail) {
        return trips.stream()
                .filter(trip -> trip.getName().equals(name) && ownerEmail.equals(trip.getOwnerEmail())) // Фільтрація за назвою та власником
                .findFirst()
                .orElse(null); // Якщо не знайдено - повертаємо null
    }

    /**
     * Оновлює інформацію про подорож.
     * @param updatedTrip оновлений об'єкт подорожі
     */
    public void updateTrip(Trip updatedTrip) {
        Trip trip = findTripByNameAndOwner(updatedTrip.getName(), updatedTrip.getOwnerEmail());
        if (trip != null) { // Якщо подорож знайдена
            trip.setLocation(updatedTrip.getLocation());
            trip.setDescription(updatedTrip.getDescription());
            trip.setStartDate(updatedTrip.getStartDate());
            trip.setEndDate(updatedTrip.getEndDate());
            trip.setParticipants(updatedTrip.getParticipants());
            saveTrips(); // Збереження змін
        }
    }

    /**
     * Видаляє подорож за назвою та email власника.
     * @param name Назва подорожі
     * @param ownerEmail Email власника подорожі
     */
    public void deleteTrip(String name, String ownerEmail) {
        trips.removeIf(trip -> trip.getName().equals(name) && ownerEmail.equals(trip.getOwnerEmail())); // Видалення подорожі
        saveTrips(); // Збереження змін у файл
    }
}
