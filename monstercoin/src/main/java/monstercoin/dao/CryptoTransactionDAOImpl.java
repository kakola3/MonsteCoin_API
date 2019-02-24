package monstercoin.dao;

import monstercoin.entity.CryptoTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CryptoTransactionDAOImpl implements CryptoTransactionDAO {
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
    public CryptoTransaction getCryptoTransaction(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from CryptoTransaction where id = :id");
        theQuery.setParameter("id", id);

        CryptoTransaction cryptoTransaction = (CryptoTransaction) theQuery.getSingleResult();

        return cryptoTransaction;
    }

    @Override
    public List<CryptoTransaction> activeTransactionsPerUser(int user_id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CryptoTransaction> theQuery = currentSession.createQuery("from CryptoTransaction where user_id = :user_id" +
                " and order_status = 'active'");
        theQuery.setParameter("user_id", user_id);

        List<CryptoTransaction> activeTransactionsPerUser = theQuery.getResultList();

        return activeTransactionsPerUser;
    }

    @Override
    public List<CryptoTransaction> inactiveTransactionsPerUser(int user_id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CryptoTransaction> theQuery = currentSession.createQuery("from CryptoTransaction where user_id = :user_id" +
                " and order_status = 'inactive'");
        theQuery.setParameter("user_id", user_id);

        List<CryptoTransaction> inactiveTransactionsPerUser = theQuery.getResultList();

        return inactiveTransactionsPerUser;
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
        int result = theQuery.executeUpdate();
    }

    @Override
    public void deleteTransaction(int cryptoTransaction_id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from CryptoTransaction where id = :cryptoTransaction_id");
        theQuery.setParameter("cryptoTransaction_id", cryptoTransaction_id);

        int result = theQuery.executeUpdate();
    }

//    @Override
//    public void deleteAllTransactionsPerUser(int user_id) {
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Query theQuery = currentSession.createQuery("delete from CryptoTransaction where user_id = :user_id");
//        theQuery.setParameter("user_id", user_id);
//
//        int result = theQuery.executeUpdate();
//    }
}
