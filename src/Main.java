import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        loadFromFile(); // 🔥 DAY 6

        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    saveToFile(); // 🔥 DAY 6
                    System.out.println("Goodbye 👋");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    // MENU
    static void showMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add student");
        System.out.println("2. View students");
        System.out.println("3. Search student");
        System.out.println("4. Update student");
        System.out.println("5. Delete student");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    // ADD STUDENT
    static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists ❌");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter department: ");
        String dept = scanner.nextLine();

        students.add(new Student(id, name, dept));
        System.out.println("Student added successfully ✅");
    }

    // VIEW STUDENTS
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(
                s.getId() + " | " + s.getName() + " | " + s.getDepartment()
            );
        }
    }

    // SEARCH STUDENT
    static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();

        Student s = findStudentById(id);

        if (s != null) {
            System.out.println(
                s.getId() + " | " + s.getName() + " | " + s.getDepartment()
            );
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // UPDATE STUDENT
    static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        String id = scanner.nextLine();

        Student s = findStudentById(id);

        if (s == null) {
            System.out.println("Student not found ❌");
            return;
        }

        System.out.print("New name (leave empty to keep same): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            s.setName(newName);
        }

        System.out.print("New department (leave empty to keep same): ");
        String newDept = scanner.nextLine();
        if (!newDept.isEmpty()) {
            s.setDepartment(newDept);
        }

        System.out.println("Student updated successfully ✅");
    }

    // DELETE STUDENT
    static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                System.out.println("Student deleted successfully ✅");
                return;
            }
        }

        System.out.println("Student not found ❌");
    }

    // HELPER
    static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // 🔥 DAY 6: SAVE TO FILE
    static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Student s : students) {
                writer.write(
                    s.getId() + "," +
                    s.getName() + "," +
                    s.getDepartment() + "\n"
                );
            }
            System.out.println("Students saved to file 💾");
        } catch (IOException e) {
            System.out.println("Error saving file ❌");
        }
    }

    // 🔥 DAY 6: LOAD FROM FILE
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(data[0], data[1], data[2]));
            }
            System.out.println("Students loaded from file 📂");
        } catch (IOException e) {
            System.out.println("Error loading file ❌");
        }
    }
}
