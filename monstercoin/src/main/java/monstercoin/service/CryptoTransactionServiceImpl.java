package monstercoin.service;

import monstercoin.dao.CryptoTransactionDAO;
import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CryptoTransactionServiceImpl implements CryptoTransactionService
{
    @Autowired
    CryptoTransactionDAO cryptoTransactionDAO;

    @Override
    @Transactional
    public List<CryptoTransaction> getTransactions() {
        return cryptoTransactionDAO.getTransactions();
    }

    @Override
    @Transactional
    public CryptoTransaction getCryptoTransaction(int id) {
        return cryptoTransactionDAO.getCryptoTransaction(id);
    }

    @Override
    @Transactional
    public List<CryptoTransaction> activeTransactionsPerUser(int user_id) {
        return cryptoTransactionDAO.activeTransactionsPerUser(user_id);
    }

    @Override
    @Transactional
    public List<CryptoTransaction> inactiveTransactionsPerUser(int user_id) {
        return cryptoTransactionDAO.inactiveTransactionsPerUser(user_id);
    }

    @Override
    @Transactional
    public void saveTransaction(CryptoTransaction cryptoTransaction) {
        cryptoTransactionDAO.saveTransaction(cryptoTransaction);
    }

    @Override
    @Transactional
    public void updateOrderStatus(CryptoTransaction cryptoTransaction) {
        cryptoTransactionDAO.updateOrderStatus(cryptoTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(int cryptoTransaction_id) {
        cryptoTransactionDAO.deleteTransaction(cryptoTransaction_id);
    }

//    @Override
//    @Transactional
//    public void deleteAllTransactionsPerUser(int user_id){
//        cryptoTransactionDAO.deleteAllTransactionsPerUser(user_id);
//    }
}
