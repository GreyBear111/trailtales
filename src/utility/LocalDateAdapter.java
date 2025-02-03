package utility;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Адаптер для серіалізації та десеріалізації об'єктів LocalDate в форматі "dd.MM.yyyy".
 * Використовується в Gson для обробки дат у специфічному форматі.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Формат для дати

    /**
     * Записує LocalDate як рядок в форматі "dd.MM.yyyy".
     * @param out Об'єкт JsonWriter для запису в JSON
     * @param value Значення LocalDate
     * @throws IOException Якщо сталася помилка при записі
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.format(formatter)); // Форматуємо LocalDate та записуємо в JSON
    }

    /**
     * Зчитує рядок у форматі "dd.MM.yyyy" і перетворює його на LocalDate.
     * @param in Об'єкт JsonReader для зчитування з JSON
     * @return LocalDate об'єкт
     * @throws IOException Якщо сталася помилка при зчитуванні
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), formatter); // Перетворюємо рядок на LocalDate
    }
}
