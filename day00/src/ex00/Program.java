package ex00;

public class Program {
    public static void main(String[] args) {
        int num = 479598;
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        System.out.println(result);
    }
}