package ex03;

public class Program {
    public static void main(String[] args) {
        TransactionsList transactionsList = new TransactionsLinkedList();

        // 거래 추가
        Transaction t1 = new Transaction("Transaction 1");
        Transaction t2 = new Transaction("Transaction 2");
        Transaction t3 = new Transaction("Transaction 3");

        transactionsList.addTransaction(t1);
        transactionsList.addTransaction(t2);
        transactionsList.addTransaction(t3);

        // 배열로 변환하여 출력
        Transaction[] transactions = transactionsList.toArray();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        // 거래 삭제
        try {
            transactionsList.removeTransactionById(t2.getId());
            System.out.println("Transaction removed successfully.");
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // 거래 삭제 후 배열로 변환하여 출력
        transactions = transactionsList.toArray();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
