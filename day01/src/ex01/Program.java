package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("aaa", 1000);
        User user2 = new User("bbb", 2000);

        System.out.println(user1);
        System.out.println(user2);

        User user3 = new User("ccc", 3000);
        System.out.println(user3);
    }
}
