package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction); // 거레 추가
    void removeTransactionById(UUID uuid) throws TransactionNotFoundException; // 거래 삭제
    Transaction[] toArray(); // 배열로 변환
}
