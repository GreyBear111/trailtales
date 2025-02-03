package service;

import entity.Location;
import entity.Trip;
import repository.TripRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Сервіс для управління подорожами.
 * Включає логіку для додавання, оновлення, видалення подорожей та перевірки дій з ними.
 */
public class TripService {
    private TripRepository tripRepository = new TripRepository(); // Репозиторій для зберігання подорожей
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Формат для парсингу дат

    /**
     * Додає нову подорож для користувача.
     * Перевіряє на наявність подорожі з такою назвою для цього користувача, правильність дат.
     * @param name Назва подорожі
     * @param country Країна
     * @param city Місто
     * @param description Опис подорожі
     * @param startDate Дата початку
     * @param endDate Дата завершення
     * @param participants Список учасників
     * @param ownerEmail Email власника подорожі
     */
    public void addTrip(String name, String country, String city, String description,
                        String startDate, String endDate, List<String> participants, String ownerEmail) {
        // Перевірка на наявність подорожі з такою назвою для цього користувача
        if (tripRepository.findTripByNameAndOwner(name, ownerEmail) != null) {
            System.out.println("Подорож з такою назвою вже існує для цього користувача.");
            return;
        }
        // Перетворення рядків дат у LocalDate
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Перевірка коректності дат
        if (start.isAfter(end)) {
            System.out.println("Дата початку не може бути пізніше дати завершення.");
            return;
        }

        // Створення об'єкта місця
        Location location = new Location(country, city, "");
        // Створення об'єкта подорожі
        Trip trip = new Trip(name, location, description, start, end, participants, ownerEmail);
        tripRepository.addTrip(trip); // Додавання подорожі в репозиторій
    }

    /**
     * Отримує всі подорожі користувача за його email.
     * @param ownerEmail Email власника подорожі
     * @return Список подорожей для користувача
     */
    public List<Trip> getTripsForUser(String ownerEmail) {
        return tripRepository.getTripsForUser(ownerEmail); // Отримання подорожей для користувача
    }

    /**
     * Оновлює існуючу подорож.
     * Перевіряє правильність дат і зберігає оновлену подорож.
     * @param name Назва подорожі
     * @param country Країна
     * @param city Місто
     * @param description Опис подорожі
     * @param startDate Дата початку
     * @param endDate Дата завершення
     * @param participants Список учасників
     * @param ownerEmail Email власника подорожі
     */
    public void updateTrip(String name, String country, String city, String description,
                           String startDate, String endDate, List<String> participants, String ownerEmail) {
        // Перетворення рядків дат у LocalDate
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Перевірка коректності дат
        if (start.isAfter(end)) {
            System.out.println("Дата початку не може бути пізніше дати завершення.");
            return;
        }

        // Створення об'єкта місця
        Location location = new Location(country, city, "");
        // Створення об'єкта подорожі
        Trip trip = new Trip(name, location, description, start, end, participants, ownerEmail);
        tripRepository.updateTrip(trip); // Оновлення подорожі в репозиторії
    }

    /**
     * Видаляє подорож за назвою та email власника.
     * @param name Назва подорожі
     * @param ownerEmail Email власника подорожі
     */
    public void deleteTrip(String name, String ownerEmail) {
        tripRepository.deleteTrip(name, ownerEmail); // Видалення подорожі з репозиторію
    }
}
