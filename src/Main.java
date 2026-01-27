import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ADD STUDENT (with duplicate check)
    static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists ❌");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
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
            System.out.println(s);
        }
    }

    // SEARCH STUDENT
    static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();

        Student s = findStudentById(id);

        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // DELETE STUDENT
    static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();

        Student s = findStudentById(id);

        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully ✅");
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // CORE SEARCH LOGIC (used everywhere)
    static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s; // loop stops here
            }
        }
        return null;
    }
}
