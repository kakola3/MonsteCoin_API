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
    public List<CryptoTransaction> getTransactions() {
        return cryptoTransactionDAO.getTransactions();
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
}
