import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        while (true) {
            showMenu();
            int choice = getChoice();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> deleteStudent();
                    case 4 -> {
                        manager.saveToFile();
                        System.out.println("Goodbye 👋");
                        return;
                    }
                    default -> System.out.println("Invalid option ❌");
                }
            } catch (Exception e) {
                System.out.println("⚠ " + e.getMessage());
                AppLogger.log("ERROR: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Delete Student");
        System.out.println("4. Save & Exit");
        System.out.print("Choose: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a number");
        }
    }

    private static void addStudent() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();

        manager.addStudent(id, name, dept);
        System.out.println("Student added ✅");
    }

    private static void viewStudents() {
        if (manager.getAllStudents().isEmpty()) {
            System.out.println("No students ❌");
            return;
        }
        manager.getAllStudents().forEach(System.out::println);
    }

    private static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String id = scanner.nextLine();
        manager.deleteStudent(id);
        System.out.println("Student deleted ✅");
    }
}
