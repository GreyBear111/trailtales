package repository;

import entity.User;
import utility.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_PATH = "users.json";
    private List<User> users;

    public UserRepository() {
        this.users = JsonUtil.fromJsonListFile(FILE_PATH, User.class);
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public void saveUsers() {
        JsonUtil.toJsonFile(users, FILE_PATH);
    }

    public User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}