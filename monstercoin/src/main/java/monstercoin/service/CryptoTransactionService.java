package monstercoin.service;

import monstercoin.entity.CryptoTransaction;

import java.util.List;

public interface CryptoTransactionService
{
    public List<CryptoTransaction> getTransactions();

    public CryptoTransaction getCryptoTransaction(int id);

    public List<CryptoTransaction> activeTransactionsPerUser(int user_id);

    public List<CryptoTransaction> inactiveTransactionsPerUser(int user_id);

    public void saveTransaction(CryptoTransaction cryptoTransaction);

    public void updateOrderStatus(CryptoTransaction cryptoTransaction);

    public void deleteTransaction(int cryptoTransaction_id);

   // public void deleteAllTransactionsPerUser(int user_id);
}
