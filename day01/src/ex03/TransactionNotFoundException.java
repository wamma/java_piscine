package ex03;

public class TransactionNotFoundException extends Exception {
    TransactionNotFoundException(String message) {
        super(message);
    }
}
