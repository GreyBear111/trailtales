package service;

import entity.User;
import exception.RegistrationException;
import org.mindrot.jbcrypt.BCrypt;
import repository.UserRepository;

/**
 * Сервіс для управління користувачами, включаючи реєстрацію та автентифікацію.
 */
public class UserService {
    private UserRepository userRepository = new UserRepository(); // Репозиторій для роботи з користувачами

    /**
     * Реєструє нового користувача.
     * Виконується перевірка вхідних даних та хешування пароля перед збереженням користувача.
     * @param name Ім'я користувача
     * @param email Електронна пошта користувача
     * @param password Пароль користувача
     * @throws RegistrationException Якщо дані не пройшли валідацію
     */
    public void registerUser(String name, String email, String password) throws RegistrationException {
        // Перевірка довжини імені
        if (name.length() < 3) {
            throw new RegistrationException("Ім'я повинно містити щонайменше 3 символи.");
        }
        // Перевірка правильності email
        if (!email.contains("@")) {
            throw new RegistrationException("Електронна пошта повинна містити символ '@'.");
        }
        // Перевірка довжини пароля
        if (password.length() < 4) {
            throw new RegistrationException("Пароль повинен містити щонайменше 4 символи.");
        }

        // Перевірка, чи існує користувач з таким email
        User existingUser = userRepository.findUserByEmail(email);
        if (existingUser != null) {
            throw new RegistrationException("Користувач з такою електронною поштою вже існує.");
        }

        // Хешування пароля з використанням BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Створення нового користувача
        User user = new User(name, email, hashedPassword);
        userRepository.addUser(user); // Додавання користувача в репозиторій
    }

    /**
     * Автентифікує користувача за допомогою email та пароля.
     * Перевіряється, чи співпадає введений пароль з хешованим паролем користувача.
     * @param email Електронна пошта користувача
     * @param password Пароль користувача
     * @return Користувач, якщо автентифікація успішна, або null, якщо ні
     */
    public User authenticateUser(String email, String password) {
        User user = userRepository.findUserByEmail(email); // Пошук користувача за email
        // Перевірка пароля за допомогою BCrypt
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user; // Якщо паролі співпали, повертаємо користувача
        }
        return null; // Якщо користувач не знайдений або пароль неправильний
    }
}
