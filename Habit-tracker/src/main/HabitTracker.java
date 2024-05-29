import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HabitTracker {
    private Map<String, Habit> habits;
    private final String dataFile;

    public HabitTracker(String dataFile) {
        this.dataFile = dataFile;
        this.habits = loadHabits();
    }

    public HabitTracker() {
        this("data.json");
    }

    private Map<String, Habit> loadHabits() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return Habit.deserializeMap(json.toString());
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    private void saveHabits() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            writer.write(Habit.serializeMap(habits));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHabit(String habitName) {
        if (habits.containsKey(habitName)) {
            throw new IllegalArgumentException("Habit already exists.");
        }
        habits.put(habitName, new Habit(habitName));
        saveHabits();
    }

    public void completeHabit(String habitName) {
        if (!habits.containsKey(habitName)) {
            throw new IllegalArgumentException("Habit does not exist.");
        }
        Habit habit = habits.get(habitName);
        habit.complete();
        saveHabits();
    }

    public Map<String, Habit> viewHabits() {
        return habits;
    }

    public void deleteHabit(String habitName) {
        if (!habits.containsKey(habitName)) {
            throw new IllegalArgumentException("Habit does not exist.");
        }
        habits.remove(habitName);
        saveHabits();
    }
}

class Habit {
    private String name;
    private int streak;
    private LocalDate lastCompleted;

    public Habit(String name) {
        this.name = name;
        this.streak = 0;
        this.lastCompleted = null;
    }

    public void complete() {
        LocalDate today = LocalDate.now();
        if (today.equals(lastCompleted)) {
            throw new IllegalArgumentException("Habit already completed today.");
        }
        if (today.minusDays(1).equals(lastCompleted)) {
            streak++;
        } else {
            streak = 1;
        }
        lastCompleted = today;
    }

    public String getName() {
        return name;
    }

    public int getStreak() {
        return streak;
    }

    public LocalDate getLastCompleted() {
        return lastCompleted;
    }

    public static Map<String, Habit> deserializeMap(String json) {
        // Implement JSON deserialization
        // This is a placeholder implementation, you need a library like Jackson or Gson to handle JSON parsing
        return new HashMap<>();
    }

    public static String serializeMap(Map<String, Habit> habits) {
        // Implement JSON serialization
        // This is a placeholder implementation, you need a library like Jackson or Gson to handle JSON parsing
        return "";
    }
}
