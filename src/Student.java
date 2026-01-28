public class Student {

    private String id;
    private String name;
    private String department;

    private int programming;
    private int database;
    private int networks;

    public Student(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.programming = -1;
        this.database = -1;
        this.networks = -1;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }

    public void setGrades(int prog, int db, int net) {
        this.programming = prog;
        this.database = db;
        this.networks = net;
    }

    public double getAverage() {
        if (programming < 0 || database < 0 || networks < 0) {
            return -1;
        }
        return (programming + database + networks) / 3.0;
    }

    public String getStatus() {
        double avg = getAverage();
        if (avg < 0) return "N/A";
        return avg >= 50 ? "PASS ✅" : "FAIL ❌";
    }

    public String gradeReport() {
        return """
        ID: %s
        Name: %s
        Department: %s
        Programming: %d
        Database: %d
        Networks: %d
        Average: %.2f
        Status: %s
        """.formatted(
                id, name, department,
                programming, database, networks,
                getAverage(), getStatus()
        );
    }

    public String toFileString() {
        return id + "," + name + "," + department + "," +
               programming + "," + database + "," + networks;
    }

    public static Student fromFileString(String line) {
        String[] d = line.split(",");
        Student s = new Student(d[0], d[1], d[2]);
        s.setGrades(
                Integer.parseInt(d[3]),
                Integer.parseInt(d[4]),
                Integer.parseInt(d[5])
        );
        return s;
    }
}
