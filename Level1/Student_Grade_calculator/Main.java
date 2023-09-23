// package Student_Grade_calculator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of Grade: ");
        int numGrades = scanner.nextInt();

        double[] grades = new double[numGrades];

        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }

        double total = 0;
        for (int i=0;i<numGrades;i++) {
            total += grades[i];
        }

        double average = total / numGrades;
        System.out.println("Average grade: " + average);
    }
}
