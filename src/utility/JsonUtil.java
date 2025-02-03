package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилітний клас для роботи з JSON файлами.
 * Використовує бібліотеку Gson для серіалізації та десеріалізації об'єктів.
 */
public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Реєстрація адаптера для роботи з LocalDate
            .setPrettyPrinting() // Форматування JSON для кращого вигляду
            .create(); // Створення Gson об'єкта

    /**
     * Десеріалізує список об'єктів з JSON файлу.
     * @param filePath Шлях до файлу
     * @param clazz Клас об'єкта для десеріалізації
     * @param <T> Тип об'єкта
     * @return Список об'єктів
     */
    public static <T> List<T> fromJsonListFile(String filePath, Class<T> clazz) {
        try (FileReader reader = new FileReader(filePath)) {
            // Десеріалізація списку з JSON
            List<T> list = gson.fromJson(reader, TypeToken.getParameterized(List.class, clazz).getType());
            return list != null ? list : new ArrayList<>(); // Повертає порожній список, якщо результат null
        } catch (IOException e) {
            return new ArrayList<>(); // Повертає порожній список у разі помилки
        }
    }

    /**
     * Серіалізує список об'єктів в JSON і записує його у файл.
     * @param list Список об'єктів
     * @param filePath Шлях до файлу
     * @param <T> Тип об'єкта
     */
    public static <T> void toJsonFile(List<T> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer); // Серіалізація списку в JSON та запис у файл
        } catch (IOException e) {
            e.printStackTrace(); // Виведення помилки, якщо сталася проблема з файлом
        }
    }
}
