import java.io.File;
import java.time.LocalDate;

public class HabitTrackerTest {
    private static final String TEST_FILE = "test_data.json";
    private HabitTrackerTest tracker;

    public static void main(String[] args) {
        HabitTrackerTest test = new HabitTrackerTest();
        test.setUp();
        test.testAddHabit();
        test.testCompleteHabit();
        test.testDeleteHabit();
        test.tearDown();
        System.out.println("All tests passed.");
    }

    public void setUp() {
        tracker = new HabitTrackerTest(TEST_FILE);
    }

    public void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    public void testAddHabit() {
        tracker.addHabit("Exercise");
        assert tracker.viewHabits().containsKey("Exercise") : "Test addHabit failed";
    }

    public void testCompleteHabit() {
        tracker.addHabit("Exercise");
        tracker.completeHabit("Exercise");
        Habit habit = tracker.viewHabits().get("Exercise");
        assert LocalDate.now().equals(habit.getLastCompleted()) : "Test completeHabit failed (LastCompleted)";
        assert habit.getStreak() == 1 : "Test completeHabit failed (Streak)";
    }

    public void testDeleteHabit() {
        tracker.addHabit("Exercise");
        tracker.deleteHabit("Exercise");
        assert !tracker.viewHabits().containsKey("Exercise") : "Test deleteHabit failed";
    }
}
