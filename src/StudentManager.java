import java.io.*;
import java.util.*;

public class StudentManager {

    private static final String FILE_NAME = "students.txt";
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManager() {
        loadFromFile();
    }

    public void addStudent(String id, String name, String dept) {
        if (findById(id) != null) {
            throw new IllegalArgumentException("Duplicate student ID");
        }
        students.add(new Student(id, name, dept));
        AppLogger.log("Student added: " + id);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student findById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public void deleteStudent(String id) {
        Student s = findById(id);
        if (s == null) {
            throw new NoSuchElementException("Student not found");
        }
        students.remove(s);
        AppLogger.log("Student deleted: " + id);
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getDepartment());
            }
            AppLogger.log("Students saved to file");
        } catch (IOException e) {
            AppLogger.log("ERROR saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 3) {
                    students.add(new Student(d[0], d[1], d[2]));
                }
            }
            AppLogger.log("Students loaded from file");
        } catch (IOException e) {
            AppLogger.log("ERROR loading file: " + e.getMessage());
        }
    }
}
