import java.io.*;
import java.util.*;

public class StudentManager {

    private static final String FILE_NAME = "students.txt";
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // ---------- INPUT HELPERS ----------
    private String getNonEmptyInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty ❌");
        }
    }

    // ---------- DAY 1 ----------
    public void addStudent() {
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

    // ---------- DAY 2 ----------
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found ❌");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ---------- DAY 3 ----------
    public void searchStudent() {
        String id = getNonEmptyInput("Enter ID to search: ");

        Student s = findStudentById(id);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // ---------- DAY 4 ----------
    public void updateStudent() {
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

    // ---------- DAY 5 ----------
    public void deleteStudent() {
        String id = getNonEmptyInput("Enter ID to delete: ");
        Student s = findStudentById(id);

        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully ✅");
        } else {
            System.out.println("Student not found ❌");
        }
    }

    // ---------- DAY 6 ----------
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getDepartment());
            }
            System.out.println("Students saved to file 💾");
        } catch (IOException e) {
            System.out.println("Error saving file ❌");
        }
    }

    public void loadFromFile() {
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

    // ---------- DAY 8 ----------
    public void sortByName() {
        students.sort(Comparator.comparing(
                Student::getName, String.CASE_INSENSITIVE_ORDER
        ));
        System.out.println("Students sorted by name ✅");
    }

    public void sortById() {
        students.sort(Comparator.comparing(
                Student::getId, String.CASE_INSENSITIVE_ORDER
        ));
        System.out.println("Students sorted by ID ✅");
    }

    // ---------- DAY 9 ----------
    public void showTotalStudents() {
        System.out.println("Total students: " + students.size());
    }

    // ---------- HELPER ----------
    private Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
