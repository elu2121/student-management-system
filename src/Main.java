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

    // ================= SAFE INPUT =================
    private static int getMenuChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number ❌: ");
            }
        }
    }

    private static String getNonEmptyInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty ❌");
        }
    }

    private static double getValidGpa() {
        while (true) {
            System.out.print("Enter GPA (0.0 - 4.0): ");
            try {
                double gpa = Double.parseDouble(scanner.nextLine());
                if (gpa >= 0.0 && gpa <= 4.0) return gpa;
                System.out.println("GPA must be between 0.0 and 4.0 ❌");
            } catch (NumberFormatException e) {
                System.out.println("Invalid GPA ❌");
            }
        }
    }

    // ================= ADD =================
    private static void addStudent() {
        String id = getNonEmptyInput("Enter ID: ");

        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists ❌");
            return;
        }

        String name = getNonEmptyInput("Enter Name: ");
        String dept = getNonEmptyInput("Enter Department: ");
        double gpa = getValidGpa();

        students.add(new Student(id, name, dept, gpa));
        System.out.println("Student added successfully ✅");
    }

    // ================= VIEW =================
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found ❌");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ================= SEARCH =================
    private static void searchStudent() {
        String id = getNonEmptyInput("Enter ID to search: ");
        Student s = findStudentById(id);

        if (s != null) System.out.println(s);
        else System.out.println("Student not found ❌");
    }

    // ================= UPDATE =================
    private static void updateStudent() {
        String id = getNonEmptyInput("Enter ID to update: ");
        Student s = findStudentById(id);

        if (s == null) {
            System.out.println("Student not found ❌");
            return;
        }

        System.out.print("New Name (leave empty to keep same): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) s.setName(name);

        System.out.print("New Department (leave empty to keep same): ");
        String dept = scanner.nextLine().trim();
        if (!dept.isEmpty()) s.setDepartment(dept);

        System.out.print("New GPA (leave empty to keep same): ");
        String gpaInput = scanner.nextLine().trim();
        if (!gpaInput.isEmpty()) {
            try {
                double gpa = Double.parseDouble(gpaInput);
                if (gpa >= 0 && gpa <= 4) s.setGpa(gpa);
            } catch (NumberFormatException ignored) {}
        }

        System.out.println("Student updated successfully ✅");
    }

    // ================= DELETE =================
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

    // ================= FIND =================
    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    // ================= FILE SAVE =================
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(
                    s.getId() + "," +
                    s.getName() + "," +
                    s.getDepartment() + "," +
                    s.getGpa()
                );
            }
            System.out.println("Students saved to file 💾");
        } catch (IOException e) {
            System.out.println("Error saving file ❌");
        }
    }

    // ================= FILE LOAD =================
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 4) {
                    students.add(
                        new Student(d[0], d[1], d[2], Double.parseDouble(d[3]))
                    );
                }
            }
            if (!students.isEmpty()) {
                System.out.println("Students loaded from file 📂");
            }
        } catch (IOException e) {
            System.out.println("Error loading file ❌");
        }
    }

    // ================= SORT & COUNT =================
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
