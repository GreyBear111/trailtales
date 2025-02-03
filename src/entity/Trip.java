package entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Клас представляє подорож, що містить основну інформацію про неї.
 * Включає назву, місцезнаходження, опис, дати, список учасників та власника.
 * Дотримується принципу інкапсуляції.
 */
public class Trip {
    private String name;               // Назва подорожі
    private Location location;         // Місцезнаходження подорожі
    private String description;        // Опис подорожі
    private LocalDate startDate;       // Дата початку подорожі
    private LocalDate endDate;         // Дата завершення подорожі
    private List<String> participants; // Список учасників подорожі (електронні адреси)
    private String ownerEmail;         // Електронна адреса власника подорожі

    /**
     * Конструктор для створення нового об'єкта подорожі.
     */
    public Trip(String name, Location location, String description, LocalDate startDate, LocalDate endDate, List<String> participants, String ownerEmail) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
        this.ownerEmail = ownerEmail;
    }

    // Геттери та сеттери для доступу до полів класу

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * Перевизначений метод toString для зручного виведення інформації про подорож.
     */
    @Override
    public String toString() {
        return name + " - " + description + " (" + startDate + " to " + endDate + ") at " + location + " [Owner: " + ownerEmail + "]";
    }
}
