package ex01;


public class User {
    private final int id;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0) {
            throw new IllegalArgumentException("it cannot be negative");
        }
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setBalance(int amount) {
        if (this.balance + amount < 0) {
            throw new IllegalArgumentException("자금 부족");
        }
        this.balance += amount;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", balance=" + balance + "]";
    }
}
