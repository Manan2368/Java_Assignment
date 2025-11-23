import java.util.*;

class InvalidMarksException extends Exception {
    InvalidMarksException(String message) {
        super(message);
    }
}

class Student {
    int rollNumber;
    String studentName;
    int[] marks = new int[3];

    public Student(int rollNumber, String studentName, int[] marks) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
    }

    void validateMarks() throws InvalidMarksException {
        int i = 0;
        while (i < marks.length) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid Marks! Must be between 0 and 100.");
            }
            i++;
        }
    }

    double calculateAverage() {
        int sum = 0;
        int i = 0;
        while (i < 3) {
            sum += marks[i];
            i++;
        }
        return sum / 3.0;
    }

    void displayResult() {
        System.out.println("Name: " + studentName);
        System.out.println("Roll: " + rollNumber);

        int i = 0;
        while (i < 3) {
            System.out.println("Marks for Subject " + (i + 1) + ": " + marks[i]);
            i++;
        }

        double avg = calculateAverage();
        System.out.println("Average: " + avg);

        if (avg >= 40) {
            System.out.println("RESULT: PASS");
        } else {
            System.out.println("RESULT: FAIL");
        }
    }
}

class ResultManager {
    Scanner input = new Scanner(System.in);
    Student[] students = new Student[100];
    int count = 0;

    void addstudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = input.nextInt();
            input.nextLine();

            System.out.print("Enter Student Name: ");
            String name = input.nextLine();

            int[] marks = new int[3];
            System.out.println("Enter marks for 3 subjects:");

            int i = 0;
            while (i < 3) {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = input.nextInt();
                i++;
            }

            Student st = new Student(roll, name, marks);
            st.validateMarks();

            students[count++] = st;
            System.out.println("Student added successfully!");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            System.out.println("addstudent() operation completed.\n");
        }
    }
    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to Search: ");
            int roll = input.nextInt();

            int i = 0;
            boolean found = false;

            while (i < count) {
                Student s = students[i];
                if (s != null && s.rollNumber == roll) {
                    s.displayResult();
                    found = true;
                    break;
                }
                i++;
            }

            if (!found) {
                System.out.println("Student not found!");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            input.nextLine();
        }
    }

    public void mainMenu() {
        int choice = 0;

        try {
            do {
                System.out.println(" Student Result Management ");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                choice = input.nextInt();

                if (choice == 1) {
                    addstudent();
                } else if (choice == 2) {
                    showStudentDetails();
                } else if (choice == 3) {
                    System.out.println("Exited successfully");
                } else {
                    System.out.println("Invalid Option");
                }

            } while (choice != 3);

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        } finally {
            System.out.println("programe ended");
        }
    }

    public static void main(String[] args) {
        ResultManager obj = new ResultManager();
        obj.mainMenu();
    }
}