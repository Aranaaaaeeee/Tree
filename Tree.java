import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tree {

    static class TreeNode {
        String name;
        List<TreeNode> children;

        TreeNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            children.add(child);
        }

        boolean isLeaf() {
            return children.isEmpty();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = new TreeNode("School");

        while (true) {
            System.out.println("\nStudent Manager");
            System.out.println("1. Add Class");
            System.out.println("2. Add Student to Class");
            System.out.println("3. View Classes and Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Class Name: ");
                    String className = scanner.nextLine();
                    root.addChild(new TreeNode(className));
                    System.out.println("Class added successfully!");
                    break;
                case 2:
                    if (root.children.isEmpty()) {
                        System.out.println("No classes available. Add a class first.");
                        break;
                    }
                    System.out.println("Available Classes:");
                    for (int i = 0; i < root.children.size(); i++) {
                        System.out.println((i + 1) + ". " + root.children.get(i).name);
                    }
                    System.out.print("Select Class (by number): ");
                    int classIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (classIndex >= 0 && classIndex < root.children.size()) {
                        System.out.print("Enter Student Name: ");
                        String studentName = scanner.nextLine();
                        root.children.get(classIndex).addChild(new TreeNode(studentName));
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Invalid class selection.");
                    }
                    break;
                case 3:
                    System.out.println("\nClasses and Students:");
                    displayTree(root, 0);
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayTree(TreeNode node, int level) {
        System.out.println(" ".repeat(level * 2) + "- " + node.name);
        for (TreeNode child : node.children) {
            displayTree(child, level + 1);
        }
    }
}
