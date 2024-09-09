package ex03;

import java.util.Scanner;
import java.util.HashMap;

public class Program {
    final static String STOPCODE = "42";
    final static String WEEKTEXT = "Week ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> scores = new HashMap<>(); // 예) Week 1 에 min값은 3 (1, 3)
        int expectedWeek = 1;

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals(STOPCODE)) {
                break;
            }

            if (!input.startsWith(WEEKTEXT)) {
                illegalError();
            }

            int weekNum = 1;
            try {
                weekNum = Integer.parseInt(input.substring(5));
            } catch (NumberFormatException e) {
                illegalError();
            }

            if (weekNum != expectedWeek) {
                illegalError();
            }

            String[] grades = scanner.nextLine().trim().split(" ");
            if (grades.length != 5) {
                illegalError();
            }

            int min = 9;
            for (String grade : grades) {
                int currentGrade = Integer.parseInt(grade);
                min = Math.min(min, currentGrade);
            }
            scores.put(weekNum, min);
            expectedWeek++;
        }

        for (int i = 1; i < expectedWeek; i++) {
            System.out.print("Week " + i + " ");
            for (int j = 0; j < scores.get(i); j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    private static void illegalError() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}
