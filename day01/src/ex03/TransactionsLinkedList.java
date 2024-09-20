package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node head;
    private int size;

    private class Node {
        Transaction transaction;
        Node next;

        Node(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }

    }
    public TransactionsLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void removeTransactionById(UUID uuid) throws TransactionNotFoundException {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.transaction.getId().equals(uuid)) {
                if (previous == null) {
                    head = current.next;
                } else {
                 previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
        throw new TransactionNotFoundException("Transaction with ID: " + uuid + " not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactionArray = new Transaction[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            transactionArray[index++] = current.transaction;
            current = current.next;
        }
        return transactionArray;
    }

    public int getSize() {
        return size;
    }
}
