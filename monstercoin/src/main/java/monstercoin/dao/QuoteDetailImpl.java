package monstercoin.dao;

import monstercoin.entity.QuoteDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDetailImpl implements QuoteDetailDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QuoteDetail> getQuoteDetails() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<QuoteDetail> theQuery = currentSession.createQuery("from QuoteDetail where id <= 5", QuoteDetail.class);

        List<QuoteDetail> quoteDetails = theQuery.getResultList();

        return quoteDetails;
    }

    @Override
    public void saveQuoteDetail(QuoteDetail quoteDetail, int i) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<QuoteDetail> theQuery = currentSession.createQuery("update QuoteDetail set " +
                "price = :price," +
                "volume_24h = :volume_24h," +
                "volume_24h_change_24h = :volume_24h_change_24h," +
                "market_cap = :market_cap," +
                "market_cap_change_24h = :market_cap_change_24h," +
                "percent_change_1h = :percent_change_1h," +
                "percent_change_12h = :percent_change_12h," +
                "percent_change_24h = :percent_change_24h," +
                "percent_change_7d = :percent_change_7d," +
                "percent_change_30d = :percent_change_30d," +
                "percent_change_1y = :percent_change_1y," +
                "ath_price = :ath_price," +
                "ath_date = :ath_date," +
                "percent_from_price_ath = :percent_from_price_ath where id = :id_i");

                theQuery.setParameter("price", quoteDetail.getPrice());
                theQuery.setParameter("volume_24h", quoteDetail.getVolume_24h());
                theQuery.setParameter("volume_24h_change_24h", quoteDetail.getVolume_24h_change_24h());
                theQuery.setParameter("market_cap", quoteDetail.getMarket_cap());
                theQuery.setParameter("market_cap_change_24h", quoteDetail.getMarket_cap_change_24h());
                theQuery.setParameter("percent_change_1h", quoteDetail.getPercent_change_1h());
                theQuery.setParameter("percent_change_12h", quoteDetail.getPercent_change_12h());
                theQuery.setParameter("percent_change_24h", quoteDetail.getPercent_change_24h());
                theQuery.setParameter("percent_change_7d", quoteDetail.getPercent_change_7d());
                theQuery.setParameter("percent_change_30d", quoteDetail.getPercent_change_30d());
                theQuery.setParameter("percent_change_1y", quoteDetail.getPercent_change_1y());
                theQuery.setParameter("ath_price", quoteDetail.getAth_price());
                theQuery.setParameter("ath_date", quoteDetail.getAth_date());
                theQuery.setParameter("percent_from_price_ath", quoteDetail.getPercent_from_price_ath());
                theQuery.setParameter("id_i", i);

        int result = theQuery.executeUpdate();

        //currentSession.saveOrUpdate(quoteDetail);
    }

    @Override
    public double getPrice(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from QuoteDetail where id = :id");
        theQuery.setParameter("id", id);

        QuoteDetail quoteDetail = (QuoteDetail) theQuery.getSingleResult();

        double price = quoteDetail.getPrice();

        return price;
    }

}
