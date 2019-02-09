package monstercoin.dao;

import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;

import java.util.List;

public interface CryptoTransactionDAO
{
    public List<CryptoTransaction> getTransactions();

    public List<CryptoTransaction> activeTransactionsPerUser(int user_id);

    public List<CryptoTransaction> inactiveTransactionsPerUser(int user_id);

    public void saveTransaction(CryptoTransaction cryptoTransaction);

    public void updateOrderStatus(CryptoTransaction cryptoTransaction);

    public void deleteTransaction(int cryptoTransaction_id);
}
