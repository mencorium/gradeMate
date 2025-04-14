import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static  Scanner scanner = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Student Stats");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");

            int choice =  scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> viewStats();
                case 4 -> viewAllStudents();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        students.add(new Student(name, id));
        System.out.printf("Student %s Adedd", id);
    }

    static void addGrade() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Student student = findStudent(id);
        if (student != null) {
            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();
            student.addGrade(grade);
            System.out.println("Grade added.");
        } else {
            System.out.println("Student not found");
        }
    }

    static void viewStats() {
        System.out.print("enter student ID: ");
        String id = scanner.nextLine();
        Student student = findStudent(id);
        if (student != null) {
            System.out.printf("Average: %.2f%n", student.getAverage());
            System.out.printf("Highest: %d%n", student.getHighestGrade());
            System.out.printf("Lowest: %.2f%n", student.getLowestGrade());
        } else {
            System.out.println("Student not found.");
        }
    }

    static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        // Determine max name length
        int maxNameLength = "Name".length();
        for (Student student : students) {
            if (student.getName().length() > maxNameLength) {
                maxNameLength = student.getName().length();
            }
        }

        // Table headers
        String nameHeader = padRight("Name", maxNameLength);
        System.out.printf("%s | %-36s | Grades%n", nameHeader, "ID");
        System.out.println("-".repeat(maxNameLength + 43 + 9));

        // Table rows
        for (Student student : students) {
            String name = padRight(student.getName(), maxNameLength);
            String grades = student.getGrades().isEmpty() ? "None" : student.getGrades().toString();
            System.out.printf("%s | %-36s | %s%n", name, student.getId(), grades);
        }
    }

    // Helper method to pad strings
    static String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }


    static Student findStudent(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }
}

