package monstercoin.dao;

import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CryptoTransactionDAOImpl implements CryptoTransactionDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CryptoTransaction> getTransactions() {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CryptoTransaction> theQuery =
                currentSession.createQuery("from CryptoTransaction");

        // execute query and get result list
        List<CryptoTransaction> cryptoTransactions = theQuery.getResultList();
        System.out.println("users: |" + cryptoTransactions + "|");

        // return the results
        return cryptoTransactions;
    }

    @Override
    public void saveTransaction(CryptoTransaction cryptoTransaction) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/update the transaction ... finally LOL
        currentSession.saveOrUpdate(cryptoTransaction);
    }

    @Override
    public void updateOrderStatus(CryptoTransaction cryptoTransaction) { // executed when active => inactive
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CryptoTransaction> theQuery = currentSession.createQuery("update CryptoTransaction set order_status = :orderStatus" +
                " where id = :transaction_id");
        theQuery.setParameter("orderStatus", "inactive");
        theQuery.setParameter("transaction_id", cryptoTransaction.getId());
    }
}
