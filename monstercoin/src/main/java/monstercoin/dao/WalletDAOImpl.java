package monstercoin.dao;

import monstercoin.entity.Wallet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WalletDAOImpl implements WalletDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createWalletForNewUser(Wallet wallet) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(wallet);
    }

    @Override
    public Wallet getWalletPerUser(int user_id) {
        return null;
    }

    @Override
    public void updateWallet(int user_id) {

    }
}
