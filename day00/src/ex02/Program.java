package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        while (true) {
            int num = scanner.nextInt();
            if (num == 42) break;

            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            boolean isPrime = true;
            if (sum <= 1) isPrime = false;
            for (int i = 2; i * i <= sum; ++i) {
                if (sum % i == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) count++;
        }

        System.out.println("Count of coffee-request - " + count);
    }
}

