import java.util.PriorityQueue;
import java.util.Scanner;

public class LibraryBookReservation {


    static class Reservation implements Comparable<Reservation> {
        String studentName;
        String bookTitle;
        int priority;
        public Reservation(String studentName, String bookTitle, int priority) {
            this.studentName = studentName;
            this.bookTitle = bookTitle;
            this.priority = priority;
        }

        @Override
        public int compareTo(Reservation other) {
            return Integer.compare(this.priority, other.priority); // Min-heap based on priority
        }

        @Override
        public String toString() {
            return "Student: " + studentName + " | Book: " + bookTitle + " | Priority: " + priority;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Reservation> reservationQueue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library Book Reservation System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Reserve a book");
            System.out.println("2. View the next reservation");
            System.out.println("3. Complete the next reservation");
            System.out.println("4. View all reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter priority (1 = high, 5 = low): ");
                    int priority = scanner.nextInt();
                    reservationQueue.add(new Reservation(studentName, bookTitle, priority));
                    System.out.println("Reservation added!");
                    break;

                case 2:
                    if (reservationQueue.isEmpty()) {
                        System.out.println("No reservations in the queue.");
                    } else {
                        System.out.println("Next reservation: " + reservationQueue.peek());
                    }
                    break;

                case 3:
                    if (reservationQueue.isEmpty()) {
                        System.out.println("No reservations to complete.");
                    } else {
                        System.out.println("Completed reservation: " + reservationQueue.poll());
                    }
                    break;

                case 4:
                    if (reservationQueue.isEmpty()) {
                        System.out.println("No reservations in the queue.");
                    } else {
                        System.out.println("All reservations:");
                        for (Reservation reservation : reservationQueue) {
                            System.out.println(reservation);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
