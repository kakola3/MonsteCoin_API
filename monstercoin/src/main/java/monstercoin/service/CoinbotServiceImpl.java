package monstercoin.service;

import monstercoin.dao.CryptoCurrencyDAO;
import monstercoin.dao.QuoteDAO;
import monstercoin.dao.QuoteDetailDAO;
import monstercoin.dao.UserDAO;
import monstercoin.entity.CryptoCurrency;
import monstercoin.entity.Quote;
import monstercoin.entity.QuoteDetail;
import monstercoin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoinbotServiceImpl implements CoinbotService
{
    // need to inject CryptoCurrency, Quote and QuoteDetail DAOs
    @Autowired
    private CryptoCurrencyDAO cryptoCurrencyDAO;

    @Autowired
    private QuoteDAO quoteDAO;

    @Autowired
    private QuoteDetailDAO quoteDetailDAO;


    @Override
    @Transactional
    public void saveCryptoCurrency(CryptoCurrency cryptoCurrency) {
        cryptoCurrencyDAO.saveCryptoCurrency(cryptoCurrency);
    }

    @Override
    @Transactional
    public void saveQuote(Quote quote) {
        quoteDAO.saveQuote(quote);
    }

    @Override
    @Transactional
    public void saveQuoteDetail(QuoteDetail quoteDetail) {
        quoteDetailDAO.saveQuoteDetail(quoteDetail);
    }
}
