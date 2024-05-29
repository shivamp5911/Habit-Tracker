import java.time.LocalDate;

public class Habit {
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

    // Placeholder methods for serialization
    public static Habit deserialize(String data) {
        String[] parts = data.split(",");
        Habit habit = new Habit(parts[0]);
        habit.streak = Integer.parseInt(parts[1]);
        habit.lastCompleted = parts[2].equals("null") ? null : LocalDate.parse(parts[2]);
        return habit;
    }

    public String serialize() {
        return name + "," + streak + "," + (lastCompleted == null ? "null" : lastCompleted.toString());
    }
}
