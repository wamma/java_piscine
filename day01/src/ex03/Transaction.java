package ex03;

import java.util.UUID;

public class Transaction {
    private final UUID id;
    private String description;

    public Transaction(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", description=" + description + "]";
    }
}
