import java.util.Hashtable;
import java.util.Scanner;

public class GradebookApp {
    public static void main(String[] args) {
        // Hashtable to store student names and grades
        Hashtable<String, Integer> gradebook = new Hashtable<>();
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to the Gradebook Application!");

        do {
            // Display menu
            System.out.println("\nOptions:");
            System.out.println("1. Add Student Grade");
            System.out.println("2. Get Student Grade");
            System.out.println("3. Update Student Grade");
            System.out.println("4. Display All Grades");
            System.out.println("5. Exit");

            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": // Add a student grade
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter grade (0-100): ");
                    try {
                        int grade = Integer.parseInt(scanner.nextLine());
                        if (grade >= 0 && grade <= 100) {
                            gradebook.put(name, grade);
                            System.out.println("Grade added successfully!");
                        } else {
                            System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric grade.");
                    }
                    break;

                case "2": // Get a student's grade
                    System.out.print("Enter student name: ");
                    name = scanner.nextLine();
                    if (gradebook.containsKey(name)) {
                        System.out.println(name + "'s grade: " + gradebook.get(name));
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "3": // Update a student's grade
                    System.out.print("Enter student name: ");
                    name = scanner.nextLine();
                    if (gradebook.containsKey(name)) {
                        System.out.print("Enter new grade (0-100): ");
                        try {
                            int grade = Integer.parseInt(scanner.nextLine());
                            if (grade >= 0 && grade <= 100) {
                                gradebook.put(name, grade);
                                System.out.println("Grade updated successfully!");
                            } else {
                                System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric grade.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "4": // Display all grades
                    if (gradebook.isEmpty()) {
                        System.out.println("No grades available.");
                    } else {
                        System.out.println("All Grades:");
                        gradebook.forEach((key, value) -> System.out.println(key + ": " + value));
                    }
                    break;

                case "5": // Exit
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("5"));

        scanner.close();
    }
}
