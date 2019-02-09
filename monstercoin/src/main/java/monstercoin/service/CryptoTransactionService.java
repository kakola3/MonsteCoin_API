package monstercoin.service;

import monstercoin.entity.CryptoTransaction;

import java.util.List;

public interface CryptoTransactionService
{
    public List<CryptoTransaction> getTransactions();

    public List<CryptoTransaction> activeTransactionsPerUser(int user_id);

    public List<CryptoTransaction> inactiveTransactionsPerUser(int user_id);

    public void saveTransaction(CryptoTransaction cryptoTransaction);

    public void updateOrderStatus(CryptoTransaction cryptoTransaction);
}
