package monstercoin.coinbot;

import monstercoin.entity.CryptoCurrency;
import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.Quote;
import monstercoin.entity.QuoteDetail;
import monstercoin.service.CoinbotService;
import monstercoin.service.CryptoTransactionService;
import monstercoin.service.QuoteDetailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class Coinbot {

    private CoinbotService coinbotService;

    @Autowired
    void setCoinbotService(CoinbotService coinbotService) {
        this.coinbotService = coinbotService;
    }

    @Autowired
    CryptoTransactionService cryptoTransactionService;

    @Autowired
    QuoteDetailService quoteDetailService;

    public static StringBuffer[] request() throws IOException {
        StringBuffer[] cryptoContentArray = new StringBuffer[5];

        URL[] urlArray = new URL[5];
        urlArray[0]= new URL("https://api.coinpaprika.com/v1/tickers/btc-bitcoin");
        urlArray[1]= new URL("https://api.coinpaprika.com/v1/tickers/eth-ethereum");
        urlArray[2]= new URL("https://api.coinpaprika.com/v1/tickers/ltc-litecoin");
        urlArray[3]= new URL("https://api.coinpaprika.com/v1/tickers/xrp-xrp");
        urlArray[4]= new URL("https://api.coinpaprika.com/v1/tickers/eos-eos");

        for (int i=0; i<5; i++) {
            HttpURLConnection con = (HttpURLConnection) urlArray[i].openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("Content-Type", "application/json");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

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
            cryptoContentArray[i] = content;
            System.out.println("content of cryptoContentArray[" + i + "]: " + content);
        }
        return cryptoContentArray;
    }

    public void contentOperations(CryptoCurrency cryptoCurrency, Quote quote, QuoteDetail quoteDetail, int i) {
//        System.out.println("CRYPTOCURRENCY IN COINBOT CLASS: " + cryptoCurrency.toString());
//        System.out.println("QUOTE IN COINBOT CLASS: " + quote.toString());
        System.out.println("QUOTE_DETAIL IN COINBOT CLASS: " + quoteDetail.toString());
        System.out.println("COINBOT_SERVICE: " + coinbotService.toString());
        coinbotService.saveQuoteDetail(quoteDetail, i);
//        coinbotService.saveQuote(quote);
//        coinbotService.saveCryptoCurrency(cryptoCurrency);
//        JSONObject cryptoCurrencyField = new JSONObject(content.toString());
//
//        CryptoCurrency cryptoCurrency = new CryptoCurrency();
//        cryptoCurrency.setId(0);
//        cryptoCurrency.setName(cryptoCurrencyField.getString("name"));
//        cryptoCurrency.setSymbol(cryptoCurrencyField.getString("symbol"));
//        cryptoCurrency.setRank(cryptoCurrencyField.getInt("rank"));
//        cryptoCurrency.setCirculating_supply(cryptoCurrencyField.getLong("circulating_supply"));
//        cryptoCurrency.setMax_supply(cryptoCurrencyField.getLong("max_supply"));
//        cryptoCurrency.setLast_updated(cryptoCurrencyField.getString("last_updated"));


//        JSONObject cryptoCurrencyQuotes = cryptoCurrencyField.getJSONObject("quotes");
//        Quote quote = new Quote();
//        quote.setId(0);
//        if(cryptoCurrencyQuotes.getJSONObject("USD").isEmpty()){
//            quote.setFiat_symbol(null);
//        }else{
//            quote.setFiat_symbol("USD");
//        }


//        JSONObject cryptoCurrencyQuoteFields = cryptoCurrencyQuotes.getJSONObject("USD");
//
//        QuoteDetail quoteDetail = new QuoteDetail();
//        quoteDetail.setId(0);
//        quoteDetail.setPrice(cryptoCurrencyQuoteFields.getDouble("price"));
//        quoteDetail.setVolume_24h(cryptoCurrencyQuoteFields.getDouble("volume_24h"));
//        quoteDetail.setVolume_24h_change_24h(cryptoCurrencyQuoteFields.getDouble("volume_24h_change_24h"));
//        quoteDetail.setMarket_cap(cryptoCurrencyQuoteFields.getDouble("market_cap"));
//        quoteDetail.setMarket_cap_change_24h(cryptoCurrencyQuoteFields.getDouble("market_cap_change_24h"));
//        quoteDetail.setPercent_change_1h(cryptoCurrencyQuoteFields.getDouble("percent_change_1h"));
//        quoteDetail.setPercent_change_12h(cryptoCurrencyQuoteFields.getDouble("percent_change_12h"));
//        quoteDetail.setPercent_change_24h(cryptoCurrencyQuoteFields.getDouble("percent_change_24h"));
//        quoteDetail.setPercent_change_7d(cryptoCurrencyQuoteFields.getDouble("percent_change_7d"));
//        quoteDetail.setPercent_change_30d(cryptoCurrencyQuoteFields.getDouble("percent_change_30d"));
//        quoteDetail.setPercent_change_1y(cryptoCurrencyQuoteFields.getDouble("percent_change_1y"));
//        quoteDetail.setAth_price(cryptoCurrencyQuoteFields.getDouble("ath_price"));
//        quoteDetail.setAth_date(cryptoCurrencyQuoteFields.getString("ath_date"));
//        quoteDetail.setPercent_from_price_ath(cryptoCurrencyQuoteFields.getDouble("percent_from_price_ath"));


//        cryptoCurrency.add(quote);

//        quote.setQuoteDetail(quoteDetail);

    }

//    public static CryptoCurrency cryptoCurrency(StringBuffer content, Quote quote) {
//        JSONObject cryptoCurrencyField = new JSONObject(content.toString());
//
//        CryptoCurrency cryptoCurrency = new CryptoCurrency();
//        cryptoCurrency.setId(0);
//        cryptoCurrency.setName(cryptoCurrencyField.getString("name"));
//        cryptoCurrency.setSymbol(cryptoCurrencyField.getString("symbol"));
//        cryptoCurrency.setRank(cryptoCurrencyField.getInt("rank"));
//        cryptoCurrency.setCirculating_supply(cryptoCurrencyField.getLong("circulating_supply"));
//        cryptoCurrency.setMax_supply(cryptoCurrencyField.getLong("max_supply"));
//        cryptoCurrency.setLast_updated(cryptoCurrencyField.getString("last_updated"));
//
//        cryptoCurrency.add(quote);
//
//        return cryptoCurrency;
//    }

//    public static Quote quote(StringBuffer content, QuoteDetail quoteDetail) {
//        JSONObject cryptoCurrencyField = new JSONObject(content.toString());
//        JSONObject cryptoCurrencyQuotes = cryptoCurrencyField.getJSONObject("quotes");
//        Quote quote = new Quote();
//        quote.setId(0);
//        if (cryptoCurrencyQuotes.getJSONObject("USD").isEmpty()) {
//            quote.setFiat_symbol(null);
//        } else {
//            quote.setFiat_symbol("USD");
//        }
//        quote.setQuoteDetail(quoteDetail);
//
//        return quote;
//    }

    public static QuoteDetail quoteDetail(StringBuffer content) {
        JSONObject cryptoCurrencyField = new JSONObject(content.toString());
        JSONObject cryptoCurrencyQuotes = cryptoCurrencyField.getJSONObject("quotes");
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

        return quoteDetail;
    }

    public void runningCoinBot(){
        List<CryptoTransaction> cryptoTransactions = cryptoTransactionService.getTransactions();    // all transactions from dataBase
        List<QuoteDetail> quoteDetails = quoteDetailService.getQuoteDetails();  // all quoteDetails from dataBase

    }
}
