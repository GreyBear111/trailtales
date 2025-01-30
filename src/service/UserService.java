package service;

import entity.User;
import exception.RegistrationException;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void registerUser(String name, String email, String password) throws RegistrationException {
        if (name.length() < 3) {
            throw new RegistrationException("Ім'я повинно містити щонайменше 3 символи.");
        }
        if (!email.contains("@")) {
            throw new RegistrationException("Електронна пошта повинна містити символ '@'.");
        }
        if (password.length() < 4) {
            throw new RegistrationException("Пароль повинен містити щонайменше 4 символи.");
        }

        User existingUser = userRepository.findUserByEmail(email);
        if (existingUser != null) {
            throw new RegistrationException("Користувач з такою електронною поштою вже існує.");
        }

        User user = new User(name, email, password);
        userRepository.addUser(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}