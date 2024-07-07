import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StudentProgram {
    public static void main(String[] args) {
        LinkedList<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        boolean continueInput = true;

        while (continueInput) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student address: ");
            String address = scanner.nextLine();

            double GPA = -1;
            boolean validGPA = false;
            while (!validGPA) {
                System.out.print("Enter student GPA: ");
                String gpaInput = scanner.nextLine();
                try {
                    GPA = Double.parseDouble(gpaInput);
                    if (GPA >= 0.0 && GPA <= 4.0) {
                        validGPA = true;
                    } else {
                        System.out.println("GPA must be between 0.0 and 4.0. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA. Please enter a numeric value between 0.0 and 4.0.");
                }
            }

            studentList.add(new Student(name, address, GPA));

            System.out.print("Would you like to add another student? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueInput = false;
            }
        }

        scanner.close();

        // Sort the list by student name
        Collections.sort(studentList);

        // Write the list to a text file
        try (FileWriter writer = new FileWriter("student_data.txt")) {
            for (Student student : studentList) {
                writer.write(student.toString() + System.lineSeparator());
            }
            System.out.println("Student data has been written to student_data.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
