import entity.Trip;
import entity.User;
import exception.RegistrationException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import service.TripService;
import service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static TripService tripService = new TripService();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        AnsiConsole.systemInstall(); // Ініціалізація Jansi
        showLoginMenu();
        AnsiConsole.systemUninstall(); // Завершення роботи з Jansi
    }

    private static void showLoginMenu() {
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).bold().a("********** Авторизація / Реєстрація **********").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("1. Реєстрація").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("2. Авторизація").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("3. Вихід").reset());

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> registerUser();
            case 2 -> loginUser();
            case 3 -> System.exit(0);
            default -> showLoginMenu();
        }
    }

    private static void registerUser() {
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Ім'я (мінімум 3 символи): ").reset());
        String name = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Електронна пошта (має містити @): ").reset());
        String email = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Пароль (мінімум 4 символи): ").reset());
        String password = scanner.nextLine();

        try {
            userService.registerUser(name, email, password);
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Реєстрація успішна!").reset());
        } catch (RegistrationException e) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Помилка: " + e.getMessage()).reset());
        }
        showLoginMenu();
    }

    private static void loginUser() {
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Електронна пошта: ").reset());
        String email = scanner.nextLine();
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Пароль: ").reset());
        String password = scanner.nextLine();

        currentUser = userService.authenticateUser(email, password);
        if (currentUser != null) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Вітаємо, ").a(currentUser.getName()).a("!").reset());
            showMainMenu();
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Невірний логін або пароль.").reset());
            showLoginMenu();
        }
    }

    private static void showMainMenu() {
        System.out.println("\n" + Ansi.ansi().fg(Ansi.Color.CYAN).bold().a("********** Головне Меню **********").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("1. Переглянути подорожі").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("2. Додати подорож").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("3. Редагувати подорож").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("4. Видалити подорож").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("5. Вийти").reset());

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> viewTrips();
            case 2 -> addTrip();
            case 3 -> editTrip();
            case 4 -> deleteTrip();
            case 5 -> {
                currentUser = null;
                showLoginMenu();
            }
            default -> showMainMenu();
        }
    }

    private static void viewTrips() {
        List<Trip> trips = tripService.getAllTrips();
        if (trips.isEmpty()) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Подорожі відсутні.").reset());
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Подорожі:").reset());
            for (Trip trip : trips) {
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Назва: ").reset().a(trip.getName()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Країна: ").reset().a(trip.getCountry()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Місто: ").reset().a(trip.getCity()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Опис: ").reset().a(trip.getDescription()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Дата початку: ").reset().a(trip.getStartDate().toString()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("Дата завершення: ").reset().a(trip.getEndDate().toString()));
                System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("------").reset());
            }
        }
        showMainMenu();
    }

    private static void addTrip() {
        while (true) {
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Назва подорожі: ").reset());
            String name = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Країна: ").reset());
            String country = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Місто: ").reset());
            String city = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Опис: ").reset());
            String description = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Дата початку (у форматі dd.MM.yyyy): ").reset());
            String startDate = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Дата завершення (у форматі dd.MM.yyyy): ").reset());
            String endDate = scanner.nextLine();

            try {
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                if (start.isAfter(end)) {
                    System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Дата початку не може бути пізніше дати завершення. Спробуйте ще раз.").reset());
                    continue;
                }

                tripService.addTrip(name, country, city, description, startDate, endDate);
                System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Подорож додана!").reset());
                break;
            } catch (DateTimeParseException e) {
                System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Невірний формат дати. Спробуйте ще раз.").reset());
            }
        }
        showMainMenu();
    }

    private static void editTrip() {
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Введіть назву подорожі для редагування: ").reset());
        String name = scanner.nextLine();
        Trip trip = tripService.getAllTrips().stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);

        if (trip == null) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Подорож не знайдена.").reset());
            showMainMenu();
            return;
        }

        while (true) {
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Нова країна (залиште порожнім, щоб не змінювати): ").reset());
            String country = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Нове місто (залиште порожнім, щоб не змінювати): ").reset());
            String city = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Новий опис (залиште порожнім, щоб не змінювати): ").reset());
            String description = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Нова дата початку (у форматі dd.MM.yyyy, залиште порожнім, щоб не змінювати): ").reset());
            String startDate = scanner.nextLine();
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Нова дата завершення (у форматі dd.MM.yyyy, залиште порожнім, щоб не змінювати): ").reset());
            String endDate = scanner.nextLine();

            try {
                if (!startDate.isEmpty()) {
                    LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    trip.setStartDate(start);
                }
                if (!endDate.isEmpty()) {
                    LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    trip.setEndDate(end);
                }

                if (trip.getStartDate().isAfter(trip.getEndDate())) {
                    System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Дата початку не може бути пізніше дати завершення. Спробуйте ще раз.").reset());
                    continue;
                }

                if (!country.isEmpty()) trip.setCountry(country);
                if (!city.isEmpty()) trip.setCity(city);
                if (!description.isEmpty()) trip.setDescription(description);

                tripService.updateTrip(trip.getName(), trip.getCountry(), trip.getCity(), trip.getDescription(), trip.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), trip.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

                System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Подорож оновлена!").reset());
                break;
            } catch (DateTimeParseException e) {
                System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Невірний формат дати. Спробуйте ще раз.").reset());
            }
        }
        showMainMenu();
    }

    private static void deleteTrip() {
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Введіть назву подорожі для видалення: ").reset());
        String name = scanner.nextLine();
        tripService.deleteTrip(name);
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Подорож видалена!").reset());
        showMainMenu();
    }
}