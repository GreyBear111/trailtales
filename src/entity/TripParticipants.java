package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас для управління списком учасників подорожі.
 * Інкапсулює логіку додавання, видалення та перевірки наявності учасників.
 */
public class TripParticipants {
    private List<String> participants; // Список учасників (електронні адреси або імена)

    /**
     * Конструктор створює порожній список учасників.
     */
    public TripParticipants() {
        this.participants = new ArrayList<>();
    }

    /**
     * Додає нового учасника до списку.
     * @param name Ім'я або електронна адреса учасника
     */
    public void addParticipant(String name) {
        participants.add(name);
    }

    /**
     * Видаляє учасника зі списку.
     * @param name Ім'я або електронна адреса учасника
     */
    public void removeParticipant(String name) {
        participants.remove(name);
    }

    /**
     * Повертає список усіх учасників.
     * @return список учасників подорожі
     */
    public List<String> getParticipants() {
        return participants;
    }

    /**
     * Перевіряє, чи є вказаний учасник у списку.
     * @param name Ім'я або електронна адреса учасника
     * @return true, якщо учасник є в списку, false - якщо ні
     */
    public boolean hasParticipant(String name) {
        return participants.contains(name);
    }

    /**
     * Перевизначений метод toString для представлення списку учасників у вигляді рядка.
     */
    @Override
    public String toString() {
        return "TripParticipants{" + "participants=" + participants + '}';
    }
}
