package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        if (num <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        int count = 0;
        boolean isPrime = true;
        for (int i = 2; i * i <= num; ++i) {
            ++count;
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) count++;
        System.out.println(isPrime + " " + count);
    }
}
