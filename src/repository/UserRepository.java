package repository;

import entity.User;
import utility.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторій для управління даними користувачів.
 * Включає методи для отримання, додавання користувачів та збереження їх у JSON-файл.
 */
public class UserRepository {
    private static final String FILE_PATH = "users.json"; // Шлях до файлу, в якому зберігаються дані про користувачів
    private List<User> users;  // Список користувачів

    /**
     * Конструктор ініціалізує список користувачів, зчитуючи дані з JSON-файлу.
     * Якщо файл не містить даних, створюється порожній список.
     */
    public UserRepository() {
        this.users = JsonUtil.fromJsonListFile(FILE_PATH, User.class); // Зчитування з файлу
        if (this.users == null) {
            this.users = new ArrayList<>(); // Якщо файл порожній, створюємо новий список
        }
    }

    /**
     * Отримує всіх користувачів з репозиторію.
     * @return список усіх користувачів
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Додає нового користувача до репозиторію та зберігає зміни в файл.
     * @param user об'єкт користувача
     */
    public void addUser(User user) {
        users.add(user); // Додавання нового користувача
        saveUsers(); // Збереження змін у файл
    }

    /**
     * Зберігає всі користувачів в JSON-файл.
     */
    public void saveUsers() {
        JsonUtil.toJsonFile(users, FILE_PATH); // Використання утиліти для запису в файл
    }

    /**
     * Знаходить користувача за email.
     * @param email електронна адреса користувача
     * @return знайдений користувач або null, якщо не знайдено
     */
    public User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email)) // Фільтрація за email
                .findFirst()
                .orElse(null); // Якщо не знайдено - повертаємо null
    }
}
