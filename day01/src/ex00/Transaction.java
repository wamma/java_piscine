package ex00;
import java.util.UUID;


public class Transaction {
    public enum Category {
        DEBITS, CREDITS
    }
    private  UUID id;
    private User sender;
    private User recipient;
    private Category category;
    private int amount;

    public Transaction(User sender, User recipient, Category category, int amount) {
        this.id = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.category = category;
        this.amount = amount;

        if (category == Category.DEBITS && sender.getBalance() < amount) {
            throw new IllegalArgumentException("거래 자금이 부족합니다");
        }

        sender.setBalance(-amount);
        recipient.setBalance(amount);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", sender=" + sender.getName() + ", recipient=" + recipient.getName() +
                ", category=" + category + ", amount=" + amount + "]";
    }
}
