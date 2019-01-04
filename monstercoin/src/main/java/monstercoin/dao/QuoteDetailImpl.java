package monstercoin.dao;

import monstercoin.entity.QuoteDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDetailImpl implements QuoteDetailDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveQuoteDetail(QuoteDetail quoteDetail) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(quoteDetail);
    }
}
