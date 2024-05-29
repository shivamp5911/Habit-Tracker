import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        HabitTracker tracker = new HabitTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHabit Tracker Menu:");
            System.out.println("1. Add Habit");
            System.out.println("2. Complete Habit");
            System.out.println("3. View Habits");
            System.out.println("4. Delete Habit");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter habit name: ");
                        String habitName = scanner.nextLine().trim();
                        tracker.addHabit(habitName);
                        System.out.println("Habit '" + habitName + "' added.");
                        break;
                    case "2":
                        System.out.print("Enter habit name: ");
                        habitName = scanner.nextLine().trim();
                        tracker.completeHabit(habitName);
                        System.out.println("Habit '" + habitName + "' marked as completed for today.");
                        break;
                    case "3":
                        tracker.viewHabits().forEach((name, habit) -> {
                            System.out.println("Habit: " + name + ", Streak: " + habit.getStreak() + ", Last Completed: " + habit.getLastCompleted());
                        });
                        break;
                    case "4":
                        System.out.print("Enter habit name to delete: ");
                        habitName = scanner.nextLine().trim();
                        tracker.deleteHabit(habitName);
                        System.out.println("Habit '" + habitName + "' deleted.");
                        break;
                    case "5":
                        System.out.println("Exiting Habit Tracker.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
