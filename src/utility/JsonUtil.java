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

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static <T> List<T> fromJsonListFile(String filePath, Class<T> clazz) {
        try (FileReader reader = new FileReader(filePath)) {
            List<T> list = gson.fromJson(reader, TypeToken.getParameterized(List.class, clazz).getType());
            return list != null ? list : new ArrayList<>(); // Повертаємо порожній список, якщо файл порожній або некоректний
        } catch (IOException e) {
            return new ArrayList<>(); // Повертаємо новий порожній список у разі помилки
        }
    }

    public static <T> void toJsonFile(List<T> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}