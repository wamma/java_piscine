package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "hyungjup", 100);
        User user2 = new User(2, "jaehejun", 500);

        System.out.println(user1);
        System.out.println(user2);

        try {
            Transaction transaction = new Transaction(user1, user2, Transaction.Category.DEBITS, 200);
            System.out.println(transaction);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(user1);
        System.out.println(user2);
    }
}
