public class Student {

    private String id;
    private String name;
    private String department;
    private double gpa;   // ⭐ DAY 11

    // Constructor
    public Student(String id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Display format
    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | GPA: " + gpa;
    }
}
