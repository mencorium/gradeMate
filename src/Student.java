import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String id;
    private List<Integer> grades = new ArrayList<>();

    // Sutdent Constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Student behavior
    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getAverage() {
        return grades.stream().mapToInt(g -> g).average().orElse(0.0);
    }

    public int getHighestGrade() {
        return grades.stream().max(Integer::compare).orElse(0);
    }

    public double getLowestGrade() {
        return grades.stream().min(Integer::compare).orElse(0);
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return id;
    }

    public List getGrades() {
        return grades;
    }
}