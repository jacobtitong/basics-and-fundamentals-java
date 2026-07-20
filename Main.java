// Import arrays and scanner
import java.util.*;

public class Main {
    
    // Declare global variables
    static Scanner sc = new Scanner(System.in);
    static boolean endProgram;
    static String[] students = new String[1];
    static int[] grades = new int[1];
    static int numberOfStudents = students.length - 1;
    static int numberOfGrades = grades.length - 1;
    static int highestGrade = grades[0];
    
    // NOTES: Make validation for grades input
    public static void main(String[] args) {
        endProgram = false;
        
        // Main Menu
        while(!endProgram) {
            System.out.print("\n" + "=".repeat(10) + " GRADE ANALYSIS SYSTEM " + "=".repeat(10));
            System.out.print("\n1 - Add Student\n2 - Grades of Each Student\n3 - Top Performing Students\n4 - Passing Students\n5 - Failing Students\n6 - Class Performance\n0 - Exit\n");
            int option = getValidInt("--> Option: ");
            
            switch (option) {
                case 1:
                addStudent();
                break;
                case 2:
                displayGrades();
                break;
                case 3:
                getTopStudents();
                break;
                case 4:
                getPassingStudents();
                break;
                case 5:
                getFailingStudents();
                break;
                case 6:
                getClassPerformance();
                break;
                case 0:
                endProgram = true;
                break;
            }
        }
    }
    
    public static void addStudent() {
        System.out.print("\n=== ADD STUDENT ===\n");
        System.out.printf("Student #%d Name: ", students.length);
        students[students.length - 1] = sc.nextLine();
        grades[grades.length - 1] = getValidGrade();
        sc.nextLine();
        
        // Adds an empty slot for both arrays
        students = addSlotStudent(students);
        numberOfStudents = students.length - 1;
        grades = addSlotGrade(grades);
        numberOfGrades = grades.length - 1;
    }
    
    // Gets grade between 0 and 100 only
    public static int getValidGrade() {
        int grade;
        do {
            System.out.print("Grade (0-100): ");
            grade = sc.nextInt();
        } while (grade < 0 || grade > 100);
        return grade;
    }
    
    public static String[] addSlotStudent(String[] students) {
        return Arrays.copyOf(students, students.length + 1);
    }
    
    public static int[] addSlotGrade(int[] grades) {
        return Arrays.copyOf(grades, grades.length + 1);
    }
    
    public static void displayGrades() {
        System.out.print("\n=== GRADES OF EACH STUDENT ===\n");
        String letterGrade = "";
        for (int i = 0; i < numberOfGrades; i++) {
            letterGrade = getLetterGrade(i);
            System.out.printf("%s: %d (%s)\n", students[i], grades[i], letterGrade);
        }
    }
    
    public static String getLetterGrade(int i) {
        String letterGrade;
        if (grades[i] >= 98 && grades[i] <= 100) {
            letterGrade = "A+";
        } else if (grades[i] >= 92 && grades[i] <= 97) {
            letterGrade = "A";
        } else if (grades[i] >= 87 && grades[i] <= 91) {
            letterGrade = "B+";
        } else if (grades[i] >= 81 && grades[i] <= 86) {
            letterGrade = "B";
        } else if (grades[i] >= 77 && grades[i] <= 80) {
            letterGrade= "C+";
        } else if (grades[i] >= 71 && grades[i] <= 76) {
            letterGrade = "C";
        } else if (grades[i] >= 60 && grades[i] <= 70) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }
        return letterGrade;
    }
    
    public static void getTopStudents() {
        System.out.print("\n=== TOP PERFORMING STUDENTS ===\n");
        // Get the highest grade amongst all
        for (int i = 0; i < numberOfGrades; i++) {
            if (grades[i] > highestGrade) {
                highestGrade = grades[i];
            }
        }
        
        displayTopStudents(highestGrade);
    }
    
    public static void displayTopStudents(int highestGrade) {
        // Loops a second time to get students matching with the highest grade
        for (int i = 0; i < numberOfGrades; i++) {
            if (grades[i] == highestGrade) {
                System.out.printf("%s: %d\n", students[i], grades[i]);
            }
        }
    }
    
    public static void getPassingStudents() {
        System.out.print("\n=== PASSING STUDENTS ===\n");
        int ctr = 0;
        for (int i = 0; i < numberOfGrades; i++) {
            if (grades[i] >= 60) {
                System.out.printf("%s: %d\n", students[i], grades[i]);
                ctr++;
            }
        }
        System.out.printf("Number of Students Passed: %d\n", ctr);
    }
    
    public static void getFailingStudents() {
        System.out.print("\n=== FAILING STUDENTS ===\n");
        int ctr = 0;
        for (int i = 0; i < numberOfGrades; i++) {
            if (grades[i] < 60) {
                System.out.printf("%s: %d\n", students[i], grades[i]);
                ctr++;
            }
        }
        System.out.printf("Number of Students Failed: %d\n", ctr);
    }
    
    public static void getClassPerformance() {
        System.out.print("\n=== CLASS PERFORMANCE ===\n");
        float classAverage = getClassAverage();
        boolean classPerformance = classAverage >= 70;
        System.out.printf("Class Average: %.2f\n", classAverage);
        if (classPerformance) {
            System.out.print("Overall, the class did well.\n");
        } else {
            System.out.print("Overall, the class did poorly.\n");
        }
    }
    
    public static float getClassAverage() {
        int sum = 0;
        for (int i = 0; i < numberOfGrades; i++) {
            sum+=grades[i];
        }
        return (numberOfGrades == 0) ? 0 : (float) sum / numberOfGrades;
    }
    
    public static int getValidInt(String prompt) {
        int number = -1;
        while (number < 0) {
            System.out.print(prompt);
            try {
                number = sc.nextInt();
                if (number < 0) {
                    System.out.println("Error: Must be greater than or equal to 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number, not text.");
            } finally {
                sc.nextLine();
            }
        }
        return number;
    }
}