package monstercoin.coinbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import monstercoin.entity.CryptoCurrency;
import monstercoin.entity.Quote;
import monstercoin.entity.QuoteDetail;
import monstercoin.service.CoinbotService;
import monstercoin.service.CoinbotServiceImpl;
import monstercoin.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Coinbot
{
    @Autowired
    private CoinbotService coinbotService;

    public static void request() throws IOException {
        URL url = new URL("https://api.coinpaprika.com/v1/tickers/btc-bitcoin");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");

        con.setConnectTimeout(20000);
        con.setReadTimeout(20000);

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();// maybe StringBuilder
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        System.out.println(content);

        JSONObject cryptoCurrencyField = new JSONObject(content.toString());

        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(0);
        cryptoCurrency.setName(cryptoCurrencyField.getString("name"));
        cryptoCurrency.setSymbol(cryptoCurrencyField.getString("symbol"));
        cryptoCurrency.setRank(cryptoCurrencyField.getInt("rank"));
        cryptoCurrency.setCirculating_supply(cryptoCurrencyField.getLong("circulating_supply"));
        cryptoCurrency.setMax_supply(cryptoCurrencyField.getLong("max_supply"));
        cryptoCurrency.setLast_updated(cryptoCurrencyField.getString("last_updated"));


        JSONObject cryptoCurrencyQuotes = cryptoCurrencyField.getJSONObject("quotes");
        Quote quote = new Quote();
        quote.setId(0);
        if(cryptoCurrencyQuotes.getJSONObject("USD").isEmpty()){
            quote.setFiat_symbol(null);
        }else{
            quote.setFiat_symbol("USD");
        }


        JSONObject cryptoCurrencyQuoteFields = cryptoCurrencyQuotes.getJSONObject("USD");

        QuoteDetail quoteDetail = new QuoteDetail();
        quoteDetail.setId(0);
        quoteDetail.setPrice(cryptoCurrencyQuoteFields.getDouble("price"));
        quoteDetail.setVolume_24h(cryptoCurrencyQuoteFields.getDouble("volume_24h"));
        quoteDetail.setVolume_24h_change_24h(cryptoCurrencyQuoteFields.getDouble("volume_24h_change_24h"));
        quoteDetail.setMarket_cap(cryptoCurrencyQuoteFields.getDouble("market_cap"));
        quoteDetail.setMarket_cap_change_24h(cryptoCurrencyQuoteFields.getDouble("market_cap_change_24h"));
        quoteDetail.setPercent_change_1h(cryptoCurrencyQuoteFields.getDouble("percent_change_1h"));
        quoteDetail.setPercent_change_12h(cryptoCurrencyQuoteFields.getDouble("percent_change_12h"));
        quoteDetail.setPercent_change_24h(cryptoCurrencyQuoteFields.getDouble("percent_change_24h"));
        quoteDetail.setPercent_change_7d(cryptoCurrencyQuoteFields.getDouble("percent_change_7d"));
        quoteDetail.setPercent_change_30d(cryptoCurrencyQuoteFields.getDouble("percent_change_30d"));
        quoteDetail.setPercent_change_1y(cryptoCurrencyQuoteFields.getDouble("percent_change_1y"));
        quoteDetail.setAth_price(cryptoCurrencyQuoteFields.getDouble("ath_price"));
        quoteDetail.setAth_date(cryptoCurrencyQuoteFields.getString("ath_date"));
        quoteDetail.setPercent_from_price_ath(cryptoCurrencyQuoteFields.getDouble("percent_from_price_ath"));


        cryptoCurrency.add(quote);

        quote.setQuoteDetail(quoteDetail);
    }
}
