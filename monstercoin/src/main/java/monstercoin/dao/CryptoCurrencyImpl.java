package monstercoin.dao;

import monstercoin.entity.CryptoCurrency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CryptoCurrencyImpl implements CryptoCurrencyDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCryptoCurrency(CryptoCurrency cryptoCurrency) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(cryptoCurrency);
    }
}
