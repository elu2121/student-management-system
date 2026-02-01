import java.io.*;
import java.util.*;

public class Main {

    private static final String FILE_NAME = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    saveToFile();
                    System.out.println("Goodbye 👋");
                    running = false;
                }
                case 7 -> sortByName();
                case 8 -> sortById();
                case 9 -> showTotalStudents();
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    // ================= MENU =================
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

    // ================= DAY 10: SAFE INPUT =================
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

    private static String getNonEmptyInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty ❌");
        }
    }

    // ================= DAY 1 =================
    private static void addStudent() {
        String id = getNonEmptyInput("Enter ID: ");

        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists ❌");
            return;
        }

        String name = getNonEmptyInput("Enter Name: ");
        String dept = getNonEmptyInput("Enter Department: ");

        students.add(new Student(id, name, dept));
        System.out.println("Student added successfully ✅");
    }

    // ================= DAY 2 =================
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found ❌");
            return;
        }

        for (Student s : students) {
            System.out.println(
                s.getId() + " | " + s.getName() + " | " + s.getDepartment()
            );
        }
    }

    // ================= DAY 3 =================
    private static void searchStudent() {
        String id = getNonEmptyInput("Enter ID to search: ");

        Student s = findStudentById(id);
        if (s != null) {
            System.out.println(
                s.getId() + " | " + s.getName() + " | " + s.getDepartment()
            );
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // ================= DAY 4 =================
    private static void updateStudent() {
        String id = getNonEmptyInput("Enter ID to update: ");

        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student not found ❌");
            return;
        }

        System.out.print("New Name (leave empty to keep same): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            s.setName(newName);
        }

        System.out.print("New Department (leave empty to keep same): ");
        String newDept = scanner.nextLine().trim();
        if (!newDept.isEmpty()) {
            s.setDepartment(newDept);
        }

        System.out.println("Student updated successfully ✅");
    }

    // ================= DAY 5 =================
    private static void deleteStudent() {
        String id = getNonEmptyInput("Enter ID to delete: ");

        Student s = findStudentById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully ✅");
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // ================= HELPER =================
    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // ================= DAY 6 =================
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(
                    s.getId() + "," +
                    s.getName() + "," +
                    s.getDepartment()
                );
            }
            System.out.println("Students saved to file 💾");
        } catch (IOException e) {
            System.out.println("Error saving file ❌");
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    students.add(new Student(data[0], data[1], data[2]));
                }
            }
            if (!students.isEmpty()) {
                System.out.println("Students loaded from file 📂");
            }
        } catch (IOException e) {
            System.out.println("Error loading file ❌");
        }
    }

    // ================= DAY 8 =================
    private static void sortByName() {
        students.sort(Comparator.comparing(
            Student::getName, String.CASE_INSENSITIVE_ORDER
        ));
        System.out.println("Students sorted by name ✅");
    }

    private static void sortById() {
        students.sort(Comparator.comparing(
            Student::getId, String.CASE_INSENSITIVE_ORDER
        ));
        System.out.println("Students sorted by ID ✅");
    }

    private static void showTotalStudents() {
        System.out.println("Total students: " + students.size());
    }
}
