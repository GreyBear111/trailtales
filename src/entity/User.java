package entity;

/**
 * Клас представляє користувача системи.
 * Містить основну інформацію: ім'я, email та пароль (без шифрування).
 * Дотримується принципу інкапсуляції.
 */
public class User {
    private String name;      // Ім'я користувача
    private String email;     // Електронна адреса користувача
    private String password;  // Пароль користувача (зберігається у відкритому вигляді, шифрування не реалізовано)

    /**
     * Конструктор для створення нового користувача.
     * @param name Ім'я користувача
     * @param email Електронна адреса користувача
     * @param password Пароль користувача
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Отримати ім'я користувача.
     * @return ім'я у вигляді рядка
     */
    public String getName() {
        return name;
    }

    /**
     * Отримати електронну адресу користувача.
     * @return email у вигляді рядка
     */
    public String getEmail() {
        return email;
    }

    /**
     * Отримати пароль користувача (небезпечно, оскільки зберігається у відкритому вигляді).
     * @return пароль у вигляді рядка
     */
    public String getPassword() {
        return password;
    }

    /**
     * Перевизначений метод toString для представлення користувача у вигляді рядка.
     */
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
