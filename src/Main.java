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

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number ❌");
                continue;
            }

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
                case 7 -> sortByName();       // DAY 8
                case 8 -> sortById();         // DAY 8
                case 9 -> showTotalStudents(); // DAY 8
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
        System.out.println("7. Sort by Name (Day 8)");
        System.out.println("8. Sort by ID (Day 8)");
        System.out.println("9. Show Total Students (Day 8)");
        System.out.print("Choose: ");
    }

    // ================= DAY 1 =================
    private static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        students.add(new Student(id, name, dept));
        System.out.println("Student added ✅");
    }

    // ================= DAY 2 =================
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found ❌");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ================= DAY 3 =================
    private static void searchStudent() {
        System.out.print("Enter ID to search: ");
        String id = scanner.nextLine();

        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found ❌");
    }

    // ================= DAY 4 =================
    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        String id = scanner.nextLine();

        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.print("New Name: ");
                s.setName(scanner.nextLine());

                System.out.print("New Department: ");
                s.setDepartment(scanner.nextLine());

                System.out.println("Student updated ✅");
                return;
            }
        }
        System.out.println("Student not found ❌");
    }

    // ================= DAY 5 =================
    private static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String id = scanner.nextLine();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                System.out.println("Student deleted ✅");
                return;
            }
        }
        System.out.println("Student not found ❌");
    }

    // ================= DAY 6 =================
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getDepartment());
            }
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
            System.out.println("Students loaded 📂");
        } catch (IOException e) {
            System.out.println("Error loading file ❌");
        }
    }

    // ================= DAY 8 =================
    private static void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Sorted by name ✅");
    }

    private static void sortById() {
        students.sort(Comparator.comparing(Student::getId));
        System.out.println("Sorted by ID ✅");
    }

    private static void showTotalStudents() {
        System.out.println("Total students: " + students.size());
    }
}
