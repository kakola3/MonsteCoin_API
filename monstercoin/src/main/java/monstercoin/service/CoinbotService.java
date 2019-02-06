package monstercoin.service;

import monstercoin.entity.CryptoCurrency;
import monstercoin.entity.Quote;
import monstercoin.entity.QuoteDetail;
import org.springframework.stereotype.Component;

@Component
public interface CoinbotService
{
//    public void saveCryptoCurrency(CryptoCurrency cryptoCurrency);
//
//    public void saveQuote(Quote quote);

    public void saveQuoteDetail(QuoteDetail quoteDetail, int i);
}
