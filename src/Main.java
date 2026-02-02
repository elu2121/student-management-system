import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        manager.loadFromFile();

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1 -> manager.addStudent();
                case 2 -> manager.viewStudents();
                case 3 -> manager.searchStudent();
                case 4 -> manager.updateStudent();
                case 5 -> manager.deleteStudent();
                case 6 -> {
                    manager.saveToFile();
                    System.out.println("Goodbye 👋");
                    running = false;
                }
                case 7 -> manager.sortByName();
                case 8 -> manager.sortById();
                case 9 -> manager.showTotalStudents();
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save & Exit");
        System.out.println("7. Sort by Name");
        System.out.println("8. Sort by ID");
        System.out.println("9. Show Total Students");
        System.out.print("Choose an option: ");
    }

    // ---------- DAY 10 SAFE INPUT ----------
    private static int getMenuChoice() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number ❌: ");
            }
        }
    }
}
