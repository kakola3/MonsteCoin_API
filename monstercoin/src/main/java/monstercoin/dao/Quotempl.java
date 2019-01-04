package monstercoin.dao;

import monstercoin.entity.Quote;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Quotempl implements QuoteDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveQuote(Quote quote) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(quote);
    }
}
