package monstercoin.dao;

import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;

import java.util.List;

public interface CryptoTransactionDAO
{
    public List<CryptoTransaction> getTransactions();

    public void saveTransaction(CryptoTransaction cryptoTransaction);

    public void updateOrderStatus(CryptoTransaction cryptoTransaction);
}
